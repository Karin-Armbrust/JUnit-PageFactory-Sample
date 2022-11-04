package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends PageFactory {

    private final WebDriver driver;
    private final WebDriverWait wait;
    @FindBy(how = How.CLASS_NAME, using = "login")
    WebElement loginButton;

    // Click the Sign In Button
    public void signInAction() {
        loginButton.click();
    }

    // Waiting
    public void waitUntilPageLoads() {
        wait.until(ExpectedConditions.titleIs("My Store"));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    // Constructor
    public MainPage(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
