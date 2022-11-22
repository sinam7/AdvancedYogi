package project.sinam7.advancedYogi.advancedYogi.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.sinam7.advancedYogi.advancedYogi.Rest.YogiyoRequest;

@Service
public class RestaurantService {

    public ResponseEntity<Object> getRestaurants() {
        YogiyoRequest yogiyoRequest = new YogiyoRequest();

        return yogiyoRequest.getRestaurants(Secrets.MY_LAT, Secrets.MY_LNG);
    }
}
