package object.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private static WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    private static By signInButton = By.xpath("(//a[contains(text(),'Войти')])[1]");

    @Step("Нажать на кнопку \"Войти\"")
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
}
