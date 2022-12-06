package project.sinam7.advancedYogi.advancedYogi.Controller.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.sinam7.advancedYogi.advancedYogi.Domain.Search.Options;
import project.sinam7.advancedYogi.advancedYogi.Domain.Search.SearchFilter;

import java.util.Map;

@Slf4j
@Controller
public class MainPageController {

    @Autowired
    private RestaurantRestController restaurantRestController;

    @ModelAttribute("categories")
    public Map<String, String> categories() {
        return Options.getCategories();
    }

    @ModelAttribute("sort_orders")
    public Map<String, String> sortOrders() {
        return Options.getSortOrder();
    }

    @SuppressWarnings("SpringMVCViewInspection")
    @GetMapping("/")
    public String indexPage(@ModelAttribute(name = "filter") SearchFilter searchFilter) {
        return "index.html";
    }

    @ResponseBody // todo submit -> RestaurantController.result 로 PRG 패턴 적용
    @PostMapping("/submit")
    public Object search(@ModelAttribute(name = "filter") SearchFilter searchFilter) {
        // 나중에 위치정보 위경도로 받을 필요 없어지면 삭제
        if (!validateGeoPosition(searchFilter)) {
            log.warn("Invalid Geoposition input: " +
                    "{lat: " + searchFilter.getLatitudeText() + ", lng: " + searchFilter.getLongitudeText() + "}");
//            return indexPage(searchFilter);
//            return "redirect:/";
            return "잘못된 위치정보입니다!";
        }
        log.info(searchFilter.toString());
        return restaurantRestController.getRestaurants(searchFilter, "0");
    }

    private boolean validateGeoPosition(SearchFilter filter){
        try {
            filter.setLatitude(Double.valueOf(filter.getLatitudeText()));
            filter.setLongitude(Double.valueOf(filter.getLongitudeText()));
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

}
