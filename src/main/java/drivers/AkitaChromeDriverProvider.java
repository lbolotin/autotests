package drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Hashtable;
import java.util.Map;

import static ru.alfabank.tests.core.helpers.PropertyLoader.loadSystemPropertyOrDefault;


/**
 *  Используется для создания хром-браузера с пользовательскими настройками
 *  ("profile.default_content_settings.popups", 0) - блокирует всплывающие окна
 *  ("download.prompt_for_download", "false") - выключает подтверждение (и выбор пути) для загрузки файла
 *  ("download.default_directory", ...) - устанавливает стандартную папку загрузки файлов
 *  "plugins.plugins_disabled", new String[]{
 *       "Adobe Flash Player", "Chrome PDF Viewer" - выключает плагины
 *
 *  Чтобы задать папку для загрузки файлов пропишите абсолютный путь
 *  в fileDownloadPath в application.properties
 *
 *  Полный список параметров, которые можно установить таким способом:
 *  https://sites.google.com/a/chromium.org/chromedriver/capabilities
 */
public class AkitaChromeDriverProvider implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        Map<String, Object> preferences = new Hashtable<String, Object>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        String downloadsPath = System.getProperty("user.home") + "/Downloads";
        preferences.put("download.default_directory", loadSystemPropertyOrDefault("fileDownloadPath", downloadsPath));
        preferences.put("plugins.plugins_disabled", new String[]{
                "Adobe Flash Player", "Chrome PDF Viewer"});
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);

        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return new ChromeDriver(capabilities);
    }
}
