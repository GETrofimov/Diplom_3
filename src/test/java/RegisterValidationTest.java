import api.client.BaseTest;
import api.client.user.body.User;
import com.github.javafaker.Faker;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.URL.REGISTER_PAGE;
import static object.page.RegisterPage.passwordValidationWebElement;

public class RegisterValidationTest {
    private WebDriver driver;
    private User user;
    private RegisterPage rp;
    private static Faker faker = new Faker();

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

        BaseTest.setUp();

        //Присваиваем данные, чтобы в дальнейшем удалить юзера, если валидация провалена и создастся экземпляр
        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(5,6));

        driver.get(REGISTER_PAGE);
    }

    @Test
    @DisplayName("Регистрация нового пользователя с неправильным паролем")
    @Description("Регистрация нового пользователя")
    public void registerInvalidPasswordTest() {
        rp.waitForLoaderDisappear();
        rp.fillRegisterFields(user.getName(), user.getEmail(), user.getPassword());
        rp.clickRegisterButton();
        Assert.assertTrue(passwordValidationWebElement().isDisplayed());
}

    @After
    public void teardown() {
        driver.quit();

        //Условие для удаления юзера
        String token = BaseTest.acquireToken(user);
        if(token != null) {
            user.setAccessToken(token);
            BaseTest.deleteUser(user);
        }
    }
}
