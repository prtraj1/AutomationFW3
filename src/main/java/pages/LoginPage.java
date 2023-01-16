package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    private WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    By usernameTextBox = By.id("user-name");
    By pwdTxtBox = By.id("password");
    By loginBtn = By.id("login-button");

    public void login(String userName, String password){
         wait.until(ExpectedConditions.presenceOfElementLocated(usernameTextBox)).sendKeys(userName);
         wait.until(ExpectedConditions.presenceOfElementLocated(pwdTxtBox)).sendKeys(password);
         wait.until(ExpectedConditions.presenceOfElementLocated(loginBtn)).click();
    }
}
