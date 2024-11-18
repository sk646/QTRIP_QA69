package qtriptest.tests;

import qtriptest.driverManager.DriverSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
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

@BeforeSuite
public void setup() throws MalformedURLException{
 
// RemoteWebDriver driver = new ChromeDriver();
driver=DriverSingleton.getDriver("chrome");
DriverSingleton.launchApp("https://qtripdynamic-qa-frontend.vercel.app/");

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

homePage = new HomePage(driver);
registerPage = new RegisterPage(driver);
loginPage = new LoginPage(driver,registerPage );

}

@Test(dataProvider = "QtripData",dataProviderClass = ExternalDataProvider.class,description="New User registration and validating login and logout")
public void TestCase01(String username,String password) throws InterruptedException{
 softAssert = new SoftAssert();

 softAssert.assertTrue(homePage.isRegisterbuttonVisible(),"Register button not found");

 homePage.navigateToRegistrationPage();

 softAssert.assertTrue(registerPage.isNavigatetoRegisterpage(),"Registerpage navigation is failed");

 registerPage.NewuserOnboard(username,password,password,true);

 softAssert.assertTrue(loginPage.isNavigatetoLoginpage(),"Login page navigation is failed");

 loginPage.performlogin(username,password,true);

 homePage.logoutButtonIsvisible();
 //logout from application
 homePage.LogoutUser();
 softAssert.assertAll();

}

@AfterSuite
public void teardown()throws MalformedURLException{
    driver.quit();
    System.out.println("Application closed");
}

}
