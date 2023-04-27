package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WebActions;

public class HomePage {

    By pageTitle = By.className("title");

    By openMenu = By.xpath("//button[text()='Open Menu']");

    By logout = By.xpath("//a[text()='Logout']");
    private WebDriver driver;
    private WebActions act;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        act = new WebActions(driver);
    }

    public String getPageTitle() {
        return act.doGetText(pageTitle);
    }
    public void openMenuOptions(){
         act.doClick(openMenu);
    }
    public void logout(){
        act.doJSClick(logout);
    }
}
