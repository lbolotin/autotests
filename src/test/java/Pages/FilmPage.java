package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FilmPage {
    private static final String MOVIE_NAME = ".moviename-big";
    private WebDriver driver;

    @FindBy(css = ".moviename-big")
    private WebElement movieName;

    public FilmPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    public String findMovieTitle() {
        WebElement nameField = driver.findElement(By.cssSelector(MOVIE_NAME));
        return nameField.getText();
    }
}