package qtriptest;

import qtriptest.ReportSingleton;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ReportSingleton {
    public static ExtentReports report;
    public static ExtentTest test;

    private ReportSingleton() {}

    public static ExtentReports getReport() {
        if (report == null) {
            report = new ExtentReports(System.getProperty("user.dir") + "extentReport.html"+System.currentTimeMillis());
            // report.loadConfig(
            //         new File(System.getProperty("user.dir") + "/extent_customization_configs.xml"));
        //    test=report.startTest("Qtrip Automation Report");         
        }
        return report;
    }

    public static String capture(RemoteWebDriver driver) throws InterruptedException, IOException{
        File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destfile = new File(System.getProperty("user.dir")+"/images/"+System.currentTimeMillis()+".Png");
        String errflpath = destfile.getAbsolutePath();
        FileUtils.copyFile(srcfile,destfile);
        System.out.println(errflpath);
        return errflpath;
         
       }
    }
