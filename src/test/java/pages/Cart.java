package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Cart extends LoadableComponent {
    public final WebDriver driver;
    public final WebDriverWait wait;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    WebElement cartLink;

    public void clickCart() {
        cartLink.click();
    }

    public int numProducts() {
        List<WebElement> numberProducts = driver.findElements(By.className("cart_item"));
        return numberProducts.size();
    }

    public Boolean checkItemInCart(String itemTitle) {
        List<WebElement> cartContents = driver.findElements(By.className("inventory_item_name"));
        List<String> allItems = new ArrayList<>();

        for (int i = 0; i<cartContents.size(); i++) {
            allItems.add(cartContents.get(i).getText());
        }
        for (int i = 0; i<allItems.size(); i++) {
            if (itemTitle.equals(allItems.get(i))) {
                return true;
            }

        }
        return false;
    }


    // Constructor for Cart
    public Cart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Code for the LoadedComponent
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
