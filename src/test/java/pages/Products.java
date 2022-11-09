package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Products extends LoadableComponent {
    public final WebDriver driver;
    public final WebDriverWait wait;

    @FindBy(how = How.CLASS_NAME, using = "title")
    WebElement heading;


    // Check the Title of the current page
    public boolean checkHeading() {
        return heading.getText().equals("PRODUCTS");
    }

    public void clickProductAddToCart(String id) {
        WebElement button = driver.findElement(By.id(id));
        button.click();
    }

    public int getNumberOfProductsInCart() {
        WebElement cart = driver.findElement(By.className("shopping_cart_badge"));
        return Integer.parseInt(cart.getText());
    }

    public String getProductName(String id) {
        WebElement item = driver.findElement(By.id(id));
        String name = item.getText();
        return name;
    }

    public void clickProduct(String id) {
        WebElement item = driver.findElement(By.id(id));
        item.click();

    }

    // Constructor
    public Products(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Override
    protected void load() {

    }

    // Set up to use Loadable Components
    @Override
    protected void isLoaded() throws Error {

        boolean ready=false;

        try {
            boolean hasTitleLoaded = driver.getTitle().equals("Swag Labs");
            ready = hasTitleLoaded;

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!ready){
            throw new Error("Page has not loaded");
        }
    }
}
