package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import utilities.ExcelUtility;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class TestBase {

    @DataProvider(name = "sadiaData")
    public Object[][] getData(Method m){
//        Object[][] any2dArray = new Object[1][2];
//        any2dArray[0][0] = "data01";
//        any2dArray[1][0] = "data02";
//        return any2dArray;
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
//        return new Object[][]{{"sadia"}, {"nasim"}};
    }

    @Test(dataProvider = "sadiaData")
    public void TC_001(Map<String, String> data){
        System.out.println(data);
        System.out.println(data.get("Username"));
        System.out.println(data.get("Password"));
        //driver.findElement(By.xpath("xyx")).sendKeys(data.get("Username"));
    }


    @Test(dataProvider = "sadiaData")
    public void TC_002(Map<String, String> data){
        System.out.println(data);
        System.out.println(data.get("Username"));
        System.out.println(data.get("Password"));
    }

    @Parameters({"theater", "city"})
    @Test
    public void TC_003(String theater, String city){
        System.out.println(theater);
        System.out.println(city);
    }

    @Parameters({"theater", "city"})
    @Test
    public void TC_004(@Optional("inox") String theater, @Optional("kolkata") String city){
        System.out.println(theater);
        System.out.println(city);
    }

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(String browser){
        System.out.println(browser);
    }

    @Test(groups = {"smoke", "regression"})
    public void TC_005(){
        System.out.println("smoke and regression");
    }

    @Test(groups = {"smoke"}, invocationCount = 3)
    public void TC_006(){
        System.out.println("smoke");
    }

    @Test(groups = {"regression"})
    public void TC_007(){
        System.out.println("regression");
    }


}
