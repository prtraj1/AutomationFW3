package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WebActions;

public class CartPage {
    private WebDriver driver; // technical reason for making this private?
    private WebActions act;

    By addToCart = By.xpath("//div[contains(text(),'Sauce Labs Backpack')]/ancestor::div[1]/following-sibling::div/button");
    By cartButton = By.xpath("//a[@class='shopping_cart_link']");
    By cartItemName = By.className("inventory_item_name");
    By productItemName = By.xpath("//div[@class='inventory_details_name large_size']");

    By checkout = By.id("checkout");
    By checkoutInfoText = By.xpath("//span[@class='title']");
    By fname = By.id("first-name");
    By lname = By.id("last-name");
    By pcode = By.id("postal-code");
    By ctd = By.id("continue");
    By checkoutItemText = By.className("inventory_item_name");
    By finish = By.id("finish");



    public CartPage(WebDriver driver){
        this.driver = driver;
        act = new WebActions(driver);
    }
    public void addToCart(String item){
        act.doClick(By.xpath("//div[contains(text(),'"+item+"')]/ancestor::div[1]/following-sibling::div/button"));
    }
    public void goToCart(){
        act.doClick(cartButton);
    }
    public String getItemFromCart(){
        return act.doGetText(cartItemName);
    }
    public void goToProduct(String itm){
        act.doClick(By.xpath("//div[contains(text(),'"+itm+"')]"));
    }
    public String getItemFromProduct(){
       return act.doGetText(productItemName);
    }

    public void checkoutItem(){
        act.doClick(checkout);
    }
    public String getCheckoutInfoText(){
        return act.doGetText(checkoutInfoText);
    }
    public void enterInfo(String firstName, String lastName, String postalCode){
        act.doEnterText(fname, firstName);
        act.doEnterText(lname, lastName);
        act.doEnterText(pcode, postalCode);
        act.doClick(ctd);
    }
    public String getItemFromCheckout(){
        return act.doGetText(checkoutItemText);
    }
    public void clickFinish(){
        act.doClick(finish);
    }

}
//how to handle multiple item addition? 1 row of testcase sending only 1 item at a time?
