package qtriptest.tests;

import qtriptest.ReportSingleton;
import qtriptest.driverManager.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_03 {
    RemoteWebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    AdventurePage adventurePage;
    AdventureDetailsPage adventuredetailsPage;
    HistoryPage historyPage;
    SoftAssert softAssert;
    private static final int pageLoadTimeout = 20;
   
@BeforeSuite
public void setup() throws MalformedURLException{
     
    // RemoteWebDriver driver = new ChromeDriver();
    driver=DriverSingleton.getDriver("chrome");
    DriverSingleton.launchApp("https://qtripdynamic-qa-frontend.vercel.app/");
    ReportSingleton.report= ReportSingleton.getReport();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    
    homePage = new HomePage(driver);
    registerPage = new RegisterPage(driver);
    loginPage = new LoginPage(driver,registerPage );
    adventurePage = new AdventurePage(driver);
    adventuredetailsPage = new AdventureDetailsPage(driver);
    historyPage = new HistoryPage(driver);
    }

    @Test(dataProvider = "QtripNewUserData",dataProviderClass = ExternalDataProvider.class,description="booking adventure",
     priority = 3,groups = {"Booking and Cancellation Flow"})
    public void TestCase03(String NewUserName, String Password, String SearchCity, String AdventureName, String GuestName, String Date, String Count) throws InterruptedException, IOException{
     ReportSingleton.test = ReportSingleton.report.startTest("Booking and Cancellation Flow");
      try{
      softAssert = new SoftAssert();

//    softAssert.assertTrue(homePage.isRegisterbuttonVisible(),"Register button not found");

   homePage.navigateToRegistrationPage();

   softAssert.assertTrue(registerPage.isNavigatetoRegisterpage(),"Registerpage navigation is failed");

   registerPage.NewuserOnboard(NewUserName,Password,Password,true);

   softAssert.assertTrue(loginPage.isNavigatetoLoginpage(),"Login page navigation is failed");

   loginPage.performlogin(NewUserName,Password,true);
   
   homePage.searchbar(SearchCity);
   AdventurePage.chooseAdventure(AdventureName);
   Thread.sleep(3000);

   AdventureDetailsPage.enterdetails(GuestName,Date,Count);
   Thread.sleep(2000);
   AdventureDetailsPage.messageResult();
   
   AdventureDetailsPage.bookingHistory();
   Thread.sleep(2000);
   historyPage.isNavigatetoReservationpageHistory();
   historyPage.idText();
   historyPage.TransactionsCount();
   historyPage.istansIdexist();
   historyPage.cancelReservation();
   Thread.sleep(3000);
   homePage.LogoutUser();
   softAssert.assertAll();
   ReportSingleton.test.log(LogStatus.PASS, "Successfull Booking and Cancellation Flow");
      }catch (Exception e) {
        //TODO: handle exception
        ReportSingleton.test.log(LogStatus.FAIL,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+ "Failed Booking and Cancellation Flow");
        
        }
      
    }
    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
            String.valueOf(java.time.LocalDateTime.now()), type, message, status));
      }

    @AfterSuite(alwaysRun = true)
    public void teardown()throws MalformedURLException{
        driver.quit();
        System.out.println("Application closed");
        ReportSingleton.report.endTest(ReportSingleton.test);
        ReportSingleton.report.flush();
    }
}
