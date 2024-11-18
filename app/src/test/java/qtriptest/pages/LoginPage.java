package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {
    RemoteWebDriver driver;
    RegisterPage registerPage;
public LoginPage(RemoteWebDriver driver,RegisterPage registerPage){
    this.driver=driver;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
    this.registerPage = registerPage;
}

private static String loginpageEndpoint = "/pages/login";

@FindBy(xpath = "//h2")
private WebElement LoginpageTitle;

@FindBy(name="email")
private WebElement EmailaddressText;

@FindBy(name="password")
private WebElement passwordText;

@FindBy(xpath="//button[contains(text(),'Login to QTrip')]")
private WebElement LoginButton;

public boolean isNavigatetoLoginpage() throws InterruptedException{
Thread.sleep(2000);
return driver.getCurrentUrl().contains(loginpageEndpoint) && 
LoginpageTitle.getText().equals("Login");
}

public void performlogin(String emailId, String password, boolean withDynamicUser) throws InterruptedException{
    System.out.println("Into Loginpage");
    Thread.sleep(4000);
    if(withDynamicUser){
    emailId = registerPage.userName;
    }
    Actions actions = new Actions(driver);
    actions.moveToElement(this.EmailaddressText)
    .sendKeys(this.EmailaddressText, emailId).build().perform();

    actions.moveToElement(this.passwordText)
    .sendKeys(this.passwordText, password).build().perform();

    actions.moveToElement(this.LoginButton).click().build().perform();
}

}
