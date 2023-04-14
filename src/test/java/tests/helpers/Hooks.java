package tests.helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import selenide.CustomAllureSelenide;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
/**
 * Класс используется для задания действия, которые нужно выполнять до или после тестов
 *
 * @author Горячев Роман Юрьевич
 */
public class Hooks {
    /**
     * Метод для задания параметров вебдрайвера
     *
     * @author Горячев Роман Юрьевич
     */
    @Before()
    public void chromeSettings(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions", "--start-maximized", "--remote-allow-origins=*", "webdriver.http.factory", "jdk-http-client"
                , "--no-sandbox", "disable-infobars", "--start-maximized", "--single-process'",
                "--disable-dev-shm-usage", "--disable-blink-features=AutomationControlled", "log-level=3");
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        WebDriver driver;
        driver = new ChromeDriver(options);
        setWebDriver(driver);
        Configuration.timeout=20000;

    }
    /**
     * Метод для включения механизма снятия скриншотов после каждого действия
     *
     * @author Горячев Роман Юрьевич
     */
    @Before()
    public static void setup(){
        SelenideLogger.addListener("AllureSelenide", new CustomAllureSelenide().screenshots(true));
    }
    /**
     * Метод для закрытия браузера после прохождения сценариев
     *
     * @author Горячев Роман Юрьевич
     */
    @After
    public void closeBrowser(){
        Selenide.closeWebDriver();
    }
}
