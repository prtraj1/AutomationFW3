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
        List<WebElement> l = pp.getWebElements();
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
        List<String> l = pp.sortAscendName();
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
        List<String> sort = pp.sortDescendName();
        System.out.println("After sorting in page - Descending" +sort);
        ExtentLog.log(Status.INFO,"Trying to compare if before and after item list are the same");
        Assert.assertTrue(li.equals(sort));
    }

    @Test(dataProvider = "myData") //to check
    public void TC_011(Map <String, String> data) {
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        ProductsPage pp = new ProductsPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to fetch the existing list price");
        List<WebElement> elm = pp.getWebElements();
        List<Float> fl = new LinkedList<>();
        for (int i = 0; i < elm.size(); i++) {
            String amount = elm.get(i).getText();
            float amt = Float.parseFloat(amount.replaceAll("[^0-9.]", ""));
            fl.add(amt);
        }
        System.out.println("Existing list of prices " + fl);
        Collections.sort(fl);
        System.out.println("Sorting existing Printing list from low to high price" + fl);
        ExtentLog.log(Status.INFO, "Trying to click on price Low to high");
        List<String> sort = pp.lowToHighPrice();
        List<Float> fl2 = new LinkedList<>();
        sort.forEach(e -> fl2.add(Float.parseFloat(e.replaceAll("[^0-9.]", ""))));// to ask
        System.out.println("After sorting prices from low to high " + fl2);
//        ExtentLog.log(Status.INFO,"Trying to compare if before and after price list are the same");
        Assert.assertEquals(fl2, fl);
    }

        @Test(dataProvider = "myData")
        public void TC_012(Map <String, String> data){
        ExtentLog.log(Status.INFO, data.toString());
        LoginPage lp = new LoginPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO, "Trying to login");
        lp.login(data.get("Username"), data.get("Password"));
        ProductsPage pp = new ProductsPage(BrowserFactory.getDriver());
        ExtentLog.log(Status.INFO,"Trying to fetch the existing list price");
        List<WebElement> elm = pp.getWebElements();
        List<Float> fl = new LinkedList<>();
        for (int i = 0; i<elm.size(); i++){
            String amount = elm.get(i).getText();
            float amt = Float.parseFloat(amount.replaceAll("[^0-9.]",""));
            fl.add(amt);
        }
            System.out.println("Existing price list: " +fl);
        Collections.sort(fl, Collections.reverseOrder());
        ExtentLog.log(Status.INFO," Trying to click on Price high to Low");
        List<String> hl = pp.HighToLowPrice();
        List<Float> fl2 = new LinkedList<>();
        hl.forEach(e -> fl2.add(Float.parseFloat(e.replaceAll("[^0-9.]",""))));
            System.out.println("After sorting prices from high to Low " + fl2);
        Assert.assertEquals(fl2,fl);
        }

}
