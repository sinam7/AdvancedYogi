package project.sinam7.advancedYogi.advancedYogi.Rest.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import project.sinam7.advancedYogi.advancedYogi.Domain.Pagination;
import project.sinam7.advancedYogi.advancedYogi.Domain.Restaurant;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class YogiyoRequestDTO implements Serializable {

    Pagination pagination;
    List<Restaurant> restaurants;

}
