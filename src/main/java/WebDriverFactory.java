import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static constants.YandexPath.*;

public class WebDriverFactory {

    public static WebDriver getWebDriver(String browser) {
        switch (browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments();
                return new ChromeDriver(options);
            case "yandex":
                System.setProperty("webdriver.yandex.driver", DRIVER_PATH);
                ChromeOptions opt = new ChromeOptions();
                opt.setBinary(EXE_PATH);
                return new ChromeDriver(opt);
        }
        return new ChromeDriver();
    }
}
