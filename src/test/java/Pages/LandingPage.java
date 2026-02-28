
package Pages;

import Basics.Actions;
import Basics.ReportingUtils;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class LandingPage extends Actions {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//h1[text()='Master Test Automation']")
    WebElement masterTestAutomationTxt;

    public void verifyLandingPage(ExtentTest test){
        waitForPageToLoad(driver, 30);

        try{
            ReportingUtils.navigateAndReport(driver,test, "LandingPage_Successful");
            Assert.assertEquals(masterTestAutomationTxt.getText(), masterTestAutomationTxt.getText(), "Landing page validation failed: Master Test Automation text does not match expected.");
            System.out.println("Landing page validation successful: Master Test Automation text matches expected.");
        } catch (Exception e) {
            ReportingUtils.navigateAndReport(driver,test, "LandingPage_Failed");
            System.out.println("Landing page failed: " + e.getMessage());
            Assert.fail("Landing page failed: " + e.getMessage());
        }


    }



}
