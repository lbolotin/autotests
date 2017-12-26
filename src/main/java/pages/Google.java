package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.alfabank.alfatest.cucumber.api.AkitaPage.Name;

@Name("GoogleMain")
public class Google extends AkitaPage{

    @FindBy(xpath = "//input[@name=\"btnK\"]")
    @Name("Поиск в Google")
    public SelenideElement searchGoogle;

    @FindBy(xpath = "//input[@name=\"btnI\"]")
    @Name("Мне повезет!")
    public SelenideElement iAmLucky;

    @FindBy(xpath = "//input[@id=\"gs_taif0\"]")
    @Name("Строка поиска")
    public SelenideElement searchInputText;
}
