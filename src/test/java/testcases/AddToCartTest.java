package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class AddToCartTest {

    WebDriver driver;

    String userName = new String("standard_user");
    String passwd = new String("secret_sauce");

    @BeforeEach
    public void createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }
    @Test
    public void AddToCartTest() {
        // Log in
        Login login = new Login(driver);
        login.get();
        login.LoginToSite(userName, passwd);

        // Check Inventory Page comes up
        Products products = new Products(driver);
        products.get();
        Assertions.assertTrue(products.checkHeading());

        // Add Product from Products page
        products.clickProductAddToCart("add-to-cart-sauce-labs-backpack");
        Assertions.assertEquals(1, products.getNumberOfProductsInCart());

        // Add Product from Product Specific Screen

        // go to product screen
        String name1 = new String(products.getProductName("item_1_title_link"));
        products.clickProduct("item_1_title_link");

        // Check the name of product matches
        SingleProduct singleProduct = new SingleProduct(driver);
        String name2 = new String(singleProduct.getProductName());
        Assertions.assertEquals(name1, name2);

        // add to cart and check number in cart
        singleProduct.addToCart();
        Assertions.assertEquals(2, singleProduct.getNumberOfProductsInCart());

        // Go back to Products screen and check Number in cart
        singleProduct.backToProduct();
        Assertions.assertEquals(2, products.getNumberOfProductsInCart());
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}
