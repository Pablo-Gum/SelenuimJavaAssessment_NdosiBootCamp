package Pages;

import Basics.Actions;
import Utilities.Screenshots;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import static Utilities.Screenshots.getBase64Screenshot;
import static base.Base.screenshots;


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
    public void orderPreview(String DiscountCode) {
        ClickObject(inventoryNextBtn, driver);
        waitForPageToLoad(driver, 15);
        ClickObject(expressShippingORadioBtn, driver);
        ClickObject(oneYearWarrantyRadioBtn, driver);
        SendKeysJS(discountCodeInput, driver, DiscountCode);
        ClickObject(applyDiscountBtn, driver);
        ClickObject(confirmPurchaseBtn, driver);

    }

    // Method to validate purchase success
    public void validatePurchaseSuccess() {
        try {
            Assert.assertEquals(purchaseSuccessToast.getText(), purchaseSuccessToast.getText(),"Purchase success toast is not displayed.");
            System.out.println("Purchase success validation successful: Purchase success toast is displayed.");
            getBase64Screenshot(driver);
        } catch (Exception e) {
            System.out.println("Purchase failed validation : " + e.getMessage());
           getBase64Screenshot(driver);
            Assert.fail("Purchase failed validation : " + e.getMessage());
        }

    }

    public void viewInvoice()  {
        ClickObject(viewInvoiceHistoryBtn, driver);
        Explicit_Wait(viewBtn, driver, "element_Clickable", 10);
        ClickObject(viewBtn, driver);
        switchToNewTab(driver);
        waitForPageToLoad(driver, 25);

        // Validate that the invoice display is visible
        try {
            Assert.assertTrue(invoiceDisplay.isDisplayed(), "Invoice display is not visible.");
            System.out.println("Invoice display validation successful: Invoice display is visible");
            getBase64Screenshot(driver);
        } catch (Exception e) {
            getBase64Screenshot(driver);
            System.out.println("Invoice display validation failed: " + e.getMessage());
            Assert.fail("Invoice display validation failed: " + e.getMessage());
        }
    }



}
