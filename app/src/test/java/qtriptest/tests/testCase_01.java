package qtriptest.tests;

import qtriptest.ReportSingleton;
import qtriptest.driverManager.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_01 {
RemoteWebDriver driver;
HomePage homePage;
RegisterPage registerPage;
LoginPage loginPage;
SoftAssert softAssert;
private static final int pageLoadTimeout = 20;

@BeforeSuite(alwaysRun = true)
public void setup() throws MalformedURLException {
 
// RemoteWebDriver driver = new ChromeDriver();
driver=DriverSingleton.getDriver("chrome");
DriverSingleton.launchApp("https://qtripdynamic-qa-frontend.vercel.app/");
ReportSingleton.report= ReportSingleton.getReport();

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

homePage = new HomePage(driver);
registerPage = new RegisterPage(driver);
loginPage = new LoginPage(driver,registerPage );

}

@Test(dataProvider = "QtripData",dataProviderClass = ExternalDataProvider.class,description="New User registration and validating login and logout",
 priority = 1,groups = {"Login Flow"})
public void TestCase01(String username,String password) throws InterruptedException, IOException{
 try {
 ReportSingleton.test=ReportSingleton.report.startTest( "New User registration and validating login and logout");
 softAssert = new SoftAssert();
 softAssert.assertTrue(homePage.isRegisterbuttonVisible(),"Register button not found");

 homePage.navigateToRegistrationPage();

 softAssert.assertTrue(registerPage.isNavigatetoRegisterpage(),"Registerpage navigation is failed");

 registerPage.NewuserOnboard(username,password,password,true);

 softAssert.assertTrue(loginPage.isNavigatetoLoginpage(),"Login page navigation is failed");

 loginPage.performlogin(username,password,true);

 homePage.logoutButtonIsvisible();
 Thread.sleep(3000);
 ReportSingleton.test.log(LogStatus.PASS, "Sucessfully Login");
  //logout from application
 homePage.LogoutUser();
 softAssert.assertAll();

} catch (Exception e) {
    //TODO: handle exception

    ReportSingleton.test.log(LogStatus.FAIL,  ReportSingleton.test.addScreenCapture(ReportSingleton.capture(driver))+" Failed To  Login");
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
