package qtriptest.pages;

import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {
    RemoteWebDriver driver;

public RegisterPage(RemoteWebDriver driver){
     this.driver=driver;
     PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);

     }

private static String registerpageEndpoint = "/pages/register/";
public String userName = null;

//  @FindBy(xpath="//div[@id='navbarNavDropdown']/descendant::ul/li//a[text()='Register']")
//  WebElement RegisterButton;

 @FindBy(xpath = "//h2")
 WebElement registerpageTitle;

 @FindBy(name="email")
 private WebElement EmailaddressText;
 
 @FindBy(name="password")
 private WebElement passwordText;
 
 @FindBy(name="confirmpassword")
 WebElement ConfirmpasswrodText;
 
 @FindBy(xpath="//button[contains(text(),'Register Now')]")
 private WebElement Registernow;


public boolean isNavigatetoRegisterpage(){
  // RegisterButton.click();
 return driver.getCurrentUrl().contains(registerpageEndpoint) && 
 registerpageTitle.getText().equals("Register");
}

public void NewuserOnboard(String userName,String password,String confirmPassword,boolean isDynamicUser){
  Actions action = new Actions(driver);
   try{
      if(isDynamicUser){
        userName = String.format("testemail_%s@email.com",UUID.randomUUID().toString());
        this.userName = userName;
      }
        // RegisterButton.click();
        this.EmailaddressText.click();
        action.moveToElement(this.EmailaddressText).click()
        .sendKeys(this.EmailaddressText, userName).build().perform();

        action.moveToElement(this.passwordText).click()
        .sendKeys(this.passwordText, password).build().perform();
        
        action.moveToElement(this.ConfirmpasswrodText).click()
        .sendKeys(this.ConfirmpasswrodText, confirmPassword).build().perform();

        action.moveToElement(this.Registernow).click().build().perform();
    } catch (Exception e) {
        //TODO: handle exception
        System.out.println(e.getMessage());
        e.printStackTrace();

    }
}
}

