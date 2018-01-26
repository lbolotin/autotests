package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RatingsPage {
    private WebDriver driver;

    @FindBy(xpath = "//tr[@class=\"top250_place_1\"]")
    private WebElement top1;

    public RatingsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public FilmPage topOneMovieClick(){
        top1.click();
        return new FilmPage(driver);
    }
}
