package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Clock;
import java.time.Duration;

public class Login extends SlowLoadableComponent {

    //karinarmbrustvo@gmail.com and mypassword
    // http://automationpractice.com/index.php

    private final WebDriver driver;
    //private final WebDriverWait wait;

    @FindBy(how = How.ID, using = "email")
    WebElement emailField;

    @FindBy(how = How.ID, using = "passwd")
    WebElement password;

    @FindBy(how = How.ID, using = "SubmitLogin")
    WebElement loginButton;

    // Log into the site
    public void LoginToSite(String username, String passwd) {
        emailField.sendKeys(username);
        password.sendKeys(passwd);
        loginButton.click();

    }

    // Constructor
    public Login(WebDriver driver) {
        super(Clock.systemDefaultZone(), 10);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        boolean ready = false;
        try {
            String title = driver.getTitle();
            ready = title.equals("Login - My Store");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!ready) {
            throw new Error("Login Page not ready");
        }

    }
}
