import api.client.BaseTest;
import api.client.user.body.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import object.page.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.SharedSteps;

import java.time.Duration;

import static constants.URL.*;

public class NavigationTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private HeaderSharedElements hse;
    private SharedSteps ss;
    private HomePage hp;
    private User user = new User();

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments();
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(3000));
        driver.get(LOGIN_PAGE);
        ss = new SharedSteps(driver);
        hse = new HeaderSharedElements(driver);
        hp = new HomePage(driver);
        BaseTest.createUser(user);
        ss.authorizeTestUser();
        System.out.println(user.getName());
        System.out.println(user.getAccessToken());
    }

    @Test
    @DisplayName("Переход в личный кабинет по кнопке \"Личный кабинет\"")
    @Description("Получаем заказы пользователя")
    public void openPersonalAccountByPersonalAccountButtonTest() {
        hse.clickPersonalAccountButton();
        wait.until(ExpectedConditions.urlContains(PERSONAL_ACCOUNT_PAGE));

        Assert.assertEquals(PERSONAL_ACCOUNT_PAGE, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета по кнопке \"Конструктор\"")
    @Description("Переход в конструктор из личного кабинета")
    public void openConstructorFromPersonalAccountByConstructorButtonTest() {
        hse.clickPersonalAccountButton();
        wait.until(ExpectedConditions.urlContains(PERSONAL_ACCOUNT_PAGE));
        hse.clickConstructorButton();
        wait.until(ExpectedConditions.urlContains(BASE_URL));

        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета по кнопке лого")
    @Description("Переход в конструктор из личного кабинета")
    public void openConstructorFromPersonalAccountByLogoTest() {
        hse.clickPersonalAccountButton();
        wait.until(ExpectedConditions.urlContains(PERSONAL_ACCOUNT_PAGE));
        hse.clickLogoButton();
        wait.until(ExpectedConditions.urlContains(BASE_URL));

        Assert.assertEquals(BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход к разделу \"Соусы\"")
    @Description("Переход к разделу \"Соусы\"")
    public void slideToSauceTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(hp.getBunsOption()));
        hp.clickSauceButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(hp.getSauceOption()));
        Assert.assertTrue(hp.sauceOptionIsVisible());
    }

    @Test
    @DisplayName("Переход к разделу \"Начинки\"")
    @Description("Переход к разделу \"Начинки\"")
    public void slideToFillingTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(hp.getBunsOption()));
        hp.clickFillingButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(hp.getFillingOption()));
        Assert.assertTrue(hp.fillingOptionIsVisible());
    }

    @Test
    @DisplayName("Переход к разделу \"Булки\"")
    @Description("Переход к разделу \"Булки\"")
    public void slideToBunsTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(hp.getBunsOption()));
        hp.clickSauceButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(hp.getSauceOption()));
        hp.clickBunsButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(hp.getBunsOption()));
        Assert.assertTrue(hp.bunsOptionIsVisible());
    }


    @After
    public void teardown() {
        driver.quit();
        BaseTest.deleteUser(user);
    }
}
