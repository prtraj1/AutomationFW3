package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WebActions;

public class LoginPage {

    By usernameTextBox = By.id("user-name");
    By pwdTxtBox = By.id("password");
    By loginBtn = By.id("login-button");

    By errorMsg = By.xpath("//h3[@data-test=\"error\"]");
    private WebDriver driver;
    private WebActions act;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        act = new WebActions(driver);
    }

    public void login(String userName, String password) {
        act.doEnterText(usernameTextBox, userName);
        act.doEnterText(pwdTxtBox, password);
        act.doClick(loginBtn);
    }

    public boolean validateErrorMsg(String msg){
        return msg.equals(act.doGetText(errorMsg));
    }
}
