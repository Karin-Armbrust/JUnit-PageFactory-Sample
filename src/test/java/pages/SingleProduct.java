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

    public SingleProduct(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addToCart() {
        WebElement button = driver.findElement(By.className("btn_inventory"));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("btn_inventory")));
        button.click();
    }

    public int getNumberOfProductsInCart() {
        WebElement cart = driver.findElement(By.className("shopping_cart_badge"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("shopping_cart_badge")));
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

}
