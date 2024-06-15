package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
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

// Class AddToCartTest - Adding and removing items from the Cart from the Products Page
public class AddToCartTest {

    WebDriver driver;

    private static File screenshotFolder = new File(System.getProperty("user.dir"),
            "screenshotsAddToCartTest");
    String userName = new String("standard_user");
    String passwd = new String("secret_sauce");

    // Clear the screenshots folder
    @BeforeAll
    public static void clearScreenshots() {
        if (screenshotFolder.exists()) {
            try {
                FileUtils.cleanDirectory(screenshotFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        screenshotFolder.mkdir();
    }

    // Set up Chrome, the driver and get website
    @BeforeEach
    public void createDriver() {
        TestSetup setup = new TestSetup();
        driver = setup.TestSetupDriver(driver);
    }

    // AddTwoToCartFromProductsPageTest- adds 2 items to the cart from the main Products Page
    @Test
    @Tag("CartTests")
    public void AddTwoToCartFromProductsPageTest() throws InterruptedException {
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

        // Add Products from Products page - one product in cart
        products.addProductsFromProductsScreen(numProducts);

        // Ensure the number of products added matches numProducts
        Assertions.assertEquals(numProducts, products.getNumberOfProductsInCart());

        // if number of products doesn't match numProducts take a screenshot
        if (numProducts != products.getNumberOfProductsInCart()) {
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

        // Ensure names match in cart to what was ordered on the Products Page
        for (int i=0; i<numProducts; i++) {
            Assertions.assertEquals(true, cart.checkItemInCart(products.productArray[1][i]));
        }

        // Ensure number of items matches what was ordered on the Cart Page
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

    // AddTwoToCartFromProductsPageTest- adds 2 items to the cart from the main Products Page
    @Test
    @Tag("CartTests")
    public void AddAndRemoveTwoToCartFromProductsPageTest() throws InterruptedException {
        int numProducts = 2;
        int numProductsExpected = 0; // after removal
        File screenshot;

        // Log in
        Login login = new Login(driver);
        login.get();
        login.LoginToSite(userName, passwd);

        // Check Inventory Page comes up
        Products products = new Products(driver);
        products.get();
        Assertions.assertTrue(products.checkHeading());

        // Add Products from Products page - two products in cart
        products.addProductsFromProductsScreen(numProducts);

        // Remove Products from Product page - Two products removed
        products.removeProductsFromProductsScreen(numProducts);

        // Ensure that the number of product in the cart button matches the expected
        Assertions.assertEquals(numProductsExpected, products.getNumberOfProductsInCart());

        // If the number doesn't match, take a screenshot
        if (numProductsExpected != products.getNumberOfProductsInCart()) {
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

        // Ensure number of items on the Cart Page matches the expected
        Assertions.assertEquals(numProductsExpected, cart.numProducts());

        // If the number doesn't match get a screenshot
        if (numProductsExpected != cart.numProducts()) {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot, new File(screenshotFolder,
                        "NumProductsInCart2 - " + System.currentTimeMillis() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Log out of application
        products.logOutOfApp();

    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}
