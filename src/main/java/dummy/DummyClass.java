package dummy;

import utilities.ExcelUtility;

import java.io.File;
import java.util.*;

public class DummyClass {

    public static void main(String[] args) {
        String location = "assets/Runner.xlsx";
        File rnLocation = new File(location);

        ExcelUtility exc1 = new ExcelUtility(location, "Sheet1");
//        System.out.println(exc1.getHeaderRow());
//        exc1.setHeaderRow(3);
//        System.out.println(exc1.getHeaderRow());
//        ExcelUtility exc2 = new ExcelUtility(rnLocation, 0);

//        System.out.println(exc1.getData(0, 1));

//        Map<String, String> data = new LinkedHashMap<>();
//        data.put("name", "sadia");
//        data.put("add", "hwh");
//        System.out.println(data);
//        System.out.println(data.get("name") + "--" + data.get("add"));

//        List<Integer> numsA = Arrays.asList(1,2,1,2,3,3,4);
//        System.out.println(numsA);
//
//        Set<Integer> numsB = new HashSet<>(numsA);
//        System.out.println(numsB);

//        System.out.println(exc1.getRowData(1));

//        if(!false){
//            System.out.println("Inside if");
//        }else{
//            System.out.println("Inside else");
//        }

//        String text = "     ";
//        System.out.println(text.length());

//        System.out.println(exc1.isRowEmpty(7));

        ExcelUtility exc2 = new ExcelUtility("assets/data.xlsx", 0);
//        System.out.println(exc2.getRowNumbersInColumnHavingText("pwd1234", 2));
        System.out.println(exc2.getColNumbersInRowHavingText("Username", 5));

    }
}
