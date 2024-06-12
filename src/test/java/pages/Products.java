package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Products extends LoadableComponent {
    public final WebDriver driver;
    public final WebDriverWait wait;

    @FindBy(how = How.CLASS_NAME, using = "title")
    WebElement heading;

    @FindBy(how = How.ID, using = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(how = How.ID, using = "inventory_sidebar_link")
    WebElement allItems;

    @FindBy(how = How.ID, using = "about_sidebar_link")
    WebElement about;

    @FindBy(how = How.ID, using = "reset_sidebar_link")
    WebElement resetTheApp;

    @FindBy(how = How.ID, using = "logout_sidebar_link")
    WebElement logout;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    WebElement cartLink;


    // Add the number of products to the cart using the productArray
    public void addProductsFromProductsScreen(int numProducts) {
        WebElement productButton;
        //Products products = new Products(driver);

        for (int i = 0; i<numProducts; i++ ) {
            productButton = driver.findElement(By.xpath(productArray[0][i]));
            productButton.click();
        }

    }

    // logout of the application
    public void logOutOfApp() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menuButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(logout));
        logout.click();
    }

    // logout of the application
    public void resetApplication() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menuButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(resetTheApp));
        resetTheApp.click();
    }
    // Check the Title of the current page
    public boolean checkHeading() {
        return heading.getText().equals("Products");
    }

    public void clickProductAddToCart(String id) {
        WebElement button = driver.findElement(By.id(id));
        button.click();
    }

    public int getNumberOfProductsInCart() {
        WebElement cart = driver.findElement(By.className("shopping_cart_link"));
        return Integer.parseInt(cart.getText());
    }

    public String getProductName(String xPath) {
        WebElement item = driver.findElement(By.xpath(xPath));
        String name = item.getText();
        return name;
    }

    public void clickProduct(String xPath) {
        WebElement item = driver.findElement(By.xpath(xPath));
        item.click();

    }

    public void clickCart() {
        cartLink.click();

    }

    // Constructor
    public Products(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

    public String productArray[][] = {
            { //xpath to Add to Cart Button
                    "//*[@id=\"add-to-cart-sauce-labs-backpack\"]",
                    "//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]",
                    "//*[@id=\"add-to-cart-sauce-labs-onesie\"]",
                    "//*[@id=\"add-to-cart-sauce-labs-bike-light\"]",
                    "//*[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]",
                    "//*[@id=\"add-to-cart-test.allthethings()-t-shirt-(red)\"]"

            },
            {  // Name of product
                    "Sauce Labs Backpack",
                    "Sauce Labs Bolt T-Shirt",
                    "Sauce Labs Onesie",
                    "Sauce Labs Bike Light",
                    "Sauce Labs Fleece Jacket",
                    "Test.allTheThings() T-Shirt (Red)"
            }
    };

}
