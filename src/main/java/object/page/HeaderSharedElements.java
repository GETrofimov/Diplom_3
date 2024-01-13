package object.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderSharedElements {
    WebDriver driver;

    public HeaderSharedElements(WebDriver driver) {
        this.driver = driver;
    }

    private static By personalAccountButton = By.xpath("//a[@href='/account']");
    private static By constructorButton = By.xpath("(//p[contains(text(),'Конструктор')])[1]");
    private static By logoButton = By.xpath("//a[@href='/']");

    @Step("Нажать на кнопку \"Личный кабинет\"")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Нажать на кнопку \"Конструктор\"")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать на кнопку лого \"Stellar Burgers\"")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }
}
