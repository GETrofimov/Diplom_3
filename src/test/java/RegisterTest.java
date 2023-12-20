import api.client.BaseTest;
import api.client.user.body.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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
    private User user;
    private RegisterPage rp;
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
        rp = new RegisterPage(driver);
        user = new User();
        driver.get(REGISTER_PAGE);
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Description("Регистрация нового пользователя")
    public void registerTest() {
        rp.waitForLoaderDisappear();
        rp.fillRegisterFields(user);
        rp.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofMillis(5000)).until(ExpectedConditions.urlContains(LOGIN_PAGE));
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(LOGIN_PAGE, actualURL);
    }

    @Test
    @DisplayName("Регистрация нового пользователя с паролем меньше 6")
    @Description("Регистрация нового пользователя с паролем меньше 6")
    public void registerInvalidPasswordTest() {
        rp.fillRegisterFieldsInvalidPassword(user);
        rp.clickRegisterButton();
        Assert.assertTrue(passwordValidationWebElement().isDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
        BaseTest.deleteUser(user);
    }
}
