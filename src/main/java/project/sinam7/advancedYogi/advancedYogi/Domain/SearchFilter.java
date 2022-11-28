package project.sinam7.advancedYogi.advancedYogi.Domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SearchFilter {

    private List<String> categories;

}
