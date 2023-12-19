package object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static constants.Credentials.EMAIL;
import static constants.Credentials.PASSWORD;

public class LoginPage {
    private static WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private static By registerButton = By.cssSelector("[href='/register']");
    private static By signInButton = By.xpath("(//button[contains(text(),'Войти')])[1]");
    private static By fieldEmail = By.xpath("(//input[@name='name'])[1]");
    private static By fieldPassword = By.xpath("(//input[@name='Пароль'])[1]");

    public static void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    public static void setFieldEmail(String email) {
        driver.findElement(fieldEmail).click();
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public static void setFieldPassword(String password) {
        driver.findElement(fieldPassword).click();
        driver.findElement(fieldPassword).sendKeys(password);
    }

    public static void authorizeTestUser() {
        driver.findElement(fieldEmail).sendKeys(EMAIL);
        driver.findElement(fieldPassword).sendKeys(PASSWORD);
        driver.findElement(signInButton).click();
    }

    public static By getFieldEmail() {
        return fieldEmail;
    }

    public static By getFieldPassword() {
        return fieldPassword;
    }

    public static By getSignInButton() {
        return signInButton;
    }
}
