package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public  final class Screenshots {

    static final String screenshotPath = System.getProperty("user.dir") + "/target/Screenshots";

    public void captureScreenshot(WebDriver driver, String screenshotName) {
        TakesScreenshot  takesScreenshot = (TakesScreenshot) driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenshotPath+ screenshotName + ".jpeg");

        try {
            FileUtils.copyFile(file, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static String getBase64Screenshot(WebDriver driver) {
        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BASE64);
    }
}
