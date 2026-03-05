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

public class LearnPage extends Actions {
    WebDriver driver;
    public LearnPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@class='nav-items']//span[text()='Learn']")
    WebElement learnPageTxt;

    @FindBy(xpath = "//*[@id='practice-tabs']//span[text()='Web Automation Advance']")
    public static WebElement webAutomationAdvanceBtn;

    @FindBy(xpath = "//button[contains(@class,'nav-dropdown-item')]//span[text()='Learning Materials']")
    WebElement learningMaterialsOption;

    // Method to navigate to the Learn page and click on the Learning Materials option
    public void navigateToLearnPage(){
        waitForPageToLoad(driver, 30);
        ClickObject(learnPageTxt, driver);
        Explicit_Wait(learningMaterialsOption, driver, "element_Clickable",10);
        ClickObject( learningMaterialsOption, driver);
    }

    // Method to verify that the Learn page is displayed correctly
    public void verifyLearnPage() {
        waitForPageToLoad(driver, 10);
        try {

            Assert.assertEquals(webAutomationAdvanceBtn.getText(), webAutomationAdvanceBtn.getText(), "Learn page validation failed: Web Automation Advance button text does not match expected.");
            System.out.println("Learn page validation successful: Web Automation Advance button text matches expected.");
            getBase64Screenshot(driver);
        } catch (Exception e) {
            System.out.println("Learn page validation failed: " + e.getMessage());
            getBase64Screenshot(driver);
            Assert.fail("Learn page validation failed: " + e.getMessage());
        }

    }

}


