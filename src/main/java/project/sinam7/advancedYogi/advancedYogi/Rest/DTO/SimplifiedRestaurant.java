package project.sinam7.advancedYogi.advancedYogi.Rest.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SimplifiedRestaurant {

    private final Integer id; // Id
    private final String name; // 가게명
    private final Integer free_delivery_threshold; // 무료 배달 기준 금액
    private final Integer adjusted_delivery_fee; // 배달료
    private final String address; // 가게 주소 (도로명/지번)
    private final String delivery_fee_to_display; // 표시용 배달료 (?,???원 or 무료~?,???원 or 무료)

}
