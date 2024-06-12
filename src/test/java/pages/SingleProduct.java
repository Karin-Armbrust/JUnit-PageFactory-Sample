package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SingleProduct {
    public final WebDriver driver;
    public final WebDriverWait wait;

    @FindBy(how = How.CLASS_NAME, using = "inventory_details_name")
    WebElement productName;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    WebElement cartLink;

    public SingleProduct(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addToCart() {
        WebElement button = driver.findElement(By.id("add-to-cart"));;
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart")));
        button.click();
    }

    public int getNumberOfProductsInCart() {
        WebElement cart = driver.findElement(By.className("shopping_cart_link"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_link")));
        return Integer.parseInt(cart.getText());
    }

    public void backToProduct() {
        WebElement button = driver.findElement(By.id("back-to-products"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products")));
        button.click();
    }
    public String getProductName() {

        return productName.getText();
    }

    public void clickCart() {
        cartLink.click();

    }

    public String productArraySingle[][] = {
            {  // name of the product
                    "Sauce Labs Backpack",
                    "Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Onesie",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket",
                    "Test.allTheThings() T-Shirt (Red)"
            },
            {  // xpath the product image on the Products page
                    "//*[@id=\"item_4_img_link\"]/img",
                    "//*[@id=\"item_1_img_link\"]/img",
                    "//*[@id=\"item_2_img_link\"]/img",
                    "//*[@id=\"item_0_img_link\"]/img",
                    "//*[@id=\"item_5_img_link\"]/img",
                    "//*[@id=\"item_3_img_link\"]/img"
            }
    };

}
