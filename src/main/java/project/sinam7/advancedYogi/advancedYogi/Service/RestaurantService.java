package project.sinam7.advancedYogi.advancedYogi.Service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;
import project.sinam7.advancedYogi.advancedYogi.Domain.Search.SearchFilter;
import project.sinam7.advancedYogi.advancedYogi.Domain.SimplifiedRestaurant;
import project.sinam7.advancedYogi.advancedYogi.Rest.YogiyoRequest;
import project.sinam7.advancedYogi.advancedYogi.Utility.ComparatorGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Setter
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {

    private final YogiyoRequest yogiyoRequest;

    public List<Restaurant> getRestaurants(String category, Integer items, double latitude, double longitude, int pageNum) {
        List<Restaurant> restaurants = null;
        try {
            restaurants = yogiyoRequest.getRestaurants(category, items, latitude, longitude, pageNum);
        } catch (Exception e) {
            log.error("Error occurred whilst running " + this.getClass() + ": " + e);
        }

        return restaurants;
    }


    public ArrayList<Restaurant> getAllRestaurants() {
        return yogiyoRequest.getAllLoadedRestaurants();
    }

    public List<SimplifiedRestaurant> getFilteredRestaurants(List<Restaurant> restaurants, SearchFilter filter) {
        List<SimplifiedRestaurant> result = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            if (isNotValidRestaurant(filter, restaurant)) continue; // 조건에 맞지 않으면 리스트업하지 않음

            // else: 조건에 부합하는 경우이므로 리스트업
            SimplifiedRestaurant sr = new SimplifiedRestaurant(restaurant);
            result.add(sr);
        }

        // 복합 정렬 조건 처리 Comparator 생성 및 정렬
        Comparator<SimplifiedRestaurant> comparator = ComparatorGenerator.getComparator(filter.getSort_orders());
        result.sort(comparator);

        return result;
    }

    private static boolean isNotValidRestaurant(SearchFilter filter, Restaurant restaurant) {
        if (!restaurant.getOpen()) return true; // 닫은 가게 제외
        if (restaurant.getMin_order_amount() > filter.getMin_order_amount()) return true; // 최소주문금액 초과 제외
        if (restaurant.getAdjusted_delivery_fee() > filter.getAdjusted_delivery_fee()) return true; // 배달료 초과 제외
        if (restaurant.getFree_delivery_threshold() >= filter.getFree_delivery_threshold()) { // 무료배달 조건무시처리
            String old = restaurant.getDelivery_fee_to_display();
            String new_string = old.replaceFirst("^무료~", "");
            restaurant.setDelivery_fee_to_display_byString(new_string);
        }
        return false;
    }

}
