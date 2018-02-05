//package Lesson6.pages.blocks;
//
//import com.codeborne.selenide.SelenideElement;
//import org.openqa.selenium.support.FindBy;
//import ru.alfabank.alfatest.cucumber.api.AkitaPage;
//import ru.alfabank.alfatest.cucumber.api.AkitaPage.Name;
//
//@Name("GoogleSearch")
//public class GoogleResults {
//
//    @Optional
//    @FindBy(xpath = "//div[@id=\"rso\"]//div[@class=\"g\"][2]/preceding-sibling::div")
//    @AkitaPage.Name("Первый элемент")
//    public SelenideElement elementFirst;
//
//    @Optional
//    @FindBy(xpath = "//div[@id=\"rso\"]//div[@class=\"g\"][2]")
//    @AkitaPage.Name("Второй элемент")
//    public SelenideElement elementSecond;
//
//    @Optional
//    @FindBy(xpath = "//div[@id=\"rso\"]//div[@class=\"g\"][3]")
//    @AkitaPage.Name("Третий элемент")
//    public SelenideElement elementThird;
//
//    @Optional
//    @FindBy(xpath = "//div[@id=\"rso\"]//div[@class=\"g\"][4]")
//    @AkitaPage.Name("Четвертый элемент")
//    public SelenideElement elementFourth;
//}