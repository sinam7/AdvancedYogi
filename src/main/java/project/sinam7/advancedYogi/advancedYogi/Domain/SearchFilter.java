package project.sinam7.advancedYogi.advancedYogi.Domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchFilter {

    private List<String> categories;
    private List<String> sort_orders;
    private Integer min_order_amount = 12000;
    private Integer adjusted_delivery_fee = 3000;
    private Integer free_delivery_threshold = 50000;
    // TODO 아 맞다 위치정보

}
