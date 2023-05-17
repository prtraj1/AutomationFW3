package dataHandling;

import org.testng.annotations.DataProvider;
import utilities.ExcelUtility;

import java.lang.reflect.Method;
import java.util.List;

public class FetchTestData {
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
