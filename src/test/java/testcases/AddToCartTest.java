package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;

import java.util.ArrayList;
import java.util.List;

public class AddToCartTest {

    WebDriver driver;

    String userName = new String("standard_user");
    String passwd = new String("secret_sauce");


    // Set up Chrome and get website
    @BeforeEach
    public void createDriver() {
        TestSetup setup = new TestSetup();
        driver = setup.TestSetupDriver(driver);
    }


    @Test
    public void AddTwoToCartFromProductsPageTest() throws InterruptedException {
        int numProducts = 2;

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
        Assertions.assertEquals(numProducts, products.getNumberOfProductsInCart());

        // Go to the Cart page and check contents
        products.clickCart();

        Cart cart = new Cart(driver);

        // Ensure names match in cart to what was ordered
        for (int i=0; i<numProducts; i++) {
            Assertions.assertEquals(true, cart.checkItemInCart(products.productArray[1][i]));
        }

        // Ensure number of items matches what was ordered

        Assertions.assertEquals(numProducts, cart.numProducts());

        products.logOutOfApp();

    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}
