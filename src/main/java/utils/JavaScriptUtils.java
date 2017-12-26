package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Created by U_021GQ on 12.10.2017.
 */
public class JavaScriptUtils {

    public static WebElement expandRootElement(WebElement element) {
        WebElement ele = (WebElement) ((JavascriptExecutor) getWebDriver())
                .executeScript("return arguments[0].shadowRoot",element);
        return ele;
    }
}
