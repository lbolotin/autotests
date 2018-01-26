//package Lesson6.utils;
//
//import com.google.gson.reflect.TypeToken;
//import Lesson6.entities.Category;
//import Lesson6.utils.GsonUtil;
//
//import java.time.YearMonth;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Predicate;
//
//import static java.util.stream.Collectors.toList;
//
//public class ResponseConverter {
//    public List<Category> getCategoriesByType(String json, Predicate<String> filter) {
//        return parseFinancialPeriodResponse(json)
//                .entrySet()
//                .stream()
//                .filter(entry -> filter.test(entry.getKey()))
//                .map(Map.Entry::getValue)
//                .flatMap(Collection::stream)
//                .collect(toList());
//    }
//
//    private Map<String, List<Category>> parseFinancialPeriodResponse(String response) {
//        return GsonUtil.GSON_CONVERTER
//                .fromJson(response, new TypeToken<Map<String, List<Category>>>() {}.getType());
//    }
//}
