package testCases;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utilities.BrowserFactory;
import utilities.ExtentTestUtility;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginTest extends TestPrime{

    @Test(dataProvider = "myData")
    public void TC_004(Map<String, String> data){
        ExtentTestUtility.getExtentTest().info(data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        lp.login(data.get("Username"), data.get("Password"));

        HomePage hp = new HomePage(BrowserFactory.getDriver());
        String pageTitle = hp.getPageTitle();
        Assert.assertEquals(pageTitle, data.get("PageTitle"));
    }

    @Test(dataProvider = "myData")
    public void TC_005(Map<String, String> data){
        ExtentTestUtility.getExtentTest().log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        lp.login(data.get("Username"), data.get("Password"));
        Assert.assertTrue(lp.validateErrorMsg(data.get("ErrorMsg")), "Error message is not displayed!");
    }
}
