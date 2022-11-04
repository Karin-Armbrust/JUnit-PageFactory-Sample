package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends PageFactory {

    //karinarmbrustvo@gmail.com and mypassword
    // http://automationpractice.com/index.php

    private final WebDriver driver;
    private final WebDriverWait wait;

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

    // Wait method
    public void waitTillPageLoads() {
        wait.until(ExpectedConditions.titleIs("Login - My Store"));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

    // Constructor
    public Login(WebDriver driver) {
        this.driver = driver;
        initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
