package Base;

import Basics.DataFunction;
import Basics.ExcelReader;
import Basics.JsonReader;
import Basics.ReportingUtils;
import Pages.*;
import Utilities.BrowserFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.time.Duration;

import static Basics.ExcelReader.getCellData;

public class Base extends BrowserFactory {
    static JsonReader   reader ;
    public static String Url,Browser;
    protected static ExtentTest node;
    protected static ExtentReports repo;
    DataFunction dataFunction = new DataFunction();
    public static LandingPage landingObj;;
    public static LoginPage loginObj;
    public static LearnPage learnObj;
    public static InventoryPage inventoryObj;
    public static OrderPreviewPage orderPreviewObj;

    // This method will run before the entire test suite and will set up the WebDriver, navigate to the URL, and initialize the page objects.
    @BeforeSuite
    public static  void SetUp() throws IOException {
        reader = new JsonReader("src/test/resources/ConfigFiles/Config.json");
        Url = reader.getValue("Url");
        Browser = reader.getValue("Browser");
        setDriver(Browser);
        navigateToUrl(Url);

        repo = ReportingUtils.initializeExtentReports("Reports/Pablo_NdosiBootCamp_Assessment.html");
        ExtentTest test = repo.createTest("Pablo_De_Legend").assignAuthor("Pablo");
        node = test.createNode("MyNode");

        landingObj = new LandingPage(getDriver());
        loginObj = new LoginPage(getDriver());
        learnObj = new LearnPage(getDriver());
        inventoryObj = new InventoryPage(getDriver());
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
        repo.flush();
        getDriver().close();
        getDriver().quit();
    }

    // Method to switch to the newly opened tab
    public  static void switchToNewTab() {

        String parent = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(d -> d.getWindowHandles().size() > 1);

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }



}
