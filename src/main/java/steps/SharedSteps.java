package steps;

import object.page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static constants.Credentials.EMAIL;
import static constants.Credentials.PASSWORD;
import static constants.URL.BASE_URL;

public class SharedSteps {
    private static WebDriver driver;

    public SharedSteps(WebDriver driver) {
        this.driver = driver;
    }

    public static void startChromeBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        WebDriver driver = new ChromeDriver(options);
        driver.get(BASE_URL);
    }

    public static void authorizeTestUser() {
        LoginPage lp = new LoginPage(driver);
        driver.findElement(lp.getFieldEmail()).sendKeys(EMAIL);
        driver.findElement(lp.getFieldPassword()).sendKeys(PASSWORD);
        driver.findElement(lp.getSignInButton()).click();
    }
}
