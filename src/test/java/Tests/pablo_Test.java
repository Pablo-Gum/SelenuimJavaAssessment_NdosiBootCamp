package Tests;

import Base.Base;
import org.testng.annotations.Test;

import java.io.IOException;

import static Basics.DataFunction.*;


public class pablo_Test extends Base {


    @Test
    public void pabloTest() throws IOException {
        landingObj.verifyLandingPage(node);
        loginObj.login(Username,Password);
        loginObj.validateLogin(node);
        learnObj.navigateToLearnPage();
        learnObj.verifyLearnPage(node);
        inventoryObj.verifyWebAutoAdvancedPage(node);
        inventoryObj.customerSelectsProduct(DeviceType, Brand, Storage, Color,Quantity ,DeliveryAddress);
        inventoryObj.validateCustomerProductSelection(node);
        orderPreviewObj.orderPreview(DiscountCode);
        orderPreviewObj.validatePurchaseSuccess(node);
        orderPreviewObj.viewInvoice(node);

    }
}
