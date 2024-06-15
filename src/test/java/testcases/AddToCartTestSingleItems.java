package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class AddToCartTestSingleItems - Adding and removing items from the Cart from specific Products Pages
public class AddToCartTestSingleItems {

    WebDriver driver;

    String userName = new String("standard_user");
    String passwd = new String("secret_sauce");

    private static File screenshotFolder = new File(System.getProperty("user.dir"),
            "screenshotsAddToCartTestSingleItems");

    // Set up Chrome and get website
    @BeforeEach
    public void createDriver() {
        TestSetup setup = new TestSetup();
        driver = setup.TestSetupDriver(driver);
    }

    // AddTwoToCartSingleProductsTest- adds 2 items to the cart from the individual Products Pages
    @Test
    public void AddTwoToCartSingleProductsTest() throws InterruptedException {
        int numProducts = 2;
        File screenshot;
        // Log in
        Login login = new Login(driver);
        login.get();
        login.LoginToSite(userName, passwd);

        // Check Inventory Page comes up
        Products products = new Products(driver);
        products.get();
        Assertions.assertTrue(products.checkHeading());

        // Add Product from Product Specific Screen - two products in cart

        // *** go to each product screen and add the product
        SingleProduct singleProduct = new SingleProduct(driver);
        String name;
        int numProductsInCart = 0;
        for (int i=0; i<numProducts; i++) {
            // Go to single product page
            products.clickProduct(singleProduct.productArraySingle[1][i]);
            // get the name and verify
            name = new String(singleProduct.getProductName());
            Assertions.assertEquals(singleProduct.productArraySingle[0][i], name);
            // Add the product
            singleProduct.addToCart();
            // Check cart number on Single Product Page
            numProductsInCart = singleProduct.getNumberOfProductsInCart();
            Assertions.assertEquals(i + 1, numProductsInCart);
            // go back to Products Page
            singleProduct.backToProduct();
        }

        // Go back to Products screen and check Number in cart
        int numProductsInProductScreen = products.getNumberOfProductsInCart();
        Assertions.assertEquals(numProducts, numProductsInProductScreen);
        // Get a screenshot if not equal
        if (numProducts != numProductsInProductScreen) {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File(screenshotFolder,
                        "NumProductsInCart1 - " + System.currentTimeMillis() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Go to the Cart page and check contents
        products.clickCart();
        Cart cart = new Cart(driver);

        // Ensure names match in cart to what was ordered
        for (int i = 0; i<numProducts; i++){
            Assertions.assertEquals(true, cart.checkItemInCart(singleProduct.productArraySingle[0][i]));
        }
        // Ensure number of items matches what was ordered

        Assertions.assertEquals(numProducts, cart.numProducts());
        // Get a screenshot if not equal
        if (numProducts != cart.numProducts()) {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File(screenshotFolder,
                        "NumProductsInCart2 - " + System.currentTimeMillis() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        products.logOutOfApp();
    }



    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}
