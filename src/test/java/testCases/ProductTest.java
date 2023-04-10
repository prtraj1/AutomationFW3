package testCases;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.AssertionLogic;
import utilities.BrowserFactory;
import utilities.ExtentLog;

import java.util.List;
import java.util.Map;

public class ProductTest extends TestPrime{

    @Test(dataProvider = "myData")
    public void TC_007(Map <String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO,"Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        ExtentLog.log(Status.INFO,"Trying to validate the presence of all the cart buttons");
        ProductsPage pp = new ProductsPage(BrowserFactory.getDriver());
        pp.validateCartButtonList();
        ExtentLog.log(Status.INFO,"Trying to check if cart button present in all the products as expected");
        Assert.assertNotEquals(pp.validateCartButtonList().size(),Integer.parseInt(data.get("ExpectedCount"))); // how to show in reports about the difference in count?

    }

    @Test(dataProvider = "myData")
    public void TC_008(Map <String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO,"Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        ExtentLog.log(Status.INFO,"Trying to validate that all the products are set with a price tag");
        ProductsPage pp = new ProductsPage(BrowserFactory.getDriver());
        List< WebElement> l = pp.validatePriceList();
        SoftAssert st = new SoftAssert();
        for (WebElement e:l){
            st.assertFalse(e.getText().contains(data.get("PriceTag")));
        }
        st.assertAll();
        //Assert.assertEquals(pp.validatePriceList().contains(data.get("PriceTag")),data.get("PriceTag"));
    }

    @Test(dataProvider = "myData")
    public void TC_009(Map <String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO,"Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        ExtentLog.log(Status.INFO,"Trying to click on sort");
        ProductsPage pp = new ProductsPage(BrowserFactory.getDriver());
        pp.sortClick();
    }
}
