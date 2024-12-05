package qtriptest.tests;

import qtriptest.ReportSingleton;
import qtriptest.driverManager.DriverSingleton;
import qtriptest.pages.AdventurePage;
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

public class testCase_02 {
    RemoteWebDriver driver;
    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    AdventurePage adventurePage;
    Assertion assertion;
    private static final int pageLoadTimeout = 20;

 public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
        String.valueOf(java.time.LocalDateTime.now()), type, message, status));
 } 

@BeforeSuite
public void setup() throws MalformedURLException{
     
    // RemoteWebDriver driver = new ChromeDriver();
    driver=DriverSingleton.getDriver("chrome");
    DriverSingleton.launchApp("https://qtripdynamic-qa-frontend.vercel.app/");
    ReportSingleton.report= ReportSingleton.getReport();
    
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    // driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    
    homePage = new HomePage(driver);
    adventurePage = new AdventurePage(driver);
    }

@Test(dataProvider = "QtripCityData",dataProviderClass = ExternalDataProvider.class,description="Searching for places in Home page",
priority=2,groups = {"Search and Filter Flow"})
public void TestCase02(String CityName,String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws InterruptedException, IOException{
    assertion = new Assertion();
    boolean status;
    ReportSingleton.test=ReportSingleton.report.startTest( "Search and Filter Flow");
    // homePage.isNavigatetoHomePage();
    // Thread.sleep(2000);
    // homePage.searchbar("Hyderabad");
    // Thread.sleep(3000);
    // status = homePage.iscityNotFound();
    // assertion.assertFalse(status, "Places found");
    // Thread.sleep(3000);
    try{
    System.out.println("Searching City");
    homePage.searchbar(CityName);
    // homePage.iscityFoundOrnot();
    Thread.sleep(2000);
    // softAssert.assertTrue(adventurePage.isNavigateToadventurePage(),"Not navigated to adventurePage");
    // status = homePage.iscityFoundOrnot();
    // assertion.assertTrue(status,"not city found");
    // softAssert.assertTrue(homePage.resultDisplay(),"not visible");
    // Thread.sleep(2000);
    adventurePage.filterpage(DurationFilter);
    adventurePage.categorychoose(Category_Filter);
    adventurePage.verifyAdventureContents(ExpectedFilteredResults);
    adventurePage.verifyAdventureContents(ExpectedUnFilteredResults);
    Thread.sleep(3000);
    adventurePage.clearfilter();
    driver.navigate().back();
    // softAssert.assertTrue(adventurePage.entirepageData,"not displayed search city data");
    // softAssert.assertAll();
    ReportSingleton.test.log(LogStatus.PASS, "Succesfully Search and Filter Flow ");
}catch (Exception e) {
    //TODO: handle exception
    ReportSingleton.test.log(LogStatus.FAIL,ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+ "Failed to Verify Search and Filter flow ");
    
    }
}

@AfterSuite(alwaysRun = true)
public void teardown()throws MalformedURLException{
    driver.quit();
    System.out.println("Application closed");
    ReportSingleton.report.endTest(ReportSingleton.test);
    ReportSingleton.report.flush();
}

}
