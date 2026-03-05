package ReportUtils;

import Basics.ReportingUtils;
import Utilities.BrowserFactory;
import Utilities.Screenshots;
import base.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Utilities.BrowserFactory.getDriver;

public class Listener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    @Override
    public void onStart(ITestContext context) {
        extent = ExtentReportManager.extentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(result.getMethod().getMethodName()).assignAuthor("Pablo-Automation");
        extentTest.set(test);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = extentTest.get();

        if (test != null) {

            test.fail(result.getThrowable());

            Object currentClass = result.getInstance();
            WebDriver driver = getDriver();

            String screenshot =
                    ReportingUtils.getBase64Screenshot(driver);

            test.addScreenCaptureFromBase64String(
                    screenshot,
                    result.getMethod().getMethodName()+ ":  Failure Screenshot"
            );
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = extentTest.get();
        if (test != null) {

            test.pass(result.getThrowable());

            Object currentClass = result.getInstance();
            WebDriver driver = getDriver();

            String screenshot =
                    ReportingUtils.getBase64Screenshot(driver);

            test.addScreenCaptureFromBase64String(
                    screenshot,
                     result.getMethod().getMethodName()+":  Successful Screenshot"
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result){

        ExtentTest test = extentTest.get();
        if (test != null) {

            test.skip(result.getThrowable());

            Object currentClass = result.getInstance();
            WebDriver driver = getDriver();

            String screenshot =
                    ReportingUtils.getBase64Screenshot(driver);

            test.addScreenCaptureFromBase64String(
                    screenshot,
                    "Skipped Screenshot"
            );
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();  // Flush everything at the end
    }
}