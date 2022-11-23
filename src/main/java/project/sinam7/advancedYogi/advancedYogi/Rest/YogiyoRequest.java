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
import project.sinam7.advancedYogi.advancedYogi.Rest.DTO.ResponseJsonDTO;
import project.sinam7.advancedYogi.advancedYogi.Service.Secrets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

@Slf4j
@Component
@SuppressWarnings("unchecked")
public class YogiyoRequest {

    private final HashMap<Integer, ArrayList<LinkedHashMap<String, Object>>> allLoadedRestaurants = new HashMap<>();

    @SuppressWarnings("UnnecessaryToStringCall")
    public Object getRestaurants(double lat, double lng, int pageNum) {

        if (allLoadedRestaurants.containsKey(pageNum)) return allLoadedRestaurants.get(pageNum);
        if (allLoadedRestaurants.size() != pageNum) { // 현재 페이지 이전의 식당 정보를 받지 않은 경우
            for (int i = allLoadedRestaurants.size(); i < pageNum; i++) {
                getRestaurants(lat, lng, i);
            }
        }

        //Spring restTemplate
        String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=60&" +
                "lat=" + lat + "&lng=" + lng +
                "&order=rank" +
                "&page=" + pageNum +
                "&search=";

        // Spring restTemplate
        ResponseEntity<Object> responseEntity = new ResponseEntity<>(null, null, 200);
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.add("X-ApiKey", Secrets.YOGIYO_API_KEY);
            header.add("X-ApiSecret", Secrets.YOGIYO_API_KEY_SECRET);

            HttpEntity<?> entity = new HttpEntity<>(header);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            responseEntity = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

            // Body 가공
            ResponseJsonDTO resultBody = new ResponseJsonDTO(
                    (LinkedHashMap<String, ?>) Objects.requireNonNull
                            (responseEntity.getBody()));

            ArrayList<LinkedHashMap<String, Object>> restaurants = resultBody.getRestaurants();

            Integer currentPage = resultBody.getCurrentPage();

            // todo 이거 없어도 되는지 확인
            if (!allLoadedRestaurants.containsKey(currentPage)) {
                allLoadedRestaurants.put(currentPage, restaurants);
            }

            return restaurants;

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("error");
            System.out.println(e.toString());

            return responseEntity;
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println(responseEntity.getBody());

            return responseEntity;

        }

    }


    public Object getAllLoadedRestaurants() {
        ArrayList<Object> result = new ArrayList<>(allLoadedRestaurants.size() * 60);
        for (Integer integer : allLoadedRestaurants.keySet()) {
            result.addAll(allLoadedRestaurants.get(integer));
        }

        return result;
    }

}
