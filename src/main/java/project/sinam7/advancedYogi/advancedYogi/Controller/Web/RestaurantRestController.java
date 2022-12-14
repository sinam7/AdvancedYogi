package project.sinam7.advancedYogi.advancedYogi.Controller.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Domain.Search.SearchFilter;
import project.sinam7.advancedYogi.advancedYogi.Domain.SimplifiedRestaurant;
import project.sinam7.advancedYogi.advancedYogi.Service.RestaurantService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    @GetMapping("/pages/{pageNum}")
    public List<SimplifiedRestaurant> getRestaurants(@ModelAttribute SearchFilter filter, @PathVariable(required = false) String pageNum) {
        int num = pageNum.isEmpty() ? 0 : Math.max(Integer.parseInt(pageNum), 0);

        List<Restaurant> result;
        // 전체 검색
        if (filter.getCategories().size() == 0) {
            result = new ArrayList<>(restaurantService.getRestaurants("", 60, filter.getLatitude(), filter.getLongitude(), num));
        } 
        
        // 카테고리 검색
        else {
            int maximumSize = 60 * filter.getCategories().size(); // items per search * category size
            result = new ArrayList<>(maximumSize);
            HashSet<Integer> duplicateChecker = new HashSet<>(maximumSize);

            for (String category : filter.getCategories()) {
                List<Restaurant> restaurants = restaurantService.getRestaurants(category, 60, filter.getLatitude(), filter.getLongitude(), num);
                for (Restaurant restaurant : restaurants) {
                    if (!duplicateChecker.contains(restaurant.getId())) {
                        result.add(restaurant);
                        duplicateChecker.add(restaurant.getId());
                    }
                }
            }
        }
        return restaurantService.getFilteredRestaurants(result, filter);
    }

    // 지금까지 받은 모든 식당 정보
    @GetMapping("/loadAll")
    public ArrayList<Restaurant> getAllLoadedRestaurants() {
        return restaurantService.getAllRestaurants();
    }

}
