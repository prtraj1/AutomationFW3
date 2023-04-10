package pages;

import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WebActions;

import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebActions act;

    By cartButtonList = By.xpath("//button[contains(text(),'cart')]");
    By priceTagList = By.className("inventory_item_price");
    By cartButton = By.className("shopping_cart_link");
    By filterButton = By.className("product_sort_container");

    public ProductsPage(WebDriver driver){
        this.driver= driver;
        act = new WebActions(driver);
    }

    public List<WebElement> validateCartButtonList(){
        return act.validateList(cartButtonList);
    }
    public List<WebElement> validatePriceList(){
       return act.validateList(priceTagList);
    }
    public void sortClick(){
        act.selectByValue(filterButton,"za");
    }
}
