import api.client.SharedApiSteps;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static constants.URL.LOGIN_PAGE;
import static constants.URL.REGISTER_PAGE;

public class RegisterTest {
    private WebDriver driver;
    private User user;
    private RegisterPage rp;
    private static Faker faker = new Faker();

    @Before
    public void setUp(){
        driver = WebDriverFactory.getWebDriver("chrome");
        rp = new RegisterPage(driver);
        user = new User();

        user.setName(faker.name().fullName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6,9));

        SharedApiSteps.setUp();

        driver.get(REGISTER_PAGE);
    }

    @Test
    @DisplayName("Регистрация нового пользователя")
    @Description("Регистрация нового пользователя")
    public void registerTest() {
        rp.waitForLoaderDisappear();
        rp.fillRegisterFields(user.getName(), user.getEmail(), user.getPassword());
        rp.clickRegisterButton();
        new WebDriverWait(driver, Duration.ofMillis(5000)).until(ExpectedConditions.urlContains(LOGIN_PAGE));
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(LOGIN_PAGE, actualURL);
    }

    @After
    public void teardown() {
        driver.quit();

        //Условие, чтобы удалить пользователя
        String token = SharedApiSteps.acquireToken(user);
        if(token != null) {
            user.setAccessToken(token);
            SharedApiSteps.deleteUser(user);
        }
    }
}
