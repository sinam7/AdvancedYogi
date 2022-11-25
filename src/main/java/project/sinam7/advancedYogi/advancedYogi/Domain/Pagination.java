package project.sinam7.advancedYogi.advancedYogi.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {

    private Integer per_page;   // 페이지당 데이터 수
    private Integer current_page; // 현재 페이지
    private Integer total_pages; // 총 페이지 수
    private Integer total_objects; // 총 데이터 수
}
