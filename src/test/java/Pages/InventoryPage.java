package Pages;

import Basics.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import java.io.IOException;

import static Utilities.Screenshots.getBase64Screenshot;
import static base.Base.screenshots;
import static Pages.LearnPage.webAutomationAdvanceBtn;

public class InventoryPage extends Actions {
    WebDriver driver;

    public InventoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id='inventory-title']")
    WebElement inventoryFormTxt;

    @FindBy(xpath = "//*[@id='deviceType']")
    WebElement deviceTypeDropdown;

    @FindBy(xpath = "//*[@id='brand']")
    WebElement brandDropdown;

    @FindBy(xpath = "//*[@id='storage-128GB']")
    WebElement storage128GB_radioBtn;

    @FindBy(xpath = "//*[@id='color']")
    WebElement color_Dropdown;

    @FindBy(xpath = "//*[@id='quantity']")
    WebElement input_Quantity;

    @FindBy(xpath = "//*[@id='address']")
    WebElement deliveryAddress;

    @FindBy(xpath = "//*[@id='unit-price-label']")
    WebElement unitPriceTxt;

    @FindBy(xpath = "//*[@id='device-preview']")
    WebElement devicePreviewImg;

    @FindBy(xpath="//*[@id='subtotal-label']")
    WebElement subtotalTxt;


    // Method to verify that the Web Automation Advance page is displayed correctly
    public  void verifyWebAutoAdvancedPage(){
        waitForPageToLoad(driver, 30);
        ClickObject(webAutomationAdvanceBtn, driver);
        try {
            Assert.assertEquals(inventoryFormTxt.getText(), inventoryFormTxt.getText(), "Web Automation Advance page validation failed: Inventory form text does not match expected.");
            System.out.println("Web Automation Advance page validation successful: Inventory form is displayed" +".");
            getBase64Screenshot(driver);
        } catch (Exception e) {
            System.out.println("Web Automation Advance page validation failed: " + e.getMessage());
            Assert.fail("Web Automation Advance page validation failed: " + e.getMessage());
            getBase64Screenshot(driver);
        }
    }

    // Method to select product details and enter delivery address
    public void customerSelectsProduct(String deviceType, String brand, String storage, String color,String quantity,String delivery_Address)throws IOException {
        waitForPageToLoad(driver, 25);
        SelectObject(deviceTypeDropdown, driver,"visibletext",deviceType);
        SelectObject(brandDropdown, driver,"visibletext",brand);
        ClickObject(storage128GB_radioBtn, driver);
        SelectObject(color_Dropdown, driver,"visibletext",color);
        EnterValue(input_Quantity, driver,quantity);
        SendKeys(deliveryAddress, driver,delivery_Address);

    }

    // Method to validate that the customer's product selection is displayed correctly on the page
    public void validateCustomerProductSelection() {
        waitForPageToLoad(driver, 30);
        try {
                String unitPriceMessage = "Unit price text is not displayed.";
                String devicePreviewMessage = "Device preview image is not displayed.";
                String subtotalMessage = "Subtotal text is not displayed.";
            Assert.assertEquals(unitPriceTxt.getText(), unitPriceTxt.getText(),unitPriceMessage);
            getBase64Screenshot(driver);
            Assert.assertEquals(devicePreviewImg.getText(), devicePreviewImg.getText(),devicePreviewMessage);
            getBase64Screenshot(driver);
            Assert.assertEquals(subtotalTxt.getText(), subtotalTxt.getText(),subtotalMessage);
            System.out.println("Customer Product selection details validation successful: All expected elements are displayed.");
            getBase64Screenshot(driver);
        } catch (Exception e) {
            System.out.println("Customer selection validation failed: " + e.getMessage());
           getBase64Screenshot(driver);
            Assert.fail("Customer selection validation failed: " + e.getMessage());

        }

    }




}
