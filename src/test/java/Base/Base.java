package Base;

import Basics.JsonReader;
import Pages.LandingPage;
import Pages.LoginPage;
import Utilities.BrowserFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Base extends BrowserFactory {

    static JsonReader   reader ;
    public static String Url,Browser;
    public static LandingPage landingObj;;
    public static LoginPage loginObj;

    @BeforeSuite
    public static  void SetUp() throws IOException {
        reader = new JsonReader("src/test/resources/ConfigFiles/Config.json");
        Url = reader.getValue("Url");
        Browser = reader.getValue("Browser");
        setDriver(Browser);
        navigateToUrl(Url);

        landingObj = new LandingPage(getDriver());
        loginObj = new LoginPage(getDriver());

    }

    static {
        try {
            LogManager.getLogManager().readConfiguration(
                    new FileInputStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public  void tearDown(){
        getDriver().close();
        getDriver().quit();
    }

}
