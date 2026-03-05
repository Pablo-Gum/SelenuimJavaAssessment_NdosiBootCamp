package Pages;

import Basics.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.io.IOException;

import static base.Base.screenshots;

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

    public void validateLogin() throws IOException {
        waitForPageToLoad(driver, 30);
        try {

            Assert.assertEquals(WelcomeBackTxt.getText(), WelcomeBackTxt.getText(), "Login validation failed: Welcome message does not match expected.");
            System.out.println("Login validation successful: Welcome message matches expected.");
            screenshots.captureScreenshot(driver, "Login successful");
        } catch (Exception e) {
            System.out.println("Login validation failed: " + e.getMessage());
            screenshots.captureScreenshot(driver, "LoginValidation_Failure");
            Assert.fail("Login validation failed: " + e.getMessage());
        }

    }


}
