package Basics;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class Actions {

    // Click Method
    public void ClickObject(WebElement element, WebDriver driver){
        try
        {
            if (element.isDisplayed())
            {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofMillis(1000))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(element));
                element.click();
            }
        } catch (Exception e){
            System.out.println(element + "Not found");
        }


    }

    // Select Method
    public void SelectObject(WebElement selectElement,   WebDriver driver, String selectBy, Object selectValue){
        try {
            if (selectElement.isDisplayed())
            {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofMillis(1000))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(selectElement));

                Select select = new Select(selectElement);
                switch(selectBy.toLowerCase()){

                    case " index" :
                        select.selectByIndex((Integer)selectValue);
                        break;

                    case "visibletext":
                        select.selectByVisibleText((String)selectValue);
                        break;

                    case "value" :
                        select.selectByValue((String)selectValue);
                        break;


                }
            }



        }catch (Exception e){
            System.out.println(selectElement + "Not Found");
        }
    }

    public void EnterValue(WebElement textBox,  WebDriver driver, String data){
        try {
            if (textBox.isDisplayed()) {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(2))
                        .pollingEvery(Duration.ofMillis(500))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(textBox));

                textBox.clear();
                textBox.sendKeys(data);
                textBox.sendKeys(Keys.TAB);
                textBox.sendKeys(Keys.ENTER);
            }
        } catch (Exception e){
            System.out.println(textBox +"Not Found");
        }
    }

    // SendKeys Method
    public void SendKeys(WebElement textBox,  WebDriver driver, String data){
        try {
            if (textBox.isDisplayed()) {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofMillis(1000))
                        .ignoring(WebDriverException.class);
                wait.until(ExpectedConditions.elementToBeClickable(textBox));

                textBox.clear();
                textBox.sendKeys(data);
            }
        } catch (Exception e){
            System.out.println(textBox +"Not Found");
        }
    }

    // SendKeys Method with JavaScriptExecutor fallback
    public void SendKeysJS(WebElement textBox, WebDriver driver, String data) {

        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(5))
                    .pollingEvery(Duration.ofMillis(1000))
                    .ignoring(WebDriverException.class);

            wait.until(ExpectedConditions.visibilityOf(textBox));
            wait.until(ExpectedConditions.elementToBeClickable(textBox));

            if (textBox.isDisplayed()) {

                // Scroll element into view
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView(true);", textBox);

                try {
                    // Try normal Selenium first
                    textBox.clear();
                    textBox.sendKeys(data);

                } catch (Exception e) {

                    // Fallback to JavaScript if sendKeys fails
                    js.executeScript("arguments[0].value='';", textBox);
                    js.executeScript("arguments[0].value=arguments[1];", textBox, data);

                    // Trigger change event (important for React/Angular apps)
                    js.executeScript(
                            "arguments[0].dispatchEvent(new Event('change'));",
                            textBox
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Element Not Found: " + textBox);
        }
    }

    // Explicit_Wait Method - Waits for element with custom timeout
    public void Explicit_Wait(WebElement element, WebDriver driver, String waitType, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

            switch (waitType.toUpperCase()) {
                //case "VISIBLE":
                case "ELEMENT_VISIBLE":
                    wait.until(ExpectedConditions.visibilityOf(element));
                    break;

               // case "CLICKABLE":
                case "ELEMENT_CLICKABLE":
                    wait.until(ExpectedConditions.elementToBeClickable(element));
                    break;

                //case "PRESENT":
                case "ELEMENT_PRESENT":
                    // Wait for element to be present in DOM (not necessarily visible)
                    wait.until(d -> {
                        try {
                            element.getTagName();
                            return true;
                        } catch (StaleElementReferenceException e) {
                            return false;
                        }
                    });
                    break;

                //case "INVISIBLE":
                case "ELEMENT_INVISIBLE":
                    wait.until(ExpectedConditions.invisibilityOf(element));
                    break;

                default:
                    throw new IllegalArgumentException("Invalid wait type: " + waitType +
                            ". Supported types: VISIBLE, CLICKABLE, PRESENT, INVISIBLE");
            }
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for element (" + waitType + ") after " + timeoutSeconds + " seconds: " + element);
            throw new RuntimeException("Element wait timeout for type: " + waitType, e);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid wait type provided: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error while waiting for element: " + e.getMessage());
            throw new RuntimeException("Wait operation failed for element", e);
        }

    }

    // Implicit_wait Method - Sets implicit wait globally for the driver
    public void Implicitly_Wait(WebElement element, WebDriver driver) {
        try {
            // Set implicit wait timeout (recommended: 10-15 seconds)
            final int IMPLICIT_WAIT_SECONDS = 10;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));

            // Use explicit wait with the element to ensure it's present and visible
            WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
            explicitWait.until(ExpectedConditions.visibilityOf(element));

        } catch (TimeoutException e) {
            System.err.println("Element not visible within timeout: " + element);
            throw new RuntimeException("Element visibility timeout", e);
        } catch (Exception e) {
            System.err.println("Error waiting for element: " + e.getMessage());
            throw new RuntimeException("Wait operation failed", e);
        }
    }

    // wait for page to load completely
    public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                .until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }

}
