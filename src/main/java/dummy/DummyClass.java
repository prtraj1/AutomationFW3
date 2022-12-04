package dummy;

import utilities.ExcelUtility;

import java.io.File;

public class DummyClass {

    public static void main(String[] args) {
        String location = "assets/Runner.xlsx";
        File rnLocation = new File(location);

        ExcelUtility exc1 = new ExcelUtility(location, "Sadi");
//        ExcelUtility exc2 = new ExcelUtility(rnLocation, 0);

        System.out.println(exc1.getHeaderRow());
        exc1.setHeaderRow(3);
        System.out.println(exc1.getHeaderRow());

    }
}
