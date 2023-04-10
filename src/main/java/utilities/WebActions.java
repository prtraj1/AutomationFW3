package utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WebActions {

    private WebDriver driver;

    private WebDriverWait wait;

    private int maxRetryCount = 2;

    public WebActions(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    public void doClick(By element){ //Explain this line
        int currentRetryCnt = 0;
        while (true){
            if(currentRetryCnt > maxRetryCount){
//                ExtentTestUtility.getExtentTest().fail("Unable to perform click operation on element- "+element);
                ExtentLog.log(Status.FAIL, "Unable to perform click operation on element- "+element);
                throw new RuntimeException("Unable to perform click operation on element- "+element);
            }
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(element)).click();
//                ExtentTestUtility.getExtentTest().pass("Clicked element- "+element);
                ExtentLog.log(Status.PASS, "Clicked element- "+element);
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
//                ExtentTestUtility.getExtentTest().fail("Failed entering text '"+text+"' in the element- "+element);
                ExtentLog.log(Status.FAIL, "Failed entering text '"+text+"' in the element- "+element);
                throw new RuntimeException("Unable to perform enter text operation on element- "+element);
            }
            try {
                wait.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(text);
//                ExtentTestUtility.getExtentTest().pass("Entered text '"+text+"' in the element- "+element);
                ExtentLog.log(Status.PASS, "Entered text '"+text+"' in the element- "+element);
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
//                ExtentTestUtility.getExtentTest().fail("Unable to perform get text operation on element- "+element);
                ExtentLog.log(Status.FAIL, "Unable to perform get text operation on element- "+element);
                throw new RuntimeException("Unable to perform get text operation on element- "+element);
            }
            try {
                text = wait.until(ExpectedConditions.presenceOfElementLocated(element)).getText();
//                ExtentTestUtility.getExtentTest().pass("Fetched '"+text+"' from element- "+element);
                ExtentLog.log(Status.PASS, "Fetched '"+text+"' from element- "+element);
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
//            ExtentTestUtility.getExtentTest().info("Unable to take screenshot!");
            ExtentLog.log(Status.INFO, "Unable to take screenshot!");
            throw new RuntimeException(e);
        }
        return filePath;
    }

    public String takeBase64Screenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }

    public List<WebElement> validateList(By elements){
        int currentRetryCnt = 0;
        while(true){
            if(currentRetryCnt>maxRetryCount){
                ExtentLog.log(Status.FAIL, "Unable to find list of: "+elements);
                throw new RuntimeException("Unable to fetch the list: " +elements);
            }
            try {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elements));
                List<WebElement> elm = driver.findElements(elements);
                ExtentLog.log(Status.PASS, "Fetched the list");
                return elm;
            }
            catch (Exception e)
            {
               currentRetryCnt++;
            }
        }
    }

    public void selectByValue(By element, String value){
        int currentRetryCnt = 0;
        while (true){
            if(currentRetryCnt > maxRetryCount){
//                ExtentTestUtility.getExtentTest().fail("Failed entering text '"+text+"' in the element- "+element);
                ExtentLog.log(Status.FAIL, "Failed selecting value in the element- "+element);
                throw new RuntimeException("Unable to perform select operation on the element- "+element);
            }
            try {
                new Select(wait.until(ExpectedConditions.presenceOfElementLocated(element))).selectByValue(value);
                ExtentLog.log(Status.PASS, "Selected value: " +value + " in the drop down- " +element);
                break;
            } catch (Exception e){
                currentRetryCnt ++;
            }
        }
    }

    public void selectByText(By element, String value){
        int currentRetryCnt = 0;
        while (true){
            if(currentRetryCnt > maxRetryCount){
//                ExtentTestUtility.getExtentTest().fail("Failed entering text '"+text+"' in the element- "+element);
                ExtentLog.log(Status.FAIL, "Failed selecting value in the element- "+element);
                throw new RuntimeException("Unable to perform select operation on the element- "+element);
            }
            try {
                new Select(wait.until(ExpectedConditions.presenceOfElementLocated(element))).selectByVisibleText(value);
                ExtentLog.log(Status.PASS, "Selected value: " +value + " in the drop down- " +element);
                break;
            } catch (Exception e){
                currentRetryCnt ++;
            }
        }
    }

}
