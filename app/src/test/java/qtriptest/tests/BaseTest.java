// package qtriptest.tests;

// import qtriptest.driverManager.DriverSingleton;
// import qtriptest.utility.ExtentManager;
// import qtriptest.utility.ExtentTestManager;
// import java.io.IOException;
// import java.lang.reflect.Method;
// import java.net.MalformedURLException;
// import com.relevantcodes.extentreports.LogStatus;
// import org.openqa.selenium.remote.RemoteWebDriver;
// import org.testng.ITestResult;
// import org.testng.annotations.AfterGroups;
// import org.testng.annotations.AfterMethod;
// import org.testng.annotations.AfterSuite;
// import org.testng.annotations.BeforeMethod;
// import org.testng.annotations.BeforeSuite;
// import org.testng.annotations.BeforeTest;

// public class BaseTest {
//   RemoteWebDriver driver;

//     @BeforeSuite(alwaysRun = true)
//     public void intiTest() throws MalformedURLException{
//     driver = DriverSingleton.getDriver("Chrome");
//     DriverSingleton.launchApp("https://qtripdynamic-qa-frontend.vercel.app/");
//     }

//     @BeforeMethod(alwaysRun = true)
//     public void startStep(Method method){
//      ExtentTestManager.startTest(method.getName());
//     }

//     @AfterMethod
//     public void endStep(ITestResult iTestResult) throws IOException, InterruptedException{
//         if(iTestResult.getStatus()== ITestResult.SUCCESS){
//             ExtentTestManager.testLogger(LogStatus.PASS, "Test is passed");
//         }else if(iTestResult.getStatus()== ITestResult.FAILURE){
//             ExtentTestManager.testLogger(LogStatus.FAIL, "Test is failed");
//             ExtentTestManager.getTest().addScreenCapture(qtriptest.utility.SeleniumUtils.capture(driver));   
            
//         }else{
//             ExtentTestManager.testLogger(LogStatus.SKIP, "Test is skipped");
//         }
//         ExtentTestManager.endTest();
//         DriverSingleton.closeApp();
//     }

//     @AfterSuite(alwaysRun = true)
//     public void endTest(){
//         driver.quit();
//         ExtentTestManager.endTest();
//         ExtentManager.getReport().flush();
//     }
// }
