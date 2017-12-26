//package pages;
//
//import com.codeborne.selenide.SelenideElement;
//import entities.Amount;
//import entities.Category;
//import entities.CommonCategory;
//import entities.Currency;
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.FindBy;
//import ru.alfabank.alfatest.cucumber.api.AkitaPage;
//import ru.alfabank.alfatest.cucumber.api.AkitaPage.Name;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.codeborne.selenide.Selenide.$;
//import static java.lang.String.format;
//
//@Name("Расходы и доходы")
//public class PfmMainPage extends AkitaPage {
//
//    private final String CATEGORY_NAME_XPATH = ".//span[contains(@class,'category__title')]";
//    private final String CATEGORY_STRINGAMOUNT_XPATH = ".//div[contains(@class,'fact-amount')]//span[@class='label__inner']";
//
//    @FindBy(css = ".content__main h1")
//    @Name("Заголовок")
//    private SelenideElement title;
//
//    @FindBy(css = ".categories-list__items")
//    @Name("Блок категорий")
//    private SelenideElement categoriesBlock;
//
//
//    @FindBy(xpath = "//li[contains(@class,'category_theme_alfa-on-color')]//div[contains(@class,'category__content-wrapper')]")
//    @Name("Список категорий")
//    private List<SelenideElement> categories;
//
//    @FindBy(xpath = "//h4[.=\"Расходы\"]//following-sibling::div//span[@class=\"label__inner\"]/span")
//    @Name("Сумма расходов")
//    private SelenideElement labelinner;
//
//    public List<CommonCategory> getUiCategoriesStringAmountsAndNamesFromMainPage() {
//        List<CommonCategory> uiCategories = new ArrayList<>();
//        categories.forEach(item -> {
//            CommonCategory uiCategory = new CommonCategory();
//            uiCategory.setTitle(item.findElement(By.xpath(CATEGORY_NAME_XPATH)).getText());
//            uiCategory.setStringAmount(item.findElement(By.xpath(CATEGORY_STRINGAMOUNT_XPATH)).getText().replaceAll(" ", ""));
//            uiCategories.add(uiCategory);
//        });
//        return uiCategories;
//    }
//
//}