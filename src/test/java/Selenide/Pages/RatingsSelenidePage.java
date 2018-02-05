package Selenide.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class RatingsSelenidePage {

    @FindBy(xpath = "//tr[@class=\"top250_place_1\"]")
    private WebElement top1;

    public FilmSelenidePage topOneMovieClick() {
        top1.click();
        return page(FilmSelenidePage.class);
    }
}
