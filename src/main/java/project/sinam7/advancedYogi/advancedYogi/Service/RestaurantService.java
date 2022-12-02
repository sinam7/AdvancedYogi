package project.sinam7.advancedYogi.advancedYogi.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Rest.YogiyoRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@Setter
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final YogiyoRequest yogiyoRequest;

    public List<Restaurant> getRestaurants(double latitude, double longitude, int pageNum) {
        List<Restaurant> restaurants = null;
        try {
            restaurants = yogiyoRequest.getRestaurants(latitude, longitude, pageNum);
        } catch (Exception e) {
            log.error("Error occurred whilst running " + this.getClass() + ": " + e);
        }

        return restaurants;
    }


    public ArrayList<Restaurant> getAllRestaurants() {
        return yogiyoRequest.getAllLoadedRestaurants();
    }
}
