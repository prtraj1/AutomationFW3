package pages;

import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WebActions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebActions act;

    By cartButtonList = By.xpath("//button[contains(text(),'cart')]");
    By priceTagList = By.className("inventory_item_price");
    By cartButton = By.className("shopping_cart_link");
    By filterButton = By.className("product_sort_container");
    By itemName = By.className("inventory_item_name");

    public ProductsPage(WebDriver driver){
        this.driver= driver;
        act = new WebActions(driver);
    }

    public List<WebElement> validateCartButtonList(){
        return act.getWebElementsList(cartButtonList);
    }
    public List<WebElement> validatePriceList(){
       return act.getWebElementsList(priceTagList);
    }
    public List<String> sortAscend(){
       act.selectByValue(filterButton,"az");
       List<WebElement> elm = act.getWebElementsList(itemName);
        List<String> itemnames = new LinkedList<>();
        for(WebElement wb:elm){
            itemnames.add(wb.getText());
        }
        return itemnames;
    }
    public List<String> getItemNamesList(){
        List<WebElement> li = act.getWebElementsList(itemName);
        List<String> itemnames = new LinkedList<>();
        for(WebElement wb:li){
            itemnames.add(wb.getText());
        }
        return itemnames;
    }
    public List<String> sortDescend(){
        act.selectByValue(filterButton, "za");
        List<WebElement> elm = act.getWebElementsList(itemName);
        List<String> itemnames = new LinkedList<>();
        for(WebElement wb:elm){
            itemnames.add(wb.getText());
        }
        return itemnames;
    }
}
