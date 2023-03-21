package utilities;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestUtility {

    private static ThreadLocal<ExtentTest> eTest = new ThreadLocal<>();

    //Puts an ExtentTest object into a thread
    //Puts an ExtentTest car onto the new thread/road
    public static void setExtentTest(ExtentTest test){
        eTest.set(test);
    }

    //Sends the ExtentTest object which was set previously using setExtentTest
    //Sends the information about the car on the thread/road
    public static ExtentTest getExtentTest(){
        return eTest.get();
    }

}
