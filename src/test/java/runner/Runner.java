package runner;

import listener.MyTestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utilities.ExcelUtility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        Runner rx = new Runner();
        List<Map<String, String>> tests = rx.getTests();

        XmlSuite xst = new XmlSuite();
        xst.setName("Automation");
        xst.setListeners(List.of("listener.MyTestNGListener"));
        xst.setParallel(XmlSuite.ParallelMode.TESTS);
        xst.setThreadCount(3);

        List<XmlTest> xTests = new LinkedList<>();

        int count = 0;

        for(Map<String, String> test : tests){
            XmlTest xt = new XmlTest(xst);
            xt.setName(test.get("Test Case ID") + "_" + (count++));
            xt.setParameters(Map.of("browser", test.get("Browser"), "suite", test.get("Suite")));
            List<XmlClass> classes = new ArrayList<>();
            XmlClass tClass = new XmlClass("testCases."+test.get("Class"));
            tClass.setIncludedMethods(List.of(new XmlInclude(test.get("Test Case ID"))));
            classes.add(tClass);

            xt.setClasses(classes);

            xTests.add(xt);
        }

        xst.setTests(xTests);
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(xst);

        TestNG tng = new TestNG();
        tng.setXmlSuites(suites);
        tng.run();
    }

    public List<Map<String, String>> getTests(){
        String colName = "Execution";

        ExcelUtility exc = new ExcelUtility("assets/Runner.xlsx", 0);
        List<Integer> colNumbers = exc.getColNumbersInRowHavingText(colName, exc.getHeaderRow());

        List<Integer> rowNums = exc.getRowNumbersInColumnHavingText("Y", colNumbers.get(0));

        List<Map<String, String>> testsToExecute = new LinkedList<>();

        for(int row: rowNums){
            testsToExecute.add(exc.getRowData(row));
        }

        return testsToExecute;
    }

}
