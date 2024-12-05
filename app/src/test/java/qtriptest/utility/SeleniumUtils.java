// package qtriptest.utility;

// import java.io.File;
// import java.io.IOException;
// import org.apache.commons.io.FileUtils;
// import org.openqa.selenium.OutputType;
// import org.openqa.selenium.TakesScreenshot;
// import org.openqa.selenium.remote.RemoteWebDriver;

// public class SeleniumUtils {
    
//   public static String capture(RemoteWebDriver driver) throws InterruptedException, IOException{
//    File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//    File destfile = new File(System.getProperty("user.dir")+"/repots/"+System.currentTimeMillis()+".Png");
//    String errflpath = destfile.getAbsolutePath();
//    FileUtils.copyFile(srcfile,destfile);
//    System.out.println(errflpath);
//    return errflpath;
    
//   }
// }
