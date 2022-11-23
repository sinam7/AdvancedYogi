package project.sinam7.advancedYogi.advancedYogi.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import project.sinam7.advancedYogi.advancedYogi.Rest.YogiyoRequest;

@Service
@Setter
@RequiredArgsConstructor
public class RestaurantService {

    private final YogiyoRequest yogiyoRequest;

    public double latitude;
    public double longitude;

    private int currentPageNum = 0;

    public Object getRestaurants() {
        currentPageNum = 0;
        return getRestaurants(0);
    }

    // todo Session 도입 시 어떻게 사용할지 생각좀 해야할듯
    public Object getRestaurantsNextPage() {
        currentPageNum += 1;
        return getRestaurants(currentPageNum);
    }

    public Object getRestaurants(int pageNum) {
        currentPageNum = pageNum;
        return yogiyoRequest.getRestaurants(latitude, longitude, pageNum);
    }


    public Object getAllRestaurants() {
        return yogiyoRequest.getAllLoadedRestaurants();
    }
}
