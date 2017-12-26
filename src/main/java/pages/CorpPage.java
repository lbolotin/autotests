package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.alfabank.alfatest.cucumber.api.AkitaPage.Name;


@Name("Валютные переводы")
public class CorpPage extends AkitaPage{
    @FindBy(css = "h2.heading")
    @Name("Заголовок")
    private SelenideElement header;

    @FindBy(css = "h3.heading")
    @Name("Второй заголовок")
    private SelenideElement secondHeader;

    @Optional
    @FindBy(xpath = "(//a[text() = 'Новый платёж'])[1]")
    @Name("Новый платёж_Меню")
    private SelenideElement newPay;

    @Optional
    @FindBy(xpath = "(//a[text() = 'Продукты'])[1]")
    @Name("Продукты_Меню")
    private SelenideElement products;

    @FindBy(css = "span.select-company button.company-select-button")
    @Name("Список компаний")
    private SelenideElement companyList;

    @Optional
    @FindBy(xpath = "(//span[text() = 'ЗАО \"Экспромт\"'])[1]")
    @Name("ЗАО Экспромт")
    private SelenideElement unblockCompany;
}
