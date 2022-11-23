package project.sinam7.advancedYogi.advancedYogi.Controller.Web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.sinam7.advancedYogi.advancedYogi.Service.RestaurantService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantListController {

    public final RestaurantService restaurantService;

    @GetMapping()
    @ResponseBody
    public Object getRestaurants() {
        return restaurantService.getRestaurants();
    }
}
