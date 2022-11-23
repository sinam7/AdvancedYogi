package project.sinam7.advancedYogi.advancedYogi.Controller.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.sinam7.advancedYogi.advancedYogi.Service.RestaurantService;
import project.sinam7.advancedYogi.advancedYogi.Service.Secrets;

@RestController // todo 나중에 페이지 만들면 Controller로
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantListController {

    private final RestaurantService restaurantService;

    // TODO geo 정보 받아서 query param으로 받아 position 설정
    @GetMapping()
    public Object getRestaurants() {
        setPosition(Secrets.MY_LAT, Secrets.MY_LNG);
        return restaurantService.getRestaurants();
    }

    @GetMapping("/pages/{pageNum}")
    public Object getRestaurants(@PathVariable String pageNum) {
        setPosition(Secrets.MY_LAT, Secrets.MY_LNG);
        return restaurantService.getRestaurants(Integer.parseInt(pageNum));
    }

    // 지금까지 받은 모든 식당 정보
    @GetMapping("/loadAll")
    public Object getAllLoadedRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // todo 이거 이렇게 만들면 안될 것 같은데 어디로 옮기지
    private void setPosition(double latitude, double longitude) {
        restaurantService.setLatitude(latitude);
        restaurantService.setLongitude(longitude);
    }


}
