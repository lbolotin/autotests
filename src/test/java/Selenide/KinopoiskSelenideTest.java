package Selenide;

import Selenide.Pages.FilmSelenidePage;
import Selenide.Pages.MainSelenidePage;
import Selenide.Pages.RatingsSelenidePage;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class KinopoiskSelenideTest {

    private static final String GEO = "Геошторм";

    @Before
    public void openUrl() {
        open("https://www.kinopoisk.ru/");
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
    }

    @Test
    public void searchFilm() throws InterruptedException {

        MainSelenidePage mainPage = Selenide.page(MainSelenidePage.class);
        FilmSelenidePage filmPage = mainPage.searchInfo(GEO);
        filmPage.findMovieTitle().shouldHave(text("GEO"));
    }

    @Test
    public void chtoToTest() {
        MainSelenidePage mainPage = page(MainSelenidePage.class);
        mainPage.hoverOnElementAndCheckPopUp();
        RatingsSelenidePage ratingsPage = mainPage.clickOnTop250();
        FilmSelenidePage filmPage = ratingsPage.topOneMovieClick();
        filmPage.findMovieTitle().shouldHave(text("Побег из Шоушенка (1994)"));
        title().equals((text("Побег из Шоушенка — КиноПоиск")));
    }
}
