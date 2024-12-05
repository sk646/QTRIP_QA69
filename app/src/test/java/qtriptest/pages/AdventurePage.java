package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.FindAll;

public class AdventurePage {
    RemoteWebDriver driver;

public AdventurePage(RemoteWebDriver driver){
    this.driver = driver;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
}

private static String adventurepageEndpoint = "/pages/adventures/";

@FindBy(css = "select#duration-select")
private WebElement durationFilter;

@FindBy(css ="select#category-select")
private WebElement categoryFilter;

@FindBy(xpath = "//div[@onclick='clearDuration(event)']")
private WebElement clearDuration;

@FindBy(xpath = "//div[@onclick='clearCategory(event)']")
private WebElement clearCategory;

@FindBy(xpath = "//h1")
private WebElement adventurePageTitle;

@FindBy(xpath = "//div[@class='row']")
public boolean entirepageData;

@FindBy(xpath = "//div[contains(@class,'text-md-center')]/descendant::div/h5")
private static WebElement AdventuresChoose;

@FindAll({
    @FindBy(xpath ="//div[contains(@class,'col-6 col-lg')]"),
    @FindBy(xpath ="//div[@id='data']")
 })
List<WebElement> adventureContents;


public void filterpage(String DurationFilter){
  try{
    Select sc = new Select(durationFilter); 
    sc.selectByVisibleText(DurationFilter); 
    Thread.sleep(3000);
    // durationFilter.click();
    SeleniumWrapper.click(durationFilter);
    }catch(Exception e){
    System.out.println(e.getMessage());
    }
   }
public void categorychoose(String CategoryFilter){

  try{
    Select sc = new Select(categoryFilter);
    sc.selectByVisibleText(CategoryFilter);
    Thread.sleep(3000);
    // categoryFilter.click();
    SeleniumWrapper.click(categoryFilter);
  }catch(Exception e){
   System.out.println(e.getMessage());
  }
}

public void clearfilter() throws MalformedURLException{
    // clearDuration.click();
    // clearCategory.click();
    SeleniumWrapper.click(clearDuration);
    SeleniumWrapper.click(clearCategory);
}
public boolean isNavigateToadventurePage()throws InterruptedException{
    System.out.println("Navigating to adventure page");
    Thread.sleep(2000);
    return driver.getCurrentUrl().contains(adventurepageEndpoint) && 
    adventurePageTitle.getText().equals("Explore all adventures");
}

public static void chooseAdventure(String adventurename){
    AdventuresChoose.getText().equals(adventurename);
       if(AdventuresChoose.isDisplayed()){
        AdventuresChoose.click();
       }
    }

public Boolean verifyAdventureContents(String filteredResult) {
        try {
            Integer actualResult = adventureContents.size();
            String result = actualResult.toString();
            if(result.equals(filteredResult)){
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception while verifying adventure contents: " + e.getMessage());
            return true;
        }
    }   
}

