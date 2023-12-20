package object.page;

import api.client.user.body.User;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static api.client.user.constants.Credentials.EMAIL;
import static api.client.user.constants.Credentials.NAME;

public class RegisterPage {
    private static WebDriver driver;

    private static Faker faker = new Faker();

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private static By registerButton = By.className("button_button__33qZ0");
    private static By fieldName = By.xpath("(//input[@name='name'])[1]");
    private static By fieldEmail = By.xpath("(//input[@name='name'])[2]");
    private static By fieldPassword = By.xpath("(//input[@name='Пароль'])[1]");
    private static By loader = By.className("Modal_modal__loading__3534A");
    private static By signInButton = By.xpath("(//a[contains(text(),'Войти')])[1]");
    private static By passwordValidationSelector = By.xpath("(//div[@class='input pr-6 pl-6 input_type_password input_size_default input_status_error'])[1]");

    @Step("Нажать на кнопку \"Зарегистрироваться\"")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажать на кнопку \"Войти в аккаунт\"")
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    @Step("Заполнение кредов")
    public void fillRegisterFields(User user) {
        setFieldName(NAME);
        setFieldEmail(EMAIL);
        setFieldPassword(faker.internet().password(6, 9));
    }

    @Step("Заполнение кредов с неверной длинной пароля")
    public void fillRegisterFieldsInvalidPassword(User user) {
        setFieldName(NAME);
        setFieldEmail(EMAIL);
        setFieldPassword(faker.internet().password(5,6));
    }

    @Step("Заполнить поле \"Имя\"")
    public void setFieldName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    @Step("Заполнить поле \"Email\"")
    public void setFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Заполнить поле \"Пароль\"")
    public void setFieldPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Ожидание, пока пропадет лоадер")
    public void waitForLoaderDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public static WebElement passwordValidationWebElement() {
        return driver.findElement(passwordValidationSelector);
    }
}
