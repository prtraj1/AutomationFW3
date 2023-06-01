package utilities;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    private static ThreadLocal<WebDriver> wDriver = new ThreadLocal<>();

    public static void setDriver(WebDriver driver){
        wDriver.set(driver);
    }

    public synchronized static WebDriver getDriver(){
        return wDriver.get();
    }
}
