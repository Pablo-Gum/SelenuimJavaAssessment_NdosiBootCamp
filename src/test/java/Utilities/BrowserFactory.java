package Utilities;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {

    public  static WebDriver driver;

    // Get Driver method
    public  static WebDriver getDriver() {
        return driver;
    }

    // Set Driver method
    public static WebDriver setDriver(String browserChoice) {
        switch (browserChoice.toLowerCase()) {
            case "edge":
                driver = new org.openqa.selenium.edge.EdgeDriver();
                break;
            case "internetexplore":
                driver = new org.openqa.selenium.ie.InternetExplorerDriver();
                break;
            case "firefox":
                driver = new org.openqa.selenium.firefox.FirefoxDriver();
                break;
            case "safari":
                driver = new org.openqa.selenium.safari.SafariDriver();
                break;
            default:
                driver =  new org.openqa.selenium.chrome.ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }

    // navigate to url method
    public static void navigateToUrl(String url){
        driver.manage().deleteAllCookies();
        driver.get(url);
    }
}
