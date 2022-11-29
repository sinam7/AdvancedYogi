package project.sinam7.advancedYogi.advancedYogi.Domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchFilter {

    private List<String> categories;
    private List<String> sort_orders;
    private Integer min_order_amount;
    private Integer adjusted_delivery_fee;
    private Integer free_delivery_threshold;
    // TODO 아 맞다 위치정보

}
