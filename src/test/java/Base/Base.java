package Base;

import Basics.DataFunction;
import Basics.ExcelReader;
import Basics.JsonReader;
import Pages.*;
import Utilities.BrowserFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

import static Basics.ExcelReader.getCellData;

public class Base extends BrowserFactory {
    static JsonReader   reader ;
    public static String Url,Browser;
    DataFunction dataFunction = new DataFunction();
    public static LandingPage landingObj;;
    public static LoginPage loginObj;
    public static LearnPage learnObj;
    public static WebAutoAdvancedPage webAutoAdvancedObj;
    public static OrderPreviewPage orderPreviewObj;

    // This method will run before the entire test suite and will set up the WebDriver, navigate to the URL, and initialize the page objects.
    @BeforeSuite
    public static  void SetUp() throws IOException {
        reader = new JsonReader("src/test/resources/ConfigFiles/Config.json");
        Url = reader.getValue("Url");
        Browser = reader.getValue("Browser");
        setDriver(Browser);
        navigateToUrl(Url);
        landingObj = new LandingPage(getDriver());
        loginObj = new LoginPage(getDriver());
        learnObj = new LearnPage(getDriver());
        webAutoAdvancedObj = new WebAutoAdvancedPage(getDriver());
        orderPreviewObj = new OrderPreviewPage(getDriver());

    }

    // This method will run before each test method and will read the test data from the Excel file and store it in the respective variables for use in the test methods.
    @BeforeMethod
  public void testData() throws IOException {
        dataFunction.excelTestData();
    }

    // This method will run after each test method and will close and quit the WebDriver to ensure a clean state for the next test.
    @AfterMethod
    public  void tearDown(){
        getDriver().close();
        getDriver().quit();
    }



}
