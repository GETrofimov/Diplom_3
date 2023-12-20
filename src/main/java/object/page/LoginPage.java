package object.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static api.client.user.constants.Credentials.EMAIL;
import static api.client.user.constants.Credentials.PASSWORD;

public class LoginPage {
    private static WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private static By registerButton = By.cssSelector("[href='/register']");
    private static By signInButton = By.xpath("(//button[contains(text(),'Войти')])[1]");
    private static By fieldEmail = By.xpath("(//input[@name='name'])[1]");
    private static By fieldPassword = By.xpath("(//input[@name='Пароль'])[1]");

    @Step("Нажать на кнопку \"Зарегистрироваться\"")
    public static void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажать на кнопку \"Войти\"")
    public static void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    @Step("Заполнить поле \"Email\"")
    public static void setFieldEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Заполнить поле \"Пароль\"")
    public static void setFieldPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Авторизоваться под тестовым пользователем")
    public void authorizeTestUser() {
        setFieldEmail(EMAIL);
        setFieldPassword(PASSWORD);
        clickSignInButton();
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
