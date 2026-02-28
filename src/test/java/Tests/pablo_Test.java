package Tests;

import Base.Base;
import org.testng.annotations.Test;

import java.io.IOException;

import static Basics.DataFunction.*;


public class pablo_Test extends Base {


    @Test
    public void pabloTest() throws IOException {
        landingObj.verifyLandingPage();
        loginObj.login(Username,Password);
        loginObj.validateLogin(Username);
        learnObj.navigateToLearnPage();
        learnObj.verifyLearnPage();
        webAutoAdvancedObj.verifyWebAutoAdvancedPage();
        webAutoAdvancedObj.customerSelectsProduct(DeviceType, Brand, Storage, Color,Quantity ,DeliveryAddress);
        webAutoAdvancedObj.validateCustomerProductSelection();
        orderPreviewObj.orderPreview(DiscountCode);
        orderPreviewObj.validatePurchaseSuccess();
        orderPreviewObj.viewInvoice();

    }
}
