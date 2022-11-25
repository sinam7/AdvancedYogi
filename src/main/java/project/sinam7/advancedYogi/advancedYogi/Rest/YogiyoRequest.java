package project.sinam7.advancedYogi.advancedYogi.Rest;

import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import project.sinam7.advancedYogi.advancedYogi.Domain.Pagination;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Rest.DTO.YogiyoRequestDTO;
import project.sinam7.advancedYogi.advancedYogi.Secrets.Secret;

import java.util.*;

@Slf4j
@Component
public class YogiyoRequest {

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final HashMap<Integer, ArrayList<Restaurant>> allLoadedRestaurants = new HashMap<>();

    public List<Restaurant> getRestaurants(double lat, double lng, int pageNum) throws HttpClientErrorException, HttpServerErrorException {

        if (allLoadedRestaurants.containsKey(pageNum)) return allLoadedRestaurants.get(pageNum);
        if (allLoadedRestaurants.size() != pageNum) { // 현재 페이지 이전의 식당 정보를 받지 않은 경우
            for (int i = allLoadedRestaurants.size(); i < pageNum; i++) {
                getRestaurants(lat, lng, i);
            }
        }

        //Spring restTemplate
        String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=60&" + "lat=" + lat + "&lng=" + lng + "&order=rank" + "&page=" + pageNum + "&search=";

        // Spring restTemplate
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(null, null, 200);



        HttpHeaders header = new HttpHeaders();
        header.add("X-ApiKey", Secret.YOGIYO_API_KEY);
        header.add("X-ApiSecret", Secret.YOGIYO_API_KEY_SECRET);

        HttpEntity<?> entity = new HttpEntity<>(header);

        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
        RestTemplate restTemplate = new RestTemplate();

        log.info("Yogiyo API Call Start - " + url);
        ResponseEntity<YogiyoRequestDTO> rateResponse =
                restTemplate.exchange(uri.toString(),
                        HttpMethod.GET, entity, YogiyoRequestDTO.class);

        YogiyoRequestDTO yogiyoRequestDTO = rateResponse.getBody();
        assert yogiyoRequestDTO != null;

        Pagination pagination = yogiyoRequestDTO.getPagination();
        List<Restaurant> restaurants = yogiyoRequestDTO.getRestaurants();

        if (pagination == null) log.error("Error occurred whilst parsing pagination");
        else log.info("Pagination info: " + pagination);

        if (restaurants == null) log.error("Error occurred whilst parsing restaurants");
        else log.info("Restaurants info: " + restaurants);

        return restaurants;
    }


    public ArrayList<Restaurant> getAllLoadedRestaurants() {
        ArrayList<Restaurant> result = new ArrayList<>(allLoadedRestaurants.size() * 60);
        for (Integer integer : allLoadedRestaurants.keySet()) {
            result.addAll(allLoadedRestaurants.get(integer));
        }

        return result;
    }

}
