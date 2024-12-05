package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.net.MalformedURLException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {
    static RemoteWebDriver driver;

public AdventureDetailsPage(RemoteWebDriver driver){
    this.driver = driver;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
}

@FindBy(name="name")
private static WebElement enterName;

@FindBy(name="date")
private static WebElement enterDate;

@FindBy(name="person")
private static WebElement personCount;

@FindBy(className ="reserve-button")
private static WebElement reserveButton;

@FindBy(id ="reserved-banner")
private static WebElement reservationSuccess;

@FindBy(xpath ="//a[@href='../reservations/']")
private static WebElement veiwBookings;

@FindBy(xpath = "//a[text()='Home']")
private static WebElement moveToHome;

public static void enterdetails(String GuestName, String Date, String Count){
    Actions action = new Actions(driver);
    action.moveToElement(enterName).click().sendKeys(GuestName).build().perform();
    action.moveToElement(enterDate).click().sendKeys(Date).build().perform();
    action.moveToElement(personCount).click().sendKeys(Count).build().perform(); 
    action.moveToElement(reserveButton).click().build().perform();
}

public static boolean messageResult(){
    System.out.println(reservationSuccess.getText());
    return reservationSuccess.getText().contains("successful");
    
}
public static void bookingHistory() throws MalformedURLException{
    // veiwBookings.click();
    SeleniumWrapper.click(veiwBookings);
}

public static void navigateHome() throws MalformedURLException{
    // moveToHome.click();
    SeleniumWrapper.click(moveToHome);
}
}
