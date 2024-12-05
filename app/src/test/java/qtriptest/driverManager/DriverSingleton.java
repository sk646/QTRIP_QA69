package qtriptest.driverManager;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
  private static RemoteWebDriver driver = null;
  
  private DriverSingleton(){}

  public static RemoteWebDriver getDriver(String browser) throws MalformedURLException{
   if(driver == null){

    switch(browser) {
      case "chrome":
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-Sandbox");
        options.addArguments("--disable-gpu");
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),options);
        break;

        case "firefox":
        FirefoxOptions ffoptions = new FirefoxOptions();
        ffoptions.addArguments("--no-Sandbox");
        ffoptions.addArguments("--disable-gpu");
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),ffoptions);
        break;  

     }
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();

   }
   return driver;
  }
  public static void launchApp(String AppURL){
    driver.get(AppURL);
  }

//   public static void closeApp(){
//     driver.quit();
// }
}
