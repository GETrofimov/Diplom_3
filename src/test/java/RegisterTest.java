import object.page.RegisterPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.URL.LOGIN_PAGE;
import static constants.URL.REGISTER_PAGE;
import static object.page.RegisterPage.passwordValidationWebElement;

public class RegisterTest {
    private WebDriver driver;
    @Before
//    public void startBrowser() {
//        System.setProperty("webdriver.gecko.driver", GeckoWebDriverPath.PATH);
//        driver = new FirefoxDriver();
//        driver.get(REGISTER_PAGE);
//
//    }

    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments();
        driver = new ChromeDriver(options);
        driver.get(REGISTER_PAGE);
    }

    @Test
    public void registerTest() {
        RegisterPage rp = new RegisterPage(driver);
        rp.waitForLoaderDisappear();
        rp.fillRegisterFields();
        rp.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofMillis(5000)).until(ExpectedConditions.urlContains(LOGIN_PAGE));
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(LOGIN_PAGE, actualURL);
    }

    @Test
    public void registerInvalidPasswordTest() {
        RegisterPage rp = new RegisterPage(driver);
        rp.fillRegisterFieldsInvalidPassword();
        rp.clickRegisterButton();
        Assert.assertTrue(passwordValidationWebElement().isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
