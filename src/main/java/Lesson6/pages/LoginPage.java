//package Lesson6.pages;
//
//import com.codeborne.selenide.SelenideElement;
//import org.openqa.selenium.support.FindBy;
//import ru.alfabank.alfatest.cucumber.api.AkitaPage;
//import ru.alfabank.alfatest.cucumber.api.AkitaPage.Name;
//
//@Name("Страница входа")
//public class LoginPage extends AkitaPage {
//
//    /**
//    * Optional - аннотация, позволяющая исключать поле
//    * из обязательной проверки наличия на странице при загрузке страницы
//    **/
//    @Optional
//    @FindBy(css = "div.authorization__login-input input:first-child")
//    @Name("Логин")
//    private SelenideElement login;
//
//    @Optional
//    @FindBy(css = "div.input-password input:first-child")
//    @Name("Пароль")
//    private SelenideElement password;
//
//    @Optional
//    @FindBy(xpath = "//span[text()='Войти']/parent::button")
//    @Name("Войти")
//    private SelenideElement submitButton;
//}
