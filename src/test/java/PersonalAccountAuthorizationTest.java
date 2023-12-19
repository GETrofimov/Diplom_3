import constants.GeckoWebDriverPath;
import object.page.*;
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

import static constants.URL.*;
import static object.page.RegisterPage.*;

public class PersonalAccountAuthorizationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
//    public void startBrowser() {
//        System.setProperty("webdriver.gecko.driver", GeckoWebDriverPath.PATH);
//        driver = new FirefoxDriver();
//        driver.get(BASE_URL);
//
//    }

    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        driver.get(BASE_URL);
    }

    @Test
    public void AuthorizeBySignInButtonTest() {
        HomePage hp = new HomePage(driver);
        hp.clickSignInButton();

        LoginPage lp = new LoginPage(driver);
        lp.authorizeTestUser();

        Assert.assertTrue(hp.createOrderButtonIsVisible());
    }

    @Test
    public void AuthorizeByPersonalAccountButtonTest() {
        HomePage hp = new HomePage(driver);
        hp.clickPersonalAccountButton();

        LoginPage lp = new LoginPage(driver);
        lp.authorizeTestUser();

        Assert.assertTrue(hp.createOrderButtonIsVisible());
    }

    @Test
    public void AuthorizeFromRegisterPageTest() {
        RegisterPage rp = new RegisterPage(driver);
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        driver.get(REGISTER_PAGE);

        rp.clickSignInButton();
        lp.authorizeTestUser();

        Assert.assertTrue(hp.createOrderButtonIsVisible());
    }

    @Test
    public void AuthorizeFromForgotPasswordPageTest() {
        ForgotPasswordPage fsp = new ForgotPasswordPage(driver);
        LoginPage lp = new LoginPage(driver);
        HomePage hp = new HomePage(driver);
        driver.get(FORGOT_PASSWORD_PAGE);

        fsp.clickSignInButton();
        lp.authorizeTestUser();

        Assert.assertTrue(hp.createOrderButtonIsVisible());
    }

    @Test
    public void logoutTest() {
        HomePage hp = new HomePage(driver);
        LoginPage lp = new LoginPage(driver);
        PersonalAccountPage pap = new PersonalAccountPage(driver);
        HeaderSharedElements hse = new HeaderSharedElements(driver);

        hp.clickPersonalAccountButton();
        lp.authorizeTestUser();
        hse.clickPersonalAccountButton();
        wait.until(ExpectedConditions.urlContains(PERSONAL_ACCOUNT_PAGE));
        pap.clickLogoutButton();
        wait.until(ExpectedConditions.urlContains(LOGIN_PAGE));

        Assert.assertEquals(LOGIN_PAGE, driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}