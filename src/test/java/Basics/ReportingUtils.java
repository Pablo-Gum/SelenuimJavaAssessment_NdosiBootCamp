package Basics;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static Basics.Actions.waitForPageToLoad;

public class ReportingUtils {

    // Function description: Initialize extent report
    public static ExtentReports initializeExtentReports(String ReportName) {
        ExtentReports extent =  new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(ReportName);

        extent.attachReporter(spark);
        return extent;

    }
    // Function description: CaptureScreenshot as Base64 string
    public static String getBase64Screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    // Function description: Add screenshot to the report with a message
    public static void addScreenshotToReport(WebDriver driver, ExtentTest test, String message) {
        test.info(
                message,
                MediaEntityBuilder
                        .createScreenCaptureFromBase64String(
                                getBase64Screenshot(driver))
                        .build()
        );
    }

    // Function description: Navigate to a page, wait for it to load, and add a screenshot to the report
    public static void navigateAndReport(WebDriver driver, ExtentTest test, String pageName) {
        //driver.get(url);
        waitForPageToLoad(driver, 30);

        addScreenshotToReport(
                driver,
                test,
                pageName + " page loaded"
        );
    }
}
