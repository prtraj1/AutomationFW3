package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WebActions;

public class HomePage {

    By pageTitle = By.className("title");
    private WebDriver driver;
    private WebActions act;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        act = new WebActions(driver);
    }

    public String getPageTitle() {
        return act.doGetText(pageTitle);
    }

}
