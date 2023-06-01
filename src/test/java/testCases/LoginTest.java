package testCases;

import com.aventstack.extentreports.Status;
import dataHandling.FetchTestData;
import listener.MyTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.BrowserFactory;
import utilities.ExtentLog;
import utilities.ExtentTestUtility;
import utilities.WebActions;

import java.util.Map;
import java.util.concurrent.TimeUnit;

//@Listeners({MyTestNGListener.class})
public class LoginTest {

    @Test(dataProvider = "myData", dataProviderClass = FetchTestData.class, description = "Login with valid credentials")
    public void TC_004(Map<String, String> data){
        ExtentLog.log(Status.INFO, data.toString()); //explain this line
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to login");
        lp.login(data.get("Username"), data.get("Password"));

        ExtentLog.log(Status.INFO, "Trying to validate homepage title");
        HomePage hp = new HomePage(BrowserFactory.getDriver());
        String pageTitle = hp.getPageTitle();
        Assert.assertEquals(pageTitle, data.get("PageTitle"));// there is no showcase whether this was true?
    }

    @Test(dataProvider = "myData", dataProviderClass = FetchTestData.class, description = "Login with invalid credentials")
    public void TC_005(Map<String, String> data){
//        ExtentTestUtility.getExtentTest().log(Status.INFO, data.toString());
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        Assert.assertTrue(lp.validateErrorMsg(data.get("ErrorMsg")), "Error message is not displayed!");
    }
    @Test(dataProvider = "myData", dataProviderClass = FetchTestData.class)
    public void TC_006(Map<String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        ExtentLog.log(Status.INFO, "Trying to open menu tab");
        HomePage hp = new HomePage(BrowserFactory.getDriver());
        hp.openMenuOptions();
        ExtentLog.log(Status.INFO, "Trying to logout");
        hp.logout();
    }
}
