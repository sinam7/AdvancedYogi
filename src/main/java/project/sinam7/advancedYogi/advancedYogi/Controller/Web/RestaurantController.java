package project.sinam7.advancedYogi.advancedYogi.Controller.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Domain.SimplifiedRestaurant;
import project.sinam7.advancedYogi.advancedYogi.Rest.YogiyoRequest;

import java.net.URI;
import java.util.ArrayList;

@RestController // todo 페이지 만들고 controller로
@RequestMapping("/restaurants")
@Slf4j
public class RestaurantController {

    @GetMapping()
    public ResponseEntity<?> getRestaurants() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/restaurants"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/bad")
    public ArrayList<SimplifiedRestaurant> getBadDeliveryPolicyRestaurants() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);

        YogiyoRequest yogiyoRequest = new YogiyoRequest();
        ArrayList<Restaurant> allRestaurants = yogiyoRequest.getAllLoadedRestaurants();
        log.info("Checking " + allRestaurants.size() + " restaurants...");

        ArrayList<Restaurant> badRestaurants = new ArrayList<>();
        for (Restaurant res : allRestaurants) {
            if (res.getFree_delivery_threshold() >= 50_000) {
                badRestaurants.add(res);
            }
        }
        log.info("Found " + badRestaurants.size() + " restaurant" + (badRestaurants.size() != 1 ? "s" : ""));

        ArrayList<SimplifiedRestaurant> result = new ArrayList<>();

        for (Restaurant res : badRestaurants) {
            SimplifiedRestaurant sr = new SimplifiedRestaurant(
                    res.getId(),
                    res.getName(),
                    res.getFree_delivery_threshold(),
                    res.getAdjusted_delivery_fee(),
                    res.getAddress(),
                    res.getDelivery_fee_to_display());

            result.add(sr);
        }
        log.info("Simplified " + result.size() + " restaurant" + (result.size() != 1 ? "s" : ""));

        return result;
    }

}
