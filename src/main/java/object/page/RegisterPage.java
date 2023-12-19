package object.page;

import com.github.javafaker.Faker;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public static void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public static void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
    public static void fillRegisterFields() {
        setFieldName(faker.name().fullName());
        setFieldEmail(faker.internet().emailAddress());
        setFieldPassword(faker.internet().password(6, 9));
    }

    public static void fillRegisterFieldsInvalidPassword() {
        setFieldName(faker.name().fullName());
        setFieldEmail(faker.internet().emailAddress());
        setFieldPassword(faker.internet().password(5,6));
    }

    public static void setFieldName(String name) {
        driver.findElement(fieldName).click();
        driver.findElement(fieldName).sendKeys(name);
    }

    public static void setFieldEmail(String email) {
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public static void setFieldPassword(String password) {
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public static void waitForLoaderDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public static WebElement passwordValidationWebElement() {
        return driver.findElement(passwordValidationSelector);
    }
}
