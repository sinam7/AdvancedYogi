package project.sinam7.advancedYogi.advancedYogi.Rest;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import project.sinam7.advancedYogi.advancedYogi.Service.Secrets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

@Slf4j
@SuppressWarnings("unchecked")
public class YogiyoRequest {

    @SuppressWarnings("UnnecessaryToStringCall")
    public Object getRestaurants(double lat, double lng) {
        //Spring restTemplate
        String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=60&" +
                "lat=" + lat + "&lng=" + lng +
                "&order=rank&page=0&search=";

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
            ResponseJsonFactory resultBody = new ResponseJsonFactory((LinkedHashMap<String, ?>) Objects.requireNonNull(responseEntity.getBody()));

            return resultBody.restaurants;

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

    /**
     * Pagination 정보와 restaurants ArrayList를 분리, 가공, 처리를 위한 클래스
     * TODO restaurants의 형태를 ArrayList<Restaurant>로 변경
     */
    private static class ResponseJsonFactory {
        LinkedHashMap<String, Integer> pagination;
        ArrayList<LinkedHashMap<String, Object>> restaurants;

        ResponseJsonFactory(LinkedHashMap<String, ?> raw) {
            pagination = (LinkedHashMap<String, Integer>) raw.get("pagination");
            restaurants = (ArrayList<LinkedHashMap<String, Object>>) raw.get("restaurants");
        }

    }

}
