package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.SlowLoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.Duration;

public class Login extends SlowLoadableComponent {

    //karinarmbrustvo@gmail.com and mypassword
    // http://automationpractice.com/index.php

    private final WebDriver driver;

    private File screenshotFolder = new File(System.getProperty("user.dir"),
            "screenshotsFromLoginTest");

    @FindBy(how = How.ID, using = "user-name")
    WebElement uname;

    @FindBy(how = How.ID, using = "password")
    WebElement password;

    @FindBy(how = How.ID, using = "login-button")
    WebElement loginButton;

    // Log into the site
    public void LoginToSite(String username, String passwd) {
        uname.sendKeys(username);
        password.sendKeys(passwd);
        loginButton.click();

        // Get a screenshot
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(screenshotFolder,
                    "Login Result - " + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

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
            ready = title.equals("Swag Labs");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!ready) {
            throw new Error("Login Page not ready");
        }

    }
}
