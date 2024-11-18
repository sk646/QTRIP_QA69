package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    RemoteWebDriver driver;
    
public HomePage(RemoteWebDriver driver){
    this.driver=driver;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
  }

  @FindBy(xpath= "//div[@id='navbarNavDropdown']/descendant::ul/li//a[text()='Login Here']")
  WebElement LoginhereButton;
  
  @FindBy(xpath= "//div[@id='navbarNavDropdown']/descendant::ul/li//a[text()='Register']")
  WebElement RegisterButton;

  @FindBy(xpath= "//div[@id='navbarNavDropdown']/descendant::ul/li//div[text()='Logout']")
  WebElement LogoutButton;

  public boolean isRegisterbuttonVisible()throws InterruptedException{
    Thread.sleep(3000);
    return RegisterButton.isDisplayed() && RegisterButton.isEnabled();
  }

  public void navigateToRegistrationPage(){
    System.out.println("Navigating to Registration page");
    RegisterButton.click();
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


}
