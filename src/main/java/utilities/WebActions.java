package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                ExtentTestUtility.getExtentTest().fail("Unable to perform click operation on element- "+element);
                throw new RuntimeException("Unable to perform click operation on element- "+element);
            }
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(element)).click();
                ExtentTestUtility.getExtentTest().pass("Clicked element- "+element);
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
                ExtentTestUtility.getExtentTest().fail("Failed entering text '"+text+"' in the element- "+element);
                throw new RuntimeException("Unable to perform enter text operation on element- "+element);
            }
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(text);
                ExtentTestUtility.getExtentTest().pass("Entered text '"+text+"' in the element- "+element);
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
                ExtentTestUtility.getExtentTest().fail("Unable to perform get text operation on element- "+element);
                throw new RuntimeException("Unable to perform get text operation on element- "+element);
            }
            try {
                text = wait.until(ExpectedConditions.presenceOfElementLocated(element)).getText();
                ExtentTestUtility.getExtentTest().pass("Fetched '"+text+"' from element- "+element);
                break;
            } catch (Exception e){
                currentRetryCnt ++;
            }
        }
        return text;
    }

    public String takeScreenshot(){
        //TC_004_<CurrentTimeStamp>.png
        String filePath = "/screenshots/"+ExtentTestUtility.getExtentTest().getModel().getName()+"_"+ LocalDateTime.now().minusDays(100).format(DateTimeFormatter.ofPattern("dd_MM_yy_hh_mm_ss")) +".png";
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(filePath));
        } catch (IOException e) {
            ExtentTestUtility.getExtentTest().info("Unable to take screenshot!");
            throw new RuntimeException(e);
        }
        return filePath;
    }

    public String takeBase64Screenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }


}
