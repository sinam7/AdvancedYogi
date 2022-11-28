package project.sinam7.advancedYogi.advancedYogi.Domain;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Options {

    private static final Map<String, String> categories = new LinkedHashMap<>();


    public Options() {
        initCategories();
    }

    private void initCategories() {
        String[] ss = new String[]{
                "1인분주문", "프랜차이즈", "치킨", "피자양식", "중식",
                "한식", "일식돈까스", "족발보쌈", "야식", "분식",
                "카페디저트", "편의점", // 메인 화면 정렬 순서
                "고기구이", "아시안", "찜탕", "버거", "샌드위치",
                "회초밥", "도시락죽", "테이크아웃", "신규맛집", "예약픽업",
                "헬스뷰티", "리빙라이프" // 여기는 개발자 생각대로 짠 순서
                };
        for (String s : ss) categories.put(s, s);
    }

    public static Map<String, String> getCategories() {
        return categories;
    }

}
