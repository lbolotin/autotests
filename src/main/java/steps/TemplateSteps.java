//package steps;
//
//import com.codeborne.selenide.SelenideElement;
//import com.google.gson.reflect.TypeToken;
//import cucumber.api.java.ru.И;
//import cucumber.api.java.ru.Когда;
//import cucumber.api.java.ru.Тогда;
//import entities.Category;
//import entities.CommonCategory;
//import entities.CurrentUser;
//import entities.User;
//import helpers.PfmMainPageHelper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.thrift.TException;
//import org.junit.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import ru.alfabank.alfatest.cucumber.api.AkitaScenario;
//import ru.alfabank.steps.DefaultSteps;
//import ru.alfabank.thrift.entities.Customer;
//import ru.alfabank.thrift.organizations.OrganizationsFilter;
//import ru.alfabank.thrift.organizations.OrganizationsProjection;
//import ru.alfabank.thrift.organizations.OrganizationsService;
//import utils.ResponseConverter;
//import utils.ThriftClients;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.codeborne.selenide.Selenide.$$;
//import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
//import static org.junit.Assert.*;
//import static ru.alfabank.tests.core.helpers.PropertyLoader.loadProperty;
//import static ru.alfabank.tests.core.helpers.PropertyLoader.loadSystemPropertyOrDefault;
//import static utils.GsonUtil.GSON_CONVERTER;
//import static utils.JavaScriptUtils.expandRootElement;
//
//@Slf4j
//public class TemplateSteps {
//
//    AkitaScenario akitaScenario = AkitaScenario.getInstance();
//    ResponseConverter responseConverter = new ResponseConverter();
//    PfmMainPageHelper pfmMainPageHelper = new PfmMainPageHelper();
//
//    @Когда("^выполнен переход на страницу \"([^\"]*)\" по ссылке из property файла \"([^\"]*)\" под пользователем \"([^\"]*)\"")
//    public void authorizationUser(String pageName, String pageUrl, String user) {
//        String pageURL = loadProperty(pageUrl);
//        String acus = loadProperty(user + ".acus");
//        String url = pageURL + "?profileId=" + acus;
//        DefaultSteps steps = new DefaultSteps();
//        steps.goToSelectedPageByLinkFromPropertyFile(pageName, pageUrl);
//        this.akitaScenario.setVar("user", user);
//        CurrentUser.setCurrentUser(new User(
//                CurrentUser.createUserDataFromProperties(user),
//                CurrentUser.createCustomerFromProperties(user)));
//    }
//
//    /**
//     * Проверка, что файл загрузился, по умолчанию файл сохраняется
//     * в папку Downloads/Загрузки (стандартную для ОС), чтобы сохранить файл
//     * в произвольное место, укажите в application.properties абсолютный путь
//     * до папки в переменной fileDownloadPath
//     * @param fileName  имя файла
//     */
//    @Тогда("^файл \"(.*)\" загрузился$")
//    public void testFileDownloaded(String fileName) {
//        String downloadsPath = System.getProperty("user.home") + "/Downloads";
//        File downloads = new File(loadSystemPropertyOrDefault("fileDownloadPath", downloadsPath));
//        File[] expectedFiles = downloads.listFiles((files, file) -> file.contains(fileName));
//        assertNotNull("Ошибка поиска файла", expectedFiles);
//        assertFalse("Файл не загрузился", expectedFiles.length == 0);
//        assertTrue(String.format("В папке присутствуют более одного файла с одинаковым назанием, содержащим текст [%s]", fileName),
//                expectedFiles.length == 1);
//        expectedFiles[0].delete();
//    }
//
//    /**
//     *  пример шага для вызова трифт-сервиса
//     * */
//    @Тогда("^список компаний на странице совпадает с возвращаемым списком компаний сервиса organizations-api$")
//    public void verifyOrganizationList() {
//        try {
//            User currentUser = CurrentUser.current();
//            OrganizationsService.Client client = ThriftClients.getOrganizationsClient();
//            List<Customer> organizationsList = client.getOrganizations(currentUser.getUserData(), (OrganizationsProjection)null, (OrganizationsFilter)null);
//            List<String> expectedCompanies = new ArrayList<>(organizationsList.size());
//            for (Customer customer : organizationsList) {
//                expectedCompanies.add(customer.getName());
//            }
//            List<SelenideElement> actualCompaniesSelenide = $$(By.xpath("//span[@class='corporate-company-select__option']"));
//            List<String> actualCompanies = new ArrayList<>(actualCompaniesSelenide.size());
//            for (SelenideElement company : actualCompaniesSelenide) {
//                actualCompanies.add(company.innerText());
//            }
//            log.info("\n\nActual: " + actualCompanies.toString() + "\nExpected: " + expectedCompanies.toString());
//            assertThat("Списки не совпадают", actualCompanies.containsAll(expectedCompanies));
//            this.akitaScenario.setVar("companies", expectedCompanies);
//        } catch (TException e) {
//            akitaScenario.write("ERROR:" + e.toString());
//        }
//    }
//
//    @Тогда("^в переменную \"([^\"]*)\" сохранен список категорий расходов из переменной \"([^\"]*)\" за месяцы \"(.*)\"$")
//    public void saveExpensesCategoriesFromRestResponse(String expensesCategories, String restResponse, String months) {
//        List<String> expectedMonths = GSON_CONVERTER.fromJson(months, new TypeToken<List<String>>() {
//        }.getType());
//        String body = akitaScenario.getVar(restResponse).toString();
//        List<Category> expensesCategoriesList = responseConverter
//                .getCategoriesByType(body, expectedMonths::contains);
//        akitaScenario.setVar(expensesCategories, expensesCategoriesList);
//    }
//
//    @SuppressWarnings("unchecked")
//    @Когда("^список категорий на главной странице соответствует REST ответу из переменной \"([^\"]*)\"$")
//    public void categoriesOnMainPageAreCorrect(String varName) {
//        List<Category> restCategories = (List<Category>) akitaScenario.getVar(varName);
//
//        List<CommonCategory> restCommonCategories = restCategories.stream()
//                .map(Category::convertRestCategoryIntoCommonCategory)
//                .collect(Collectors.toList());
//
//        List<CommonCategory> uiCommonCatFromMainPage = pfmMainPageHelper.getUserCategoriesAmountAndNamesFromMainPage();
//        Assert.assertEquals("Размер списка категорий с REST " + restCommonCategories.size() + " не совпадает с размером списка UI категорий " + uiCommonCatFromMainPage.size(), restCommonCategories.size(), uiCommonCatFromMainPage.size());
//        assertThat("Список категорий с REST " + restCommonCategories + "не соответствует списку категорий на главной странице" + uiCommonCatFromMainPage, restCommonCategories.toArray(), arrayContainingInAnyOrder(uiCommonCatFromMainPage.toArray()));
//    }
//
//    @И("^очищен кэш через настройки браузера Chrome$")
//    public void clearBrowserCache() throws InterruptedException {
//
//        getWebDriver().get("chrome://settings/clearBrowserData");
//
//        WebElement root1 = getWebDriver().findElement(By.tagName("settings-ui"));
//
//        //Get shadow root element
//        WebElement shadowRoot1 = expandRootElement(root1);
//
//        WebElement root2 = shadowRoot1.findElement(By.cssSelector("settings-main"));
//        WebElement shadowRoot2 = expandRootElement(root2);
//
//        WebElement root3 = shadowRoot2.findElement(By.cssSelector("settings-basic-page"));
//        WebElement shadowRoot3 = expandRootElement(root3);
//
//        WebElement root5 = shadowRoot3.findElement(By.cssSelector("settings-privacy-page"));
//        WebElement shadowRoot5 = expandRootElement(root5);
//
//        WebElement root6 = shadowRoot5.findElement(By.cssSelector("settings-clear-browsing-data-dialog"));
//        WebElement shadowRoot6 = expandRootElement(root6);
//
//        shadowRoot6.findElement(By.id("clearBrowsingDataConfirm")).click();
//
//    }
//
//
//}
