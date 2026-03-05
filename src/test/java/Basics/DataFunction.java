package Basics;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import static Basics.ExcelReader.getCellData;

public class DataFunction {

    //Define instance variables
    static ExcelReader data = new ExcelReader();
    public static  String Quantity;
    public static  String DiscountCode;
    public static String Username;
    public static String Password;
    public static  String DeviceType;
    public static  String Brand;
    public static  String Storage;
    public static  String Color;
    public static  String DeliveryAddress;

    // This method will run before each test method and will read the test data from the Excel file and store it in the respective variables for use in the test methods.

    public void excelTestData() throws IOException {
        data.initializeWorkbook("src/test/resources/TestData/myTestData.xlsx");

        Username = getCellData("Login_Credentials", "Username", 1);
        Password = getCellData("Login_Credentials", "Password", 1);
        DeliveryAddress = getCellData("Order_Details", "Deliver_Address", 1);
        DeviceType = getCellData("Order_Details", "Device_Type", 1);
        Brand = getCellData("Order_Details", "Brand", 1);
        Storage = getCellData("Order_Details", "Storage", 1);
        Color = getCellData("Order_Details", "Color", 1);
        Quantity = getCellData("Order_Details", "Quantity", 1);
        DiscountCode = getCellData("Order_Preview", "Discount_Code", 1);

    }

}
