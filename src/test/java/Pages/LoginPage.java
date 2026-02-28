package Pages;

import Basics.Actions;
import Basics.DataFunction;
import Basics.ReportingUtils;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.io.IOException;

import static Basics.Actions.waitForPageToLoad;

public class LoginPage extends Actions {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy(xpath = "//*[@class='user-pill']/child::span[contains(.,'Login')] ")
    WebElement LoginBtn;

    @FindBy(xpath = "//*[@id='login-email']")
    WebElement Username;

    @FindBy(xpath = "//*[@id='login-password']")
    WebElement Password;

    @FindBy(xpath = "//*[@id='login-submit']")
    WebElement SubmitBtn;

    @FindBy(xpath = "//h2[contains(.,'Welcome back')]")
    WebElement WelcomeBackTxt;

    public void login(String username, String password) throws IOException {

        waitForPageToLoad(driver, 30);
        ClickObject(LoginBtn,driver);
        SendKeysJS(Username,  driver, username);
        SendKeysJS(Password, driver, password);
        ClickObject(SubmitBtn, driver);
    }

    public void validateLogin(ExtentTest test) throws IOException {
        waitForPageToLoad(driver, 30);
        try {
            ReportingUtils.navigateAndReport(driver, test, "Login  successful");
            Assert.assertEquals(WelcomeBackTxt.getText(), WelcomeBackTxt.getText(), "Login validation failed: Welcome message does not match expected.");
            System.out.println("Login validation successful: Welcome message matches expected.");
        } catch (Exception e) {
            ReportingUtils.navigateAndReport(driver, test, "Login  failed: " + e.getMessage());
            System.out.println("Login validation failed: " + e.getMessage());
            Assert.fail("Login validation failed: " + e.getMessage());
        }

    }


}
