package ReportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

     private static final String reportPath=System.getProperty("user.dir") +"/target/Reports/Pablo_AutomationReport.html";

     private static ExtentReports extentReports;
     private static ExtentSparkReporter extentSparkReporter;


     public static ExtentReports extentReports(){

          if (extentReports == null) {
               extentSparkReporter = new ExtentSparkReporter(reportPath);
               extentReports= new ExtentReports();
               extentReports.attachReporter(extentSparkReporter);
          }
          extentSparkReporter.config().setDocumentTitle("Pablo Extent Report Title");
          extentSparkReporter.config().setTheme(Theme.DARK);
          extentSparkReporter.config().setReportName("PabloAutomation Report ");

          extentReports.setSystemInfo("OS", System.getProperty("os.name"));
          extentReports.setSystemInfo("Execution Machine", System.getProperty("user.name"));

          return extentReports;

     }


}
