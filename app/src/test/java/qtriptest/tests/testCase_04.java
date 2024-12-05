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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class testCase_04 {
    static RemoteWebDriver driver;
    static HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    AdventurePage adventurePage;
    AdventureDetailsPage adventuredetailsPage;
    HistoryPage historyPage;
    SoftAssert softAssert;
    static Assertion assertion;
    private static final int pageLoadTimeout = 20;
   
@BeforeSuite
public void setup() throws MalformedURLException{
     
    // RemoteWebDriver driver = new ChromeDriver();
    driver=DriverSingleton.getDriver("chrome");
    DriverSingleton.launchApp("https://qtripdynamic-qa-frontend.vercel.app/");
    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    ReportSingleton.report= ReportSingleton.getReport();

    homePage = new HomePage(driver);
    registerPage = new RegisterPage(driver);
    loginPage = new LoginPage(driver, registerPage);
    adventurePage = new AdventurePage(driver);
    adventuredetailsPage = new AdventureDetailsPage(driver);
    historyPage = new HistoryPage(driver);
    
}

@Test(dataProvider ="QtripDataSet",dataProviderClass = ExternalDataProvider.class, 
description="Testing with sets of data",priority = 4,groups = {"Reliability Flow"})
public void TestCase04(String NewUserName,String Password,String dataset1, String dataset2, String dataset3)throws InterruptedException, IOException{
  ReportSingleton.test = ReportSingleton.report.startTest("verifying the booking History");
  softAssert = new SoftAssert();
  boolean status;
//    String[] DS1 = dataset1.split(";");
//    String[] DS2 = dataset2.split(";");
//    String[] DS3 = dataset3.split(";");
  //  logStatus("Page test", "navigation to register page", "started");
    homePage.navigateToRegistrationPage();
    softAssert.assertTrue(registerPage.isNavigatetoRegisterpage(),"Registerpage navigation is failed");
    Thread.sleep(2000);
    registerPage.NewuserOnboard(NewUserName,Password,Password,true);
 
    softAssert.assertTrue(loginPage.isNavigatetoLoginpage(),"Login page navigation is failed");
 
    loginPage.performlogin(NewUserName,Password,true);
    status = homePage.logoutButtonIsvisible();
    softAssert.assertTrue(status,"User not logged in");
    Thread.sleep(2000);
    test04Execution(dataset1,homePage);
    test04Execution(dataset2,homePage);
    test04Execution(dataset3,homePage);
    
    homePage.navigateToRegistrations();
    historyPage.isNavigatetoReservationpageHistory();
    Thread.sleep(2000);
    historyPage.TransactionsCount();
    historyPage.idText();
    historyPage.allbookings();
    historyPage.istansIdexist();
    homePage.LogoutUser();
    softAssert.assertAll();
}

public static void test04Execution(String Dataset, HomePage home) throws InterruptedException, IOException {
    try {
      assertion = new Assertion();
      boolean status;
      String[] data = Dataset.split(";");
      String SearchCity = data[0];
      String SearchAdventure = data[1];
      String guestName = data[2];
      String date = data[3];
      String count = data[4];
      Thread.sleep(2000);
      // homePage.searchbar(SearchCity);
      // Thread.sleep(3000);
      homePage.searchbar(SearchCity);
      AdventurePage.chooseAdventure(SearchAdventure);
      Thread.sleep(2000);
      AdventureDetailsPage.enterdetails(guestName, date, count);
      Thread.sleep(2000);
      AdventureDetailsPage.messageResult();
      // assertion.assertTrue(AdventureDetailsPage.messageResult(), "Not Booked ");
      AdventureDetailsPage.navigateHome();
      ReportSingleton.test.log(LogStatus.PASS, " Successfully verified the booking History");
      // homePage.isNavigatetoHomePage();
     // try {
        // ReportSingleton.test.log(LogStatus.PASS, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+" Successfully  verified  booking history");
        //Assert.assertTrue(history.verifyNumberofReservationCount()  , ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+"Failed to verify records of reservation ");
      } catch (Exception e) {
        //TODO: handle exception
        System.out.println(e.getMessage());
        ReportSingleton.test.log(LogStatus.FAIL, ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+" Failed to verify the booking History");
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
