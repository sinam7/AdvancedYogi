package project.sinam7.advancedYogi.advancedYogi.Rest;

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
import project.sinam7.advancedYogi.advancedYogi.Domain.Search.Pagination;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Rest.DTO.YogiyoRequestDTO;
import project.sinam7.advancedYogi.advancedYogi.Secrets.Secret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class YogiyoRequest {

    private static final HashMap<Integer, List<Restaurant>> allLoadedRestaurants = new HashMap<>();

    public List<Restaurant> getRestaurants(String category, Integer items, double lat, double lng, int pageNum) throws HttpClientErrorException, HttpServerErrorException {

        // Spring restTemplate
        String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?" +
            (!category.isEmpty() ? ("category=" + category + "&") : "") +
            "items=" + (items != null ? items : "60") +
            "&lat=" + lat + "&lng=" + lng +
            "&order=rank" +
            "&page=" + pageNum +
            "&search=";


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

        if (pagination == null) {
            log.error("Error occurred whilst parsing pagination");
            throw new IllegalStateException();
        }
        log.info("Pagination info: " + pagination);

        if (restaurants == null) {
            log.error("Error occurred whilst parsing restaurants");
            throw new IllegalStateException();
        }
        log.info("Restaurants info: " + restaurants);

        allLoadedRestaurants.put(pagination.getCurrent_page(), restaurants);

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
