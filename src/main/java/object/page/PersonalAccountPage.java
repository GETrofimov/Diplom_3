package object.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private static WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private static By logoutButton = By.xpath("(//button[contains(text(),'Выход')])[1]");

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
