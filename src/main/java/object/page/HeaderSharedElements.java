package object.page;

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

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogoButton() {
        driver.findElement(constructorButton).click();
    }
}
