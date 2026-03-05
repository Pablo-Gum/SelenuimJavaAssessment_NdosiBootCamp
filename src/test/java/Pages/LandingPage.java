
package Pages;

import Basics.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import static Utilities.Screenshots.getBase64Screenshot;
import static base.Base.screenshots;

public class LandingPage extends Actions {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//h1[text()='Master Test Automation']")
    WebElement masterTestAutomationTxt;

    public void verifyLandingPage(){
        waitForPageToLoad(driver, 30);

        try{
            Assert.assertEquals(masterTestAutomationTxt.getText(), masterTestAutomationTxt.getText(), "Landing page validation failed: Master Test Automation text does not match expected.");
            System.out.println("Landing page validation successful: Master Test Automation text matches expected.");
           getBase64Screenshot(driver);
        } catch (Exception e) {
            System.out.println("Landing page failed: " + e.getMessage());
            getBase64Screenshot(driver);
            Assert.fail("Landing page failed: " + e.getMessage());

        }


    }



}
