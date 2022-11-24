package project.sinam7.advancedYogi.advancedYogi.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Rest.YogiyoRequest;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@Setter
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final YogiyoRequest yogiyoRequest;

    public double latitude;
    public double longitude;

    private int currentPageNum = 0;

    // todo Session 도입 시 어떻게 사용할지 생각좀 해야할듯
    public Object getRestaurantsNextPage() {
        currentPageNum += 1;
        return getRestaurants(currentPageNum);
    }

    public ArrayList<Restaurant> getRestaurants(int pageNum) {
        currentPageNum = pageNum;
        ArrayList<LinkedHashMap<String, Object>> result = null;

        ArrayList<Restaurant> restaurants = null;
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
