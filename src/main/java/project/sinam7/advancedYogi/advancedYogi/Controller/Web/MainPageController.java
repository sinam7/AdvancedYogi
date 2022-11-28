package project.sinam7.advancedYogi.advancedYogi.Controller.Web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.sinam7.advancedYogi.advancedYogi.Domain.Options;
import project.sinam7.advancedYogi.advancedYogi.Domain.SearchFilter;

import java.util.Map;

@Slf4j
@Controller
public class MainPageController {

    @ModelAttribute("categories")
    public Map<String, String> categories() {
        return Options.getCategories();
    }

    @GetMapping("/")
    public String indexPage(@ModelAttribute(name = "filter") SearchFilter searchFilter) {
        return "index.html";
    }

}
