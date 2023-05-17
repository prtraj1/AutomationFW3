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
    By cart = By.xpath("(//button[contains(text(),'cart')])[1]");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        act = new WebActions(driver);
    }

    public List<WebElement> validateCartButtonList() {
        return act.getWebElementsList(cartButtonList);
    }

    public List<WebElement> getWebElements() {
        return act.getWebElementsList(priceTagList);
    }

    public List<String> sortAscendName() {
        act.selectByValue(filterButton, "az");
        List<WebElement> elm = act.getWebElementsList(itemName);
        List<String> itemnames = new LinkedList<>();
        for (WebElement wb : elm) {
            itemnames.add(wb.getText());
        }
        return itemnames;
    }

    public List<String> getItemNamesList() {
        List<WebElement> li = act.getWebElementsList(itemName);
        List<String> itemnames = new LinkedList<>();
        for (WebElement wb : li) {
            itemnames.add(wb.getText());
        }
        return itemnames;
    }

    public List<String> sortDescendName() {
        act.selectByValue(filterButton, "za");
        List<WebElement> elm = act.getWebElementsList(itemName);
        List<String> itemnames = new LinkedList<>();
        for (WebElement wb : elm) {
            itemnames.add(wb.getText());
        }
        return itemnames;
    }

    public List<String> getPriceList() {
        act.getWebElementsList(priceTagList);
        List<WebElement> li = act.getWebElementsList(priceTagList);
        List<String> priceList = new LinkedList<>();
        for (WebElement wb : li) {
            priceList.add(wb.getText());
        }
        return priceList;
    }

    public List<String> lowToHighPrice(){
        act.selectByValue(filterButton,"lohi");
        List<WebElement> elm = act.getWebElementsList(priceTagList);
        List<String> priceList = new LinkedList<>();
        for(WebElement wb:elm){
            priceList.add(wb.getText());
        }
        return priceList;
    }

    public List<String> HighToLowPrice(){
      act.selectByValue(filterButton, "hilo");
      List<WebElement> elm = act.getWebElementsList(priceTagList);
      List<String> priceList = new LinkedList<>();
      for(WebElement wb:elm){
          priceList.add(wb.getText());
      }
      return priceList;
    }
}
