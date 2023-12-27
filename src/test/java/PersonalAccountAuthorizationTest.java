import api.client.SharedApiSteps;
import api.client.user.body.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import object.page.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static api.client.user.constants.Credentials.*;
import static constants.URL.*;

public class PersonalAccountAuthorizationTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private User user;
    private HomePage hp;
    private LoginPage lp;
    private RegisterPage rp;
    private ForgotPasswordPage fsp;
    private PersonalAccountPage pap;
    private HeaderSharedElements hse;

    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver("chrome");
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        hp = new HomePage(driver);
        lp = new LoginPage(driver);
        rp = new RegisterPage(driver);
        fsp = new ForgotPasswordPage(driver);
        pap = new PersonalAccountPage(driver);
        hse = new HeaderSharedElements(driver);
        user = new User();

        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPassword(PASSWORD);

        SharedApiSteps.setUp();
        SharedApiSteps.createUser(user);

        driver.get(BASE_URL);
    }

    @Test
    @DisplayName("Авторизация по кнопке \"Войти в аккаунт\"")
    @Description("Авторизация по кнопке \"Войти в аккаунт\"")
    public void authorizeBySignInButtonTest() {
        hp.clickSignInButton();
        lp.authorizeTestUser();

        Assert.assertTrue(hp.createOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Авторизация по кнопке \"Личный кабинет\"")
    @Description("Авторизация по кнопке \"Личный кабинет\"")
    public void authorizeByPersonalAccountButtonTest() {
        hp.clickPersonalAccountButton();
        lp.authorizeTestUser();

        Assert.assertTrue(hp.createOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Авторизация со странички регистрации")
    @Description("Авторизация со странички регистрации")
    public void authorizeFromRegisterPageTest() {
        driver.get(REGISTER_PAGE);

        rp.clickSignInButton();
        lp.authorizeTestUser();

        Assert.assertTrue(hp.createOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Авторизация со странички восстановления пароля")
    @Description("Авторизация со странички восстановления пароля")
    public void authorizeFromForgotPasswordPageTest() {
        driver.get(FORGOT_PASSWORD_PAGE);

        fsp.clickSignInButton();
        lp.authorizeTestUser();

        Assert.assertTrue(hp.createOrderButtonIsVisible());
    }

    @Test
    @DisplayName("Выход из личного кабинета по кнопке \"Выход\"")
    @Description("Выходим из личного кабинета")
    public void logoutTest() {

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
        user.setAccessToken(SharedApiSteps.acquireToken(user));
        SharedApiSteps.deleteUser(user);
    }
}