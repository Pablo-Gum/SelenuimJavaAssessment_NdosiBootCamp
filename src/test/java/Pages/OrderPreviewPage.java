package Pages;

import Basics.Actions;
import Basics.ReportingUtils;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

import static Base.Base.switchToNewTab;

public class OrderPreviewPage  extends Actions {
    WebDriver driver;

    public OrderPreviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//*[@id='inventory-next-btn']")
    WebElement inventoryNextBtn;

    @FindBy(xpath = "//*[@id='purchase-success-toast']//P[contains(.,', your order was purchased successfully!')]")
    WebElement purchaseSuccessToast;


    @FindBy(xpath = "//*[@id='shipping-option-express']")
    WebElement expressShippingORadioBtn;

    @FindBy(xpath = "//*[@id='warranty-option-1yr']")
    WebElement oneYearWarrantyRadioBtn;

    @FindBy(xpath = "//*[@id='discount-code']")
    WebElement discountCodeInput;

    @FindBy(xpath = "//*[@id='apply-discount-btn']")
    WebElement applyDiscountBtn;

    @FindBy(xpath = "//*[@id='purchase-device-btn']")
    WebElement confirmPurchaseBtn;

    @FindBy(xpath = "//*[@id='view-history-btn']")
    WebElement viewInvoiceHistoryBtn;

    @FindBy(xpath = "//*[contains(@id,'view-invoice-INV-')]")
    WebElement viewBtn;

    @FindBy(xpath = " //*[@class='invoice-container']")
    WebElement invoiceDisplay;

    // Method to perform order preview actions
    public void orderPreview(String DiscountCode) throws IOException {
        ClickObject(inventoryNextBtn, driver);
        waitForPageToLoad(driver, 15);
        ClickObject(expressShippingORadioBtn, driver);
        ClickObject(oneYearWarrantyRadioBtn, driver);
        SendKeysJS(discountCodeInput, driver, DiscountCode);
        ClickObject(applyDiscountBtn, driver);
        ClickObject(confirmPurchaseBtn, driver);

    }

    // Method to validate purchase success
    public void validatePurchaseSuccess(ExtentTest test) {
        try {
            ReportingUtils.navigateAndReport(driver, test,"Purchase SuccessFull ");
            Assert.assertEquals(purchaseSuccessToast.getText(), purchaseSuccessToast.getText(),"Purchase success toast is not displayed.");
            System.out.println("Purchase success validation successful: Purchase success toast is displayed.");
        } catch (Exception e) {
            ReportingUtils.navigateAndReport(driver, test,"Purchase  Validation Failed");
            System.out.println("Purchase failed validation : " + e.getMessage());
            Assert.fail("Purchase failed validation : " + e.getMessage());
        }

    }

    public void viewInvoice(ExtentTest test) throws IOException {
        ClickObject(viewInvoiceHistoryBtn, driver);
        Explicit_Wait(viewBtn, driver, "element_Clickable", 10);
        ClickObject(viewBtn, driver);
        switchToNewTab();
        waitForPageToLoad(driver, 15);

        // Validate that the invoice display is visible
        try {
            ReportingUtils.navigateAndReport(driver,test,"View Invoice");
            Assert.assertTrue(invoiceDisplay.isDisplayed(), "Invoice display is not visible.");
            System.out.println("Invoice display validation successful: Invoice display is visible");
        } catch (Exception e) {
            ReportingUtils.navigateAndReport(driver,test,"Invoice Display  Failed");
            System.out.println("Invoice display validation failed: " + e.getMessage());
            Assert.fail("Invoice display validation failed: " + e.getMessage());
        }
    }



}
