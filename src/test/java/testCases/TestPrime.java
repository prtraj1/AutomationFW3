package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ExcelUtility;
import utilities.Props;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestPrime {

    WebDriver driver;

    public TestPrime(){}

    @BeforeMethod
    public void setUp(){
        File file = new File("assets/config.properties");
        Props props = new Props(file);
        String browserName = props.getKey("browser");
        if(browserName.equalsIgnoreCase("firefox"))
            driver = WebDriverManager.firefoxdriver().create();
        else
            driver = WebDriverManager.chromedriver().create();
        driver.get(props.getKey("url"));
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void tearDown(){
        System.out.println("After method");
        driver.quit();
    }

    @DataProvider(name = "myData")
    public Object[][] getData(Method m){
        String colName = "Test Case ID";
        System.out.println(m.getName());

        ExcelUtility exc = new ExcelUtility("assets/data.xlsx", 0);
        List<Integer> colNumbers = exc.getColNumbersInRowHavingText(colName, exc.getHeaderRow());
        System.out.println(colNumbers);

        List<Integer> rowNums = exc.getRowNumbersInColumnHavingText(m.getName(), colNumbers.get(0));
        System.out.println(rowNums);
        Object[][] data = new Object[rowNums.size()][1];
        for(int i = 0; i< rowNums.size(); i++){
            data[i][0] = exc.getRowData(rowNums.get(i));
        }
        return data;
    }
}
