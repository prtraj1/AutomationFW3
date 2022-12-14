package testCases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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
        System.out.println(m.getName());
        ExcelUtility exc = new ExcelUtility("assets/data.xlsx", 0);
        List<Integer> rowNums = exc.getRowNumbersInColumnHavingText(m.getName(), 0);
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
    }

    @Test(dataProvider = "sadiaData")
    public void TC_002(Map<String, String> data){
        System.out.println(data);
        System.out.println(data.get("Username"));
        System.out.println(data.get("Password"));
    }
}
