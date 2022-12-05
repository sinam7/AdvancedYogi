package project.sinam7.advancedYogi.advancedYogi.Domain;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class ComparatorGenerator {

    public static Comparator<SimplifiedRestaurant> getComparator(List<String> sort_orders) {

        Comparator<SimplifiedRestaurant> comparator = ((o1, o2) -> 0);
        if (!sort_orders.isEmpty()) {
            for (String order : sort_orders) {
                comparator = comparator.thenComparing(getComparingFunction(order));
            }
        }

        return comparator;
    }

    /**
     * 정렬 메소드 설정
     * @param order SearchFilter key 값
     * @return SimplifiedRestaurant::getFoo
     * @see Options
     */
    private static Function<? super SimplifiedRestaurant, ? extends Integer> getComparingFunction(String order) {
        return switch (order) {
            case "min_order_amount" -> SimplifiedRestaurant::getMin_order_amount;
            case "adjusted_delivery_fee" -> SimplifiedRestaurant::getAdjusted_delivery_fee;
            case "estimated_delivery_time" -> SimplifiedRestaurant::getFastest;
            case "review_count" -> SimplifiedRestaurant::getReview_count;
            default -> SimplifiedRestaurant::getId;
        };
    }

}
