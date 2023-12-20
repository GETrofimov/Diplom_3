package object.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private static By signInButton = By.xpath("(//button[contains(text(),'Войти в аккаунт')])[1]");
    private static By loader = By.xpath("(//img[@alt='loading animation'])[1]");
    private static By createOrderButtonSelector = By.xpath("(//button[contains(text(),'Оформить заказ')])[1]");
    private static By personalAccountButton = By.xpath("(//p[contains(text(),'Личный Кабинет')])[1]");
    private static By sauceOption = By.xpath("(//h2[contains(text(),'Соусы')])[1]");
    private static By bunsOption = By.xpath("(//h2[contains(text(),'Булки')])[1]");
    private static By fillingOption = By.xpath("(//h2[contains(text(),'Начинки')])[1]");
    private static By sauceButton = By.xpath("(//span[contains(text(),'Соусы')])[1]");
    private static By bunsButton = By.xpath("(//span[contains(text(),'Булки')])[1]");
    private static By fillingButton = By.xpath("(//span[contains(text(),'Начинки')])[1]");

    @Step("Нажать на кнопку \"Войти в акаунт\"")
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    @Step("Нажать на кнопку \"Личный кабинет\"")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажать на кнопку \"Соусы\"")
    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    @Step("Нажать на кнопку \"Булки\"")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Нажать на кнопку \"Начинки\"")
    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public boolean createOrderButtonIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButtonSelector));
        return driver.findElement(createOrderButtonSelector).isDisplayed();
    }

    public boolean sauceOptionIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(sauceOption));
        return driver.findElement(sauceOption).isDisplayed();
    }

    public boolean bunsOptionIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsOption));
        return driver.findElement(bunsOption).isDisplayed();
    }

    public boolean fillingOptionIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingOption));
        return driver.findElement(fillingOption).isDisplayed();
    }

    public static By getSauceOption() {
        return sauceOption;
    }

    public static By getBunsOption() {
        return bunsOption;
    }

    public static By getFillingOption() {
        return fillingOption;
    }
}
