package Tests;

import base.Base;
import org.testng.annotations.Test;

import java.io.IOException;

import static Basics.DataFunction.*;


public class Pablo_Test extends Base {


    @Test
    public void landingPageTest() throws IOException {
        landingObj.verifyLandingPage();
    }
    @Test(dependsOnMethods = "landingPageTest")
    public void loginTest() throws IOException {
        loginObj.login(Username,Password);
        loginObj.validateLogin();
    }
    @Test( dependsOnMethods = "loginTest")
    public void learnPageTest() throws IOException {
        learnObj.navigateToLearnPage();
        learnObj.verifyLearnPage();
    }
    @Test(dependsOnMethods = "learnPageTest")
    public void inventoryPageTest() throws IOException {
        inventoryObj.verifyWebAutoAdvancedPage();

    }
    @Test(dependsOnMethods = "inventoryPageTest")
    public  void CustomerSelectionTest() throws IOException {
        inventoryObj.customerSelectsProduct(DeviceType, Brand, Storage, Color,Quantity ,DeliveryAddress);
        inventoryObj.validateCustomerProductSelection();
    }
    @Test(dependsOnMethods = "CustomerSelectionTest")
    public void orderPreviewTest() throws IOException {
        orderPreviewObj.orderPreview(DiscountCode);
        orderPreviewObj.validatePurchaseSuccess();
    }
        @Test(dependsOnMethods = "orderPreviewTest")
        public void viewInvoiceTest() throws IOException {
            orderPreviewObj.viewInvoice();
        }

}
