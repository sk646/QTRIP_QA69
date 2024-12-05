// package qtriptest.utility;

// import java.util.HashMap;
// import java.util.Map;
// import com.relevantcodes.extentreports.ExtentReports;
// import com.relevantcodes.extentreports.ExtentTest;
// import com.relevantcodes.extentreports.LogStatus;

// public class ExtentTestManager {
//     static ExtentReports extentReports= ExtentManager.getReport();
//     static Map<Object,Object> extentTestMap = new HashMap<>();

//  public static ExtentTest startTest(String TestName) {
//     ExtentTest extentTest = extentReports.startTest(TestName);
//     extentTestMap.put((int)(long)Thread.currentThread().getId(),extentTest);
//     return extentTest;

//  }
//  public static ExtentTest getTest(){
//    return(ExtentTest) extentTestMap.get((int)(long)Thread.currentThread().getId());
//  }
//  public static void testLogger(LogStatus status,String desciption){
//    getTest().log(status, desciption);
//  }

//  public static void endTest(){
//   extentReports.endTest(getTest());
//  }

// }
