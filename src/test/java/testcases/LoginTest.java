package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.IOException;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    String userName = new String("standard_user");
    String passwd = new String("secret_sauce");
    String badUserName = "invalid_user";
    String badPasswd = new String ("badpassword");

    // Set up Chrome and get the website

    @BeforeEach
    public void setupNewTest() {
        TestSetup setup = new TestSetup();
        driver = setup.TestSetupDriver(driver);
    }

    @Test
    public void goodLoginTest() {

        // Enter Login Information and Log In
        Login login = new Login(driver);
        login.get();
        login.LoginToSite(userName, passwd);

        // Check Inventory Page comes up
        Products products = new Products(driver);
        products.get();
        Assertions.assertTrue(products.checkHeading());

        products.logOutOfApp();
    }

    @Test
    public void badUserNameLoginTest() {

        // Enter Login Information and Log In
        Login login = new Login(driver);
        login.get();
        login.LoginToSite(badUserName, passwd);

        // Check login failed
        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("error-message-container")));
        String errorText = errorMessage.getText().substring(0,16);
        Assertions.assertEquals("Epic sadface: Us", errorText);

    }

    @Test
    public void badPasswdLoginTest() {

        // Enter Login Information and Log In
        Login login = new Login(driver);
        login.get();
        login.LoginToSite(userName, badPasswd);

        // Check login failed
        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("error-message-container")));
        String errorText = errorMessage.getText().substring(0,16);
        Assertions.assertEquals("Epic sadface: Us", errorText);

    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}
