package qtriptest;

import qtriptest.driverManager.DriverSingleton;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumWrapper {
    public static RemoteWebDriver driver;

    public static boolean click(WebElement element) throws MalformedURLException {
          if (element.isDisplayed()) {
            Actions actions = new Actions(DriverSingleton.getDriver(null));
            actions.moveToElement(element).click().build().perform();
            return true;
        } else
            return false;
    }

    public static boolean sendKeys(WebElement inputTextBox, String text) {
        if (inputTextBox.isDisplayed()) {
            inputTextBox.clear();
            inputTextBox.sendKeys(text);
            return true;
        }
        else
            return false;
    }

    public static boolean navigatetoUrl(WebDriver driver, String url) {
        boolean status;
        status = driver.getCurrentUrl().equals(url);
        if (!status) {
            driver.get(url);
        } else {
            return status;
        }
        return status;

    }

    public static String  getEpochTimeasString(){
        Long epochTime = System.currentTimeMillis()/1000;
        String epochTimeString = String.valueOf(epochTime);
        return epochTimeString;
    }

}
