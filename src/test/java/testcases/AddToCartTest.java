package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;

public class AddToCartTest {

    WebDriver driver;

    String userName = new String("standard_user");
    String passwd = new String("secret_sauce");

    @BeforeEach
    public void createDriver() {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\QA-Tools\\drivers\\chromedriver123.exe");
        driver = new ChromeDriver(options);
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

        // Add Product from Products page - one product in cart
        products.clickProductAddToCart("add-to-cart-sauce-labs-backpack");
        Assertions.assertEquals(1, products.getNumberOfProductsInCart());

        // Add Product from Product Specific Screen - two products in cart

        // go to product screen
        String name1 = new String(products.getProductName("item_1_title_link"));
        products.clickProduct("item_1_title_link");

        // Check the name of product matches
        SingleProduct singleProduct = new SingleProduct(driver);
        String name2 = new String(singleProduct.getProductName());
        System.out.println("Expected: " + name1);
        System.out.println("Actual: " + name2);
        Assertions.assertEquals(name1, name2);

        // add to cart and check number in cart
        singleProduct.addToCart();
        int numProducts = singleProduct.getNumberOfProductsInCart();
        System.out.println("Expected: 2");
        System.out.println("Actual: " + numProducts);
        Assertions.assertEquals(2, numProducts);

        // Go back to Products screen and check Number in cart
        singleProduct.backToProduct();
        int numProductsInProduct = products.getNumberOfProductsInCart();
        System.out.println("Expected: 2");
        System.out.println("Actual: " + numProducts);
        Assertions.assertEquals(2, numProductsInProduct);
    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}
