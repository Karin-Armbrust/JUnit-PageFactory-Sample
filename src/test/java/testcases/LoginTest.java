package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginTest {

    WebDriver driver;

    private static File screenshotFolder = new File(System.getProperty("user.dir"),
            "screenshotsFromLoginTest");
    String userName = new String("standard_user");
    String passwd = new String("secret_sauce");
    String badUserName = "invalid_user";
    String badPasswd = new String ("badpassword");


    // Clear the screenshots folder before run
    @BeforeAll
    public static void clearScreenshots() {
        if (screenshotFolder.exists()) {
            try {
                FileUtils.cleanDirectory(screenshotFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        screenshotFolder.mkdir();
    }

    @BeforeEach
    public void setupTest() {
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\QA-Tools\\drivers\\chromedriver123.exe");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
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

    }

    @Test
    public void badEmailLoginTest() {

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
        login.LoginToSite(badPasswd, badPasswd);

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
