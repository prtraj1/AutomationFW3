package testCases;

import com.aventstack.extentreports.Status;
import dataHandling.FetchTestData;
import listener.MyTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import utilities.BrowserFactory;
import utilities.ExtentLog;

import java.util.Map;

//@Listeners({MyTestNGListener.class})
public class CartTest{

    @Test(dataProvider = "myData", dataProviderClass = FetchTestData.class, description = "Verify add to cart functionality")
    public void TC_013(Map<String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO,"Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        CartPage cp = new CartPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to add an item");
        cp.addToCart(data.get("ItemName"));
        ExtentLog.log(Status.INFO,"Trying to visit cart page");
        cp.goToCart();
        ExtentLog.log(Status.INFO,"User is on the cart page");
        ExtentLog.log(Status.INFO,"Validating if added item is listed in the cart");
        Assert.assertEquals(data.get("ItemName"),cp.getItemFromCart());

}
    @Test(dataProvider = "myData", dataProviderClass = FetchTestData.class)
    public void TC_014(Map<String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO,"Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        CartPage cp = new CartPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to open an item and view in products page");
        cp.goToProduct(data.get("ItemName"));
        ExtentLog.log(Status.INFO,"Products page is opened");
        ExtentLog.log(Status.INFO,"Verifying the item selected and opened");
        Assert.assertEquals(cp.getItemFromProduct(),data.get("ItemName"));
    }

    @Test(dataProvider = "myData", dataProviderClass = FetchTestData.class, description = "Verify product purchase functionality")
    public void TC_015(Map<String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO,"Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        CartPage cp = new CartPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to add an item");
        cp.addToCart(data.get("ItemName"));
        ExtentLog.log(Status.INFO,"Trying to visit cart page");
        cp.goToCart();
        ExtentLog.log(Status.INFO,"User is on the cart page");
        ExtentLog.log(Status.INFO,"Validating if added item is listed in the cart");
        Assert.assertEquals(data.get("ItemName"),cp.getItemFromCart());
        ExtentLog.log(Status.INFO,"Same item found as the added one. Checking out..");
        cp.checkoutItem();
        ExtentLog.log(Status.INFO,"Checking if checkout page is reached");
        Assert.assertTrue(cp.getCheckoutInfoText().contains("Information"));
        ExtentLog.log(Status.INFO,"On the checkout page. Filling the personal info");
        cp.enterInfo(data.get("firstName"), data.get("lastName"), data.get("postalCode"));
        ExtentLog.log(Status.INFO,"Validating if user is on checkout overview page");
        Assert.assertEquals(cp.getItemFromCheckout(), data.get("ItemName"));
        ExtentLog.log(Status.INFO,"Click on finish");
        cp.clickFinish();
    }
}