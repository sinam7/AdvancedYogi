package project.sinam7.advancedYogi.advancedYogi.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings("unused")
@Getter
@Setter
public class Restaurant {

    public LinkedHashMap<String, Object> variableMap = new LinkedHashMap<>();

    public Integer getId() {
        if (id == null) id = (Integer) variableMap.get("id");
        return id;
    }

    private Integer id; // Id
    private Boolean app_order; // 앱 주문 가능
    private Boolean phone_order; // 전화 주문 가능
    private String name; // 가게명
    private Boolean except_cash; // ?? - 현금?
    private Boolean is_available_delivery; // 배달 가능
    private Boolean is_available_pickup; // 픽업 가능
    private List<String> categories; // 가게 카테고리 (ex: 치킨, 프랜차이즈, 버거, 1인분주문, 테이크아웃)
    private List<String> tags; // 가게 태그 (ex: relayo <- ?, 세스코)
    private List<String> payment_methods; // 결제 수단 (ex: 신용카드, 온라인)
    private Integer discount_percent; // 할인율(%)
    private String restaurant_type; // 가게 타입 (ex: 음식)
    private Integer additional_discount; // 추가 할인 금액
    private Double review_avg; // 리뷰 평균 점수
    private Integer review_count; // 리뷰 수
    private Integer franchise_id; // 프랜차이즈 ID
    private Double lat; // 주소 - 위도
    private Double lng; // 주소 - 경도
    private String begin; // 영업 시작 시각 (24h 표기)
    private String end; // 영업 종료 시각 (24h 표기)
    private Double new_rating; // ????
    private Double distance; // 거리 (km?)
    private Integer min_order_amount; // 최소 주문 금액
    private Integer free_delivery_threshold; // 무료 배달 기준 금액
    private Boolean is_deliverable; // 배달 가능
    private String reason; // ?? - 배달 불가능 시 이유
    private Boolean _new; // 신규 입점 가게
    private Boolean open; // 가게 오픈 중
    private Integer owner_reply_count; // 점주 답글 수
    private String estimated_delivery_time; // 예상 배달 시간
    private String representative_menus; // 대표 메뉴
    private Boolean has_shop_coupons; // 가게 쿠폰 여부
    private Integer adjusted_delivery_fee; // 배달료
    private String delivery_method; // 배달 방식
    private List<String> section; // 가게 섹션 (핫딜 등)
    private Integer section_pos; // ?? - 섹션 위치
    private Integer list_pos; // 목록 내 가게 순번 (1부터)
    private String phone; // 가게 전화번호
    private String address; // 가게 주소 (도로명/지번)
    private String logo_url; // 가게 로고 url
    private String thumbnail_url; // 가게 썸네일 (가게 클릭 시 가게 이름 뒤에 나오는) url
    private Boolean reachable; // ?? - 주문 가능
    private Integer minimum_pickup_minutes; // 포장 최소 픽업 시간
    private String franchise_name; // 프랜차이즈 이름
    private Boolean is_oe; // ????
    private String discount_from; // 할인 시작 시각 - nullable
    private String discount_until; // 할인 종료 시각 - nullable
    private Object discounts; // discounts - additional - (delivery, pickup 중 최소 1개, 없으면 discounts 자체가 없음)
    private String promotion_text; // 신규 입점 알림 광고멘트 (새로 들어왔어요, 첫 리뷰 남겨주세요, ...)
    private String thumbnail_message; // ?? - 보통 비어 있는듯
    // todo String 파싱을 통해 값 분리가 필요할 수 있음
    private String delivery_fee_to_display; // 표시용 배달료 (?,???원 or 무료~?,???원 or 무료)

    public Restaurant() {
        initVariableMap();
    }

    public static Restaurant restaurantMapper(LinkedHashMap<String, Object> from) {
        Restaurant r = new Restaurant();
        for (String s : from.keySet()) r.variableMap.put(s, from.get(s));
        return r;
    }

    public static ArrayList<Restaurant> restaurantMapper(ArrayList<LinkedHashMap<String, Object>> fromArrayList) {
        ArrayList<Restaurant> restaurants = new ArrayList<>(fromArrayList.size());


        for (LinkedHashMap<String, Object> from : fromArrayList) {
            restaurants.add(restaurantMapper(from));
        }

        return restaurants;
    }

    private void initVariableMap() {
        variableMap.put("id", id);
        variableMap.put("app_order", app_order);
        variableMap.put("phone_order", phone_order);
        variableMap.put("name", name);
        variableMap.put("except_cash", except_cash);

        variableMap.put("is_available_delivery", is_available_delivery);
        variableMap.put("is_available_pickup", is_available_pickup);
        variableMap.put("categories", categories);
        variableMap.put("tags", tags);
        variableMap.put("payment_methods", payment_methods);

        variableMap.put("discount_percent", discount_percent);
        variableMap.put("restaurant_type", restaurant_type);
        variableMap.put("additional_discount", additional_discount);
        variableMap.put("review_avg", review_avg);
        variableMap.put("review_count", review_count);

        variableMap.put("franchise_id", franchise_id);
        variableMap.put("lat", lat);
        variableMap.put("lng", lng);
        variableMap.put("begin", begin);
        variableMap.put("end", end);

        variableMap.put("new_rating", new_rating);
        variableMap.put("distance", distance);
        variableMap.put("min_order_amount", min_order_amount);
        variableMap.put("free_delivery_threshold", free_delivery_threshold);
        variableMap.put("is_deliverable", is_deliverable);

        variableMap.put("reason", reason);
        variableMap.put("new", _new);
        variableMap.put("open", open);
        variableMap.put("owner_reply_count", owner_reply_count);
        variableMap.put("estimated_delivery_time", estimated_delivery_time);

        variableMap.put("representative_menus", representative_menus);
        variableMap.put("has_shop_coupons", has_shop_coupons);
        variableMap.put("adjusted_delivery_fee", adjusted_delivery_fee);
        variableMap.put("delivery_method", delivery_method);
        variableMap.put("section", section);

        variableMap.put("section_pos", section_pos);
        variableMap.put("list_pos", list_pos);
        variableMap.put("phone", phone);
        variableMap.put("address", address);
        variableMap.put("logo_url", logo_url);

        variableMap.put("thumbnail_url", thumbnail_url);
        variableMap.put("reachable", reachable);
        variableMap.put("minimum_pickup_minutes", minimum_pickup_minutes);
        variableMap.put("franchise_name", franchise_name);
        variableMap.put("is_oe", is_oe);

        variableMap.put("discount_from", discount_from);
        variableMap.put("discount_until", discount_until);
        variableMap.put("discounts", discounts);
        variableMap.put("promotion_text", promotion_text);
        variableMap.put("thumbnail_message", thumbnail_message);

        variableMap.put("delivery_fee_to_display", delivery_fee_to_display);
    }

}
