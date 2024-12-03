
package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
  RemoteWebDriver driver;
    
public HistoryPage(RemoteWebDriver driver){
    this.driver=driver;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver,15),this);
  }
 
  @FindBy(id = "reservation-table")
  private WebElement bookingsDetails;

  @FindBy(css = ".content>h1" )
  private WebElement pageTitle;
  
  @FindBy(xpath = "//th[@scope='row']")
  private WebElement transactionId;

  @FindBy(className = "cancel-button")
  private WebElement cancelButton;

  @FindBy(id = "no-reservation-banner")
  private WebElement reservationtext;

  public boolean isNavigatetoReservationpageHistory() throws InterruptedException{
    Thread.sleep(2000);
   return pageTitle.getText().equals("Your Reservations");
  }

  public void idText(){
    System.out.println(transactionId.getText());
  }
  public int TransactionsCount() {
    List<WebElement> list=driver.findElements(By.xpath("//th[@scope='row']"));
    return list.size();
  }
 public void cancelReservation() throws InterruptedException{
  cancelButton.click();
  Thread.sleep(2000);
  System.out.println(reservationtext.getText());
   }

 public boolean istansIdexist(){
  System.out.println(transactionId.isDisplayed());
  return transactionId.isDisplayed();
 }
 public boolean allbookings(){
  return bookingsDetails.isDisplayed();
 }


}