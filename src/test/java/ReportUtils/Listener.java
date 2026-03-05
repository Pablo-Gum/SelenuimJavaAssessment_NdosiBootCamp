package ReportUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Utilities.BrowserFactory.getDriver;
import static Utilities.Screenshots.getBase64Screenshot;

public class Listener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // Function description: Initialize the ExtentReports instance when the test suite starts
    @Override
    public void onStart(ITestContext context) {
        extent = ExtentReportManager.extentReports();
    }

    // Function description: Create a new ExtentTest instance for each test method and assign it to the ThreadLocal variable
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(result.getMethod().getMethodName()).assignAuthor("Pablo-Automation");
        extentTest.set(test);
    }

    // Function description: Log the test failure, capture a screenshot, and attach it to the report
    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = extentTest.get();

        if (test != null) {

            test.fail(result.getThrowable());

            WebDriver driver = getDriver();

            String screenshot = getBase64Screenshot(driver);

            test.fail(result.getMethod().getMethodName()+ ": Test Details - Failure Screenshot captured");
            test.addScreenCaptureFromBase64String(screenshot, result.getMethod().getMethodName()+ ":  Failure Screenshot");
        }
    }

    // Function description: Log the test success, capture a screenshot, and attach it to the report
    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = extentTest.get();
        if (test != null) {

            test.pass(result.getThrowable());

            WebDriver driver = getDriver();

            String screenshot = getBase64Screenshot(driver);

            test.pass(result.getMethod().getMethodName()+ ": Test Details - Successful Screenshot captured");
            test.addScreenCaptureFromBase64String(screenshot, result.getMethod().getMethodName()+":  Successful Screenshot");
        }
    }

    // Function description: Log the test skip, capture a screenshot, and attach it to the report
    @Override
    public void onTestSkipped(ITestResult result){

        ExtentTest test = extentTest.get();
        if (test != null) {

            test.skip(result.getThrowable());

            WebDriver driver = getDriver();

            String screenshot = getBase64Screenshot(driver);

            test.skip(result.getMethod().getMethodName()+ ": Test Details - Skipped Screenshot captured");
            test.addScreenCaptureFromBase64String(screenshot, "Skipped Screenshot");
        }
    }

    // Function description: Flush the ExtentReports instance to write all the logs and screenshots to the report file at the end of the test suite
    @Override
    public void onFinish(ITestContext context) {
        extent.flush();// Flush everything at the end
    }
}