package testCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestPrime {

    WebDriver driver;

    ExtentSparkReporter reporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    @BeforeSuite
    public void setUpReport(){
        reporter = new ExtentSparkReporter(new File("report/AutomationReport.html"));
        reporter.config().thumbnailForBase64(true);
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
    }

    @AfterSuite
    public void endReport(){
        extentReports.flush();
    }

    public TestPrime(){}

    @BeforeMethod
    public void setUp(Method m){
        ExtentLog.log(Status.INFO, "Starting test-"+m.getName());
//        extentTest = extentReports.createTest(m.getName());
        //Sets ExtentTest object into a thread using ExtentTestUtility
        //We are sending a car of company ExtentTest and on the driver seat putting test method's name
        ExtentTestUtility.setExtentTest(extentReports.createTest(m.getName()));
//        File file = new File("assets/config.properties");
//        Props props = new Props(file);
//        String browserName = props.getKey("browser");
        String browserName = GlobalProperties.getProperty("browser");
        if(browserName.equalsIgnoreCase("firefox")) {
//            driver = new FirefoxDriver();
            BrowserFactory.setDriver(new FirefoxDriver());
            driver = BrowserFactory.getDriver();
        }
        else
        {
            ChromeOptions crOptions = new ChromeOptions();
            crOptions.addArguments("--remote-allow-origins=*");
//            driver = new ChromeDriver(crOptions);
            BrowserFactory.setDriver(new ChromeDriver(crOptions));
            driver = BrowserFactory.getDriver();
        }
        BrowserFactory.getDriver().get(GlobalProperties.getProperty("url"));
        BrowserFactory.getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult itr){
        System.out.println("After method");
        if(itr.getStatus() == 1) {
            //  ExtentTestUtility.getExtentTest().pass("Test Passed");
            ExtentLog.log(Status.PASS, "Test Passed");
        }
        else {
          //  ExtentTestUtility.getExtentTest().fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(new WebActions(BrowserFactory.getDriver()).takeBase64Screenshot()).build());
            ExtentLog.log(Status.FAIL, "Test Failed");
          //  ExtentLog.log(Status.INFO, Arrays.toString(Thread.currentThread().getStackTrace())); // need to ecplore
        }
        //BrowserFactory.getDriver().quit();
    }

    @DataProvider(name = "myData", parallel = true)
    public Object[][] getData(Method m){
        String colName = "Test Case ID";
        System.out.println(m.getName());

        ExcelUtility exc = new ExcelUtility("assets/data.xlsx", 0);
        List<Integer> colNumbers = exc.getColNumbersInRowHavingText(colName, exc.getHeaderRow());
        System.out.println(colNumbers);

        List<Integer> rowNums = exc.getRowNumbersInColumnHavingText(m.getName(), colNumbers.get(0)); //why column zero? - multiple data for different login for example
        System.out.println(rowNums);
        Object[][] data = new Object[rowNums.size()][1]; //here column size 1 meaning 0,1? but why 2 and why 1? i think only 1
        for(int i = 0; i< rowNums.size(); i++){
            data[i][0] = exc.getRowData(rowNums.get(i));
        }
        return data;
    }
}
