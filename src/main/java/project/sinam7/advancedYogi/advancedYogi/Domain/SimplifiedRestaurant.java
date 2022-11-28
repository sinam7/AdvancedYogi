package project.sinam7.advancedYogi.advancedYogi.Domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SimplifiedRestaurant {

    private final Integer id; // Id
    private final String name; // 가게명
    private final Integer free_delivery_threshold; // 무료 배달 기준 금액
    private final Integer adjusted_delivery_fee; // 배달료
    private final String address; // 가게 주소 (도로명/지번)
    private final String delivery_fee_to_display; // 표시용 배달료 (?,???원 or 무료~?,???원 or 무료)
    private Boolean open; // 가게 오픈 중
    private List<String> categories; // 가게 카테고리 (ex: 치킨, 프랜차이즈, 버거, 1인분주문, 테이크아웃)

}
