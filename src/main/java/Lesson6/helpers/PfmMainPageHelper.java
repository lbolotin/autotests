//package Lesson6.helpers;
//
//import Lesson6.enums.CurrencyType;
//import Lesson6.entities.Amount;
//import Lesson6.entities.CommonCategory;
//import Lesson6.pages.PfmMainPage;
//import ru.alfabank.alfatest.cucumber.api.AkitaScenario;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static java.lang.String.format;
//
//public class PfmMainPageHelper {
//
//    public List<CommonCategory> getUserCategoriesAmountAndNamesFromMainPage() {
//        List<CommonCategory> uiCategories = new ArrayList<>();
//        AkitaScenario.withPage(PfmMainPage.class, page ->
//                uiCategories.addAll(page.getUiCategoriesStringAmountsAndNamesFromMainPage())
//        );
//        return uiCategories;
//    }
//
//    public static String getAmountWithCurrencyWithoutFractionPart(Amount amount) {
//        int minority = amount.getCurrency().getMinority();
//        Double value = Double.valueOf(amount.getValue());
//        Double a = value / minority;
//        String amountValue = format("%1.2f", a).replace(".", "");
//        StringBuilder amountToReturn = new StringBuilder(amountValue);
//        int length = amountValue.length();
//        amountToReturn.delete(length - 2, length).append(getCurrencySymbol(amount.getCurrency().getCode()));
//        return amountToReturn.toString().replaceAll(" ", "");
//    }
//
//    public static String getCurrencySymbol(String code) {
//        return CurrencyType.valueOf(code.toUpperCase()).getSign();
//    }
//
//}
