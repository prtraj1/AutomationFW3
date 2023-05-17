package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.*;
import utilities.*;

import java.io.File;

public class MyTestNGListener implements ISuiteListener, ITestListener {

    WebDriver driver;
    ExtentSparkReporter reporter;
    ExtentReports extentReports;

    @Override
    public void onStart(ISuite suite) {
        reporter = new ExtentSparkReporter(new File("report/AutomationReport.html"));
        reporter.config().thumbnailForBase64(true);
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
    }

    @Override
    public void onFinish(ISuite suite) {
        extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentLog.log(Status.INFO, "Starting test-"+result.getName());
        LogMessage.info("Starting test-"+result.getName());
        ExtentTestUtility.setExtentTest(extentReports.createTest(result.getName()));
        String browserName = GlobalProperties.getProperty("browser");
        if(browserName.equalsIgnoreCase("firefox")) {
            BrowserFactory.setDriver(new FirefoxDriver());
            driver = BrowserFactory.getDriver();
        }
        else
        {
            ChromeOptions crOptions = new ChromeOptions();
            crOptions.addArguments("--remote-allow-origins=*");
            BrowserFactory.setDriver(new ChromeDriver(crOptions));
            driver = BrowserFactory.getDriver();
        }
        BrowserFactory.getDriver().get(GlobalProperties.getProperty("url"));
        BrowserFactory.getDriver().manage().window().maximize();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLog.log(Status.PASS, "Test Passed");
        BrowserFactory.getDriver().quit();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLog.log(Status.FAIL, "Test Failed");
        ExtentLog.log(Status.FAIL, result.getThrowable().toString());
        BrowserFactory.getDriver().quit();
    }
}
