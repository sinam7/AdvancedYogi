package project.sinam7.advancedYogi.advancedYogi.Controller.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Domain.SearchFilter;
import project.sinam7.advancedYogi.advancedYogi.Domain.SimplifiedRestaurant;
import project.sinam7.advancedYogi.advancedYogi.Service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    @GetMapping("/pages/{pageNum}")
    public List<SimplifiedRestaurant> getRestaurants(@ModelAttribute SearchFilter searchFilter, @PathVariable(required = false) String pageNum) {
        int num = pageNum.isEmpty() ? 0 : Math.max(Integer.parseInt(pageNum), 0);

        List<Restaurant> restaurants = restaurantService.getRestaurants(searchFilter.getLatitude(), searchFilter.getLongitude(), num);
        return restaurantService.getFilteredRestaurants(restaurants, searchFilter);
    }

    // 지금까지 받은 모든 식당 정보
    @GetMapping("/loadAll")
    public ArrayList<Restaurant> getAllLoadedRestaurants() {
        return restaurantService.getAllRestaurants();
    }

}
