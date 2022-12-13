package project.sinam7.advancedYogi.advancedYogi.Domain;

import lombok.Getter;

import java.util.List;

@Getter
public class SimplifiedRestaurant {

    private final Integer id; // Id
    private final String name; // 가게명
    private final Integer min_order_amount; // 최소 배달 금액
    private final Integer free_delivery_threshold; // 무료 배달 기준 금액
    private final Integer adjusted_delivery_fee; // 배달료
    private final String address; // 가게 주소 (도로명/지번)
    private final String delivery_fee_to_display; // 표시용 배달료 (?,???원 or 무료~?,???원 or 무료)
    private final Integer fastest;
    private final Integer slowest;
    private final String estimated_delivery_time;
    private final Integer review_count;
    private final List<String> categories; // 가게 카테고리 (ex: 치킨, 프랜차이즈, 버거, 1인분주문, 테이크아웃)

    public SimplifiedRestaurant(Restaurant restaurant) {
        id = restaurant.getId();
        name = restaurant.getName();
        min_order_amount = restaurant.getMin_order_amount();
        free_delivery_threshold = restaurant.getFree_delivery_threshold();
        adjusted_delivery_fee = restaurant.getAdjusted_delivery_fee();
        address = restaurant.getAddress();
        delivery_fee_to_display = restaurant.getDelivery_fee_to_display();
        estimated_delivery_time = restaurant.getEstimated_delivery_time();
        review_count = restaurant.getReview_count();
        categories = restaurant.getCategories();

        String[] split = estimated_delivery_time.split("~?[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]?");
        fastest = Integer.valueOf(split[0]);
        slowest = Integer.valueOf(split[1]);
    }


}
