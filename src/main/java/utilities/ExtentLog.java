package utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import java.util.Arrays;
import java.util.List;

public class ExtentLog {

    public static void log(Status status, String message){
        LogMessage.info(message);

        if(ExtentTestUtility.getExtentTest() == null){
//            LogMessage.info("Can not log message in the report at the moment! Message- "+message);
//            System.out.println("Can not log message in the report at the moment! Message- "+message);
            return;
        }

        if(Arrays.asList("yes", "true").contains(GlobalProperties.getProperty("stepScreenshot").toLowerCase())) {
            if(BrowserFactory.getDriver() != null)
                ExtentTestUtility.getExtentTest().log(status, message, MediaEntityBuilder.createScreenCaptureFromBase64String(new WebActions(BrowserFactory.getDriver()).takeBase64Screenshot()).build());
            else
                ExtentTestUtility.getExtentTest().log(status, message);
        }else
            ExtentTestUtility.getExtentTest().log(status, message);
    }
}
