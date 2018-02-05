package Selenide.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainSelenidePage {
    @FindBy(className = "header-search-partial-component__search-field")
    private SelenideElement searchField;

    @FindBy(xpath = "//span[text()='Рейтинги']")
    private SelenideElement ratings;

    @FindBy(xpath = "/descendant::*[text()=\"Топ 250\"]")
    private SelenideElement top250;

    @FindBy(css = ".header-menu-partial-component__subitems")
    private SelenideElement popUpchik;

    public FilmSelenidePage searchInfo(String info) {
        searchField.sendKeys(info);
        searchField.submit();
        return page(FilmSelenidePage.class);
    }

    public void hoverOnElementAndCheckPopUp() {
        ratings.hover();
        SelenideElement popUp = $(popUpchik);
        popUp.waitUntil(Condition.visible, 10000);
    }

    public RatingsSelenidePage clickOnTop250() {
        SelenideElement popUp = $(top250);
        popUp.click();
        return page(RatingsSelenidePage.class);
    }
}
