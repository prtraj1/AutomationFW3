package testCases;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductsPage;
import utilities.BrowserFactory;
import utilities.ExtentLog;

import java.util.*;

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
        List<WebElement> l = pp.validatePriceList();
        SoftAssert st = new SoftAssert();
        for (WebElement e:l){
            st.assertTrue(e.getText().contains(data.get("PriceTag")));
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
        ProductsPage pp = new ProductsPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to fetch the existing list of items");
        List<String> l2 = pp.getItemNamesList();
        ExtentLog.log(Status.INFO,"Trying to click on sort by name in ascending");
        List<String> l = pp.sortAscend();
        ExtentLog.log(Status.INFO,"Trying to compare if before and after item list are the same");
        Assert.assertTrue(l.equals(l2));

    }

    @Test(dataProvider = "myData")
    public void TC_010(Map <String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO,"Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        ProductsPage pp = new ProductsPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to fetch the existing list of items in descending order");
        List<String> li = pp.getItemNamesList();
        System.out.println("Before Descending" +li);
        Collections.sort(li, Collections.reverseOrder());
        System.out.println("After Descending" +li);
        ExtentLog.log(Status.INFO,"Trying to click on sort by name in descending");
        List<String> sort = pp.sortDescend();
        System.out.println("After sorting in page - Descending" +sort);
        ExtentLog.log(Status.INFO,"Trying to compare if before and after item list are the same");
        Assert.assertTrue(li.equals(sort));
       // Assert.assertEquals(Objects.equals(pp.sortDescend(), Collections.sort(pp.validateItemList(), Collections.reverseOrder()))); //how to compare here?
    }
}
