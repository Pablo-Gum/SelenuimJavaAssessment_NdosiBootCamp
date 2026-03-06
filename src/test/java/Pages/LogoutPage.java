package Pages;

import Basics.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import static Utilities.Screenshots.getBase64Screenshot;
import static base.Base.Url;

public class LogoutPage extends Actions {
    public WebDriver driver;
    public LogoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@class='nav-user-section']")
    WebElement logoutBtn;

    @FindBy(xpath = "//button[contains(@class,'nav-dropdown-item')]//span[text()='Logout']")
    WebElement logoutOption;

    @FindBy (xpath = "//*[@id='overview-hero']")
    WebElement logoutPageHeroTxt;

    public  void logout(){
        switchToNewTab(driver);
        ClickObject(logoutBtn, driver);
        Explicit_Wait(logoutOption, driver, "element_Clickable",10);
        ClickObject( logoutOption, driver);
        HandleAlert(driver);
    }

    public void validateLogout(){
        waitForPageToLoad(driver, 10);
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(Url)) {
            Explicit_Wait(logoutPageHeroTxt, driver, "element_Visible", 10);
            logoutPageHeroTxt.isDisplayed();
            getBase64Screenshot(driver);
            System.out.println("Logout validation successful: User is redirected to the expected URL after logout.");
        } else {
            System.out.println("Logout validation failed: User is not redirected to the expected URL after logout. Current URL: " + currentUrl);
        }
    }

}
