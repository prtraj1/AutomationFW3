package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebActions {

    private WebDriver driver;

    private WebDriverWait wait;

    private int maxRetryCount = 2;

    public WebActions(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void doClick(By element){
        int currentRetryCnt = 0;
        while (true){
            if(currentRetryCnt > maxRetryCount){
                throw new RuntimeException("Unable to perform click operation on element- "+element);
            }
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(element)).click();
                break;
            } catch (Exception e){
                currentRetryCnt ++;
            }
        }
    }

    public void doEnterText(By element, String text){
        int currentRetryCnt = 0;
        while (true){
            if(currentRetryCnt > maxRetryCount){
                throw new RuntimeException("Unable to perform enter text operation on element- "+element);
            }
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(text);
                break;
            } catch (Exception e){
                currentRetryCnt ++;
            }
        }
    }

    public String doGetText(By element){
        int currentRetryCnt = 0;
        String text = "";
        while (true){
            if(currentRetryCnt > maxRetryCount){
                throw new RuntimeException("Unable to perform get text operation on element- "+element);
            }
            try {
                text = wait.until(ExpectedConditions.presenceOfElementLocated(element)).getText();
                break;
            } catch (Exception e){
                currentRetryCnt ++;
            }
        }
        return text;
    }


}
