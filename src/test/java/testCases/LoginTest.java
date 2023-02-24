package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class LoginTest extends TestPrime{

    @Test(dataProvider = "myData")
    public void TC_004(Map<String, String> data){
        LoginPage lp = new LoginPage(driver);
        lp.login(data.get("Username"), data.get("Password"));

        HomePage hp = new HomePage(driver);
        String pageTitle = hp.getPageTitle();
        Assert.assertEquals(pageTitle, data.get("PageTitle"));
    }

    @Test(dataProvider = "myData")
    public void TC_005(Map<String, String> data){
        LoginPage lp = new LoginPage(driver);
        lp.login(data.get("Username"), data.get("Password"));
        Assert.assertTrue(lp.validateErrorMsg(data.get("ErrorMsg")), "Error message is not displayed!");
    }
}
