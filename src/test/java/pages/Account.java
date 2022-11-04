package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Account extends LoadableComponent {
    public final WebDriver driver;
    public final WebDriverWait wait;

    @FindBy(how = How.CLASS_NAME, using = "page-heading")
    WebElement title;

    @FindBy(how = How.CLASS_NAME, using = "home")
    private WebElement homeButton;

    // Check the Title of the current page
    public boolean checkTitle() {
        return title.getText().equals("MY ACCOUNT");
    }

    // Constructor
    public Account(WebDriver driver) {
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
            boolean hasTitleLoaded = driver.getTitle().equals("My account - My Store");
            boolean hasButtonLoaded = homeButton.isEnabled();
            ready = hasTitleLoaded && hasButtonLoaded;

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!ready){
            throw new Error("Page has not loaded");
        }
    }
}
