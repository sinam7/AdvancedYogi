package project.sinam7.advancedYogi.advancedYogi.Rest.DTO;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Pagination 정보와 restaurants ArrayList를 분리, 가공, 처리를 위한 클래스
 * TODO restaurants의 형태를 ArrayList<Restaurant>로 변경
 */
@Getter
public class ResponseJsonDTO {

    /** (example)
     * "pagination": {
     * "per_page": 60, 페이지당 데이터 수
     * "current_page": 0, 현재 페이지
     * "total_pages": 27, 총 페이지 수
     * "total_objects": 1615 총 데이터 수
     */
    LinkedHashMap<String, Integer> pagination;
    ArrayList<LinkedHashMap<String, Object>> restaurants;

    public ResponseJsonDTO(LinkedHashMap<String, ?> raw) {
        pagination = (LinkedHashMap<String, Integer>) raw.get("pagination");
        restaurants = (ArrayList<LinkedHashMap<String, Object>>) raw.get("restaurants");
    }

    public Integer getCurrentPage() {
        return pagination.get("current_page");
    }

}
