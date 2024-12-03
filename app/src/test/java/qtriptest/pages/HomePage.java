package qtriptest.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.session.CapabilityTransform;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    RemoteWebDriver driver;
    
public HomePage(RemoteWebDriver driver){
    this.driver=driver;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
  }

  @FindBy(xpath= "//div[@id='navbarNavDropdown']/descendant::ul/li//a[text()='Login Here']")
  private WebElement LoginhereButton;
  
  @FindBy(xpath= "//div[@id='navbarNavDropdown']/descendant::ul/li//a[text()='Register']")
  private WebElement RegisterButton;

  @FindBy(xpath= "//div[@id='navbarNavDropdown']/descendant::ul/li//div[text()='Logout']")
  private WebElement LogoutButton;

  @FindBy(id = "autocomplete")
  private WebElement Searchbox;

  @FindAll({@FindBy(css = "ul#results>h5"),
           @FindBy(css = "ul#results>a>li")})
     private WebElement ResultFoundOrNot;
  
  @FindBy(xpath= "//h1")
 private WebElement HomepageTitle;

 @FindBy(xpath = "//a[text()='Reservations']")
 private WebElement movetoRservation;

  public boolean isRegisterbuttonVisible()throws InterruptedException{
    Thread.sleep(3000);
    return RegisterButton.isDisplayed() && RegisterButton.isEnabled();
  }

  public void navigateToRegistrationPage() throws InterruptedException{
    System.out.println("Navigating to Registration page");
    RegisterButton.click();
    Thread.sleep(4000);
  }

  public boolean logoutButtonIsvisible()throws InterruptedException{
    Thread.sleep(3000);
    System.out.println(LogoutButton.getText());
    return LogoutButton.getText().equals("Logout");
  }

  public void LogoutUser(){
    Actions ac = new Actions(driver);
    ac.moveToElement(LogoutButton).click().build().perform();
  }

  public void searchbar(String City) throws InterruptedException{
  //  Actions ac = new Actions(driver);
  // Searchbox.click();
  System.out.println("hfxuhzfsxhfu8s89fu8s9fu89");
  Searchbox.clear();
  Thread.sleep(2000);
  //  Searchbox.click();

   Searchbox.sendKeys(City);
  //  Thread.sleep(4000);

   if(ResultFoundOrNot.getText().equalsIgnoreCase(City)){
    ResultFoundOrNot.click();
   }else{
    System.out.println("No City found");
   }
  }
  
  public boolean iscityFoundOrnot() throws InterruptedException{
    boolean status= false;
    try{
        status=ResultFoundOrNot.isDisplayed();
        return status;
    }catch(Exception e){
        return status;
    }
  }
  // public boolean iscityFound() throws InterruptedException{
  //   boolean status= false;
  //   try{
  //       status=ResultFoundOrNot.isDisplayed();
  //       return status;
  //   }catch(Exception e){
  //       return status;
  //   }
  // }

  public boolean isNavigatetoHomePage()throws InterruptedException{
    Thread.sleep(2000);
    System.out.println("anshsssssss");
    System.out.println(driver.getCurrentUrl());
    System.out.println(HomepageTitle.getText());
     if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/") &&
    HomepageTitle.getText().equals("Welcome to QTrip"))
{
  return true;
}
   return false; 

}

public void navigateToRegistrations(){
  movetoRservation.click();
}
}