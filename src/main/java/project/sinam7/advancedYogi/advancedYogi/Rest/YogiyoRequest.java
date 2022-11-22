package project.sinam7.advancedYogi.advancedYogi.Rest;

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

import java.util.HashMap;

public class YogiyoRequest {

    @SuppressWarnings("UnnecessaryToStringCall")
    public ResponseEntity<Object> getRestaurants(double lat, double lng) {
        //Spring restTemplate
        String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=60&" +
                "lat=" + lat + "&lng=" + lng +
                "&order=rank&page=0&search=";
        //Spring restTemplate
        HashMap<String, Object> result = new HashMap<>();
        ResponseEntity<Object> resultMap = new ResponseEntity<>(null, null, 200);

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.add("X-ApiKey", Secrets.YOGIYO_API_KEY);
            header.add("X-ApiSecret", Secrets.YOGIYO_API_KEY_SECRET);

            HttpEntity<?> entity = new HttpEntity<>(header);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body", e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

            return resultMap;
        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body", "excpetion오류");
            System.out.println(e.toString());

            return resultMap;

        }

        return resultMap;
    }
}
