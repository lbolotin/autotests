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
        System.setProperty("browser", "chrome");
        open("https://www.kinopoisk.ru/");
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
    }

    @Test
    public void searchFilm() {

        MainSelenidePage mainPage = Selenide.page(MainSelenidePage.class);
        FilmSelenidePage filmPage = mainPage.searchInfo(GEO);
        filmPage.findMovieTitle().shouldHave(text("Геошторм"));
    }

    @Test
    public void checkTopOneClickFilm() {
        MainSelenidePage mainPage = page(MainSelenidePage.class);
        mainPage.hoverOnElementAndCheckPopUp();
        RatingsSelenidePage ratingsPage = mainPage.clickOnTop250();
        FilmSelenidePage filmPage = ratingsPage.topOneMovieClick();
        filmPage.findMovieTitle().shouldHave(text("Побег из Шоушенка"));
        title().equals((text("Побег из Шоушенка — КиноПоиск")));
    }
}
