package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage{
        private WebDriver driver;
        private static final String POP_UP_SELECTOR = ".header-menu-partial-component__subitems";

        @FindBy(className = "header-search-partial-component__search-field")
        private WebElement searchField;

        @FindBy(xpath = "//span[text()='Рейтинги']")
        private WebElement ratings;

        public MainPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public FilmPage searchInfo(String info) {
            searchField.sendKeys(info);
            searchField.submit();
            return new FilmPage(driver);
        }

        public void hoverOnElementAndCheckPopUp(){
            Actions actions = new Actions(driver);
            actions.moveToElement(ratings).build().perform();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement popUp = driver.findElement(By.cssSelector(POP_UP_SELECTOR));
            wait.until(ExpectedConditions.visibilityOf(popUp));
        }

        public RatingsPage clickOnTop250(){
            WebElement popUp = driver.findElement(By.cssSelector(POP_UP_SELECTOR)).findElement(By.xpath("/*[text()=\"Топ 250\"]"));
            popUp.click();
            return new RatingsPage(driver);
        }
}
