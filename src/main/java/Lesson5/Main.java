package Lesson5;

import java.io.IOException;
import java.util.List;

//Вывести на экран все названия должников (debtorName) в верхнем регистре для завершенных поставок.
// Названия разумеется не должны повторяться, поместить полученные названия в список List<String> debtors
public class Main {
    public static void main(String[] args) throws IOException {
        List<Supply> debtors = JsonParser.getSuppliesList();
        debtors.stream()
                .filter(debitor -> "Завершено".equals(debitor.getStatus().getDescription()))
                .map(Supply::getDebtorName)
                .map(String::toUpperCase)
                .distinct()
                .forEach(System.out::println);


//                .stream()
//                .filter(debitor -> "Завершено".equals(debitor.getStatus().getDescription()))
//                .map(Supply::getDebtorName)
//                .peek(System.out::println)
//                .collect()
    }

}
