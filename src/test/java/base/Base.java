package base;

import Basics.DataFunction;
import Basics.JsonReader;
import Pages.*;
import Utilities.BrowserFactory;
import Utilities.Screenshots;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.time.Duration;

public class Base extends BrowserFactory {

    static JsonReader   reader ;
    public static String Url,Browser;
    public static Screenshots screenshots;
    DataFunction dataFunction = new DataFunction();
    public static LandingPage landingObj;;
    public static LoginPage loginObj;
    public static LearnPage learnObj;
    public static InventoryPage inventoryObj;
    public static OrderPreviewPage orderPreviewObj;
    public static LogoutPage logoutObj;

    // This method will run before the entire test suite and will set up the WebDriver, navigate to the URL, and initialize the page objects.
    @BeforeSuite
    public static  void SetUp() throws IOException {
        reader = new JsonReader("src/test/resources/ConfigFiles/Config.json");
        Url = reader.getValue("Url");
        Browser = reader.getValue("Browser");
        setDriver(Browser);
        navigateToUrl(Url);
        screenshots = new Screenshots();

        landingObj = new LandingPage(getDriver());
        loginObj = new LoginPage(getDriver());
        learnObj = new LearnPage(getDriver());
        inventoryObj = new InventoryPage(getDriver());
        orderPreviewObj = new OrderPreviewPage(getDriver());
        logoutObj = new LogoutPage(getDriver());

    }

    // This method will run before each test method and will read the test data from the Excel file and store it in the respective variables for use in the test methods.
    @BeforeMethod
    public void testData() throws IOException {
        dataFunction.excelTestData();
    }

    // This method will run after each test method and will close and quit the WebDriver to ensure a clean state for the next test.
    @AfterSuite
    public  void tearDown(){
        getDriver().close();
        getDriver().quit();
    }

}
