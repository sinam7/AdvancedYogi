package project.sinam7.advancedYogi.advancedYogi.Controller.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Service.RestaurantService;
import project.sinam7.advancedYogi.advancedYogi.Service.Secrets;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantRestController {

    private final RestaurantService restaurantService;

    // TODO geo 정보 받아서 query param으로 받아 position 설정
    @GetMapping()
    public ArrayList<Restaurant> getRestaurants() {
        return getRestaurants("0");
    }

    @GetMapping("/pages/{pageNum}")
    public ArrayList<Restaurant> getRestaurants(@PathVariable(required = false) String pageNum) {
        setPosition(Secrets.MY_LAT, Secrets.MY_LNG);
        int num = pageNum.isEmpty() ? 0 : Math.max(Integer.parseInt(pageNum), 0);

        return restaurantService.getRestaurants(num);
    }

    // 지금까지 받은 모든 식당 정보
    @GetMapping("/loadAll")
    public ArrayList<Restaurant> getAllLoadedRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // todo 이거 이렇게 만들면 안될 것 같은데 어디로 옮기지
    private void setPosition(double latitude, double longitude) {
        restaurantService.setLatitude(latitude);
        restaurantService.setLongitude(longitude);
    }


}
