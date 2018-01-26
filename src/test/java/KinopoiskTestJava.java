import Pages.FilmPage;
import Pages.MainPage;
import Pages.RatingsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class KinopoiskTestJava {
    WebDriver driver;
    private static final String GEO = "Геошторм";
    private static final String THE_SHAWSHANK_REDEMPTION = "Побег из Шоушенка (1994)";
    private static final String THE_SHAWSHANK_REDEMPTION_TITLE = "Побег из Шоушенка — КиноПоиск";


    @Before
    public void createDriver() {
        driver = new ChromeDriver();
        driver.get("https://www.kinopoisk.ru/");
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void closeDriver(){
        driver.quit();
    }

    @Test
    public void searchFilm() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        FilmPage filmPage = mainPage.searchInfo(GEO);
        Assert.assertEquals(GEO, filmPage.findMovieTitle());
    }

    @Test
    public void chtoToTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.hoverOnElementAndCheckPopUp();
        RatingsPage ratingsPage = mainPage.clickOnTop250();
        FilmPage filmPage = ratingsPage.topOneMovieClick();
        Assert.assertEquals(THE_SHAWSHANK_REDEMPTION, filmPage.findMovieTitle());
        Assert.assertEquals(THE_SHAWSHANK_REDEMPTION_TITLE, driver.getTitle());
    }
}
