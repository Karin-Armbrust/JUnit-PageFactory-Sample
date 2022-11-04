package testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    String emailAddress = new String("karinarmbrustvo@gmail.com");
    String passwd = new String("mypassword");

    String badEmailAddress = "karinkarmbrustvo@gmail.com";

    String badPasswd = new String ("badpassword");

    @BeforeEach
    public void createDriver() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void loginTest() {
        // Click the Sign In button
        MainPage page = new MainPage(driver);
        page.waitUntilPageLoads();
        page.signInAction();

        // Enter Login Information and Log In
        Login login = new Login(driver);
        login.waitTillPageLoads();
        login.LoginToSite(emailAddress, passwd);

        // Check Account Page comes up
        Account account = new Account(driver);
        account.get();
        Assertions.assertTrue(account.checkTitle());

    }

    @Test
    public void badEmailLoginTest() {
        // Click the Sign In button
        MainPage page = new MainPage(driver);
        page.waitUntilPageLoads();
        page.signInAction();

        // Enter Login Information and Log In
        Login login = new Login(driver);
        login.waitTillPageLoads();
        login.LoginToSite(badEmailAddress, passwd);

        // Check login failed
        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("alert-danger")));
        String errorText = errorMessage.getText().substring(0,16);
        Assertions.assertEquals("There is 1 error", errorText);

    }

    @Test
    public void badPasswdLoginTest() {
        // Click the Sign In button
        MainPage page = new MainPage(driver);
        page.waitUntilPageLoads();
        page.signInAction();

        // Enter Login Information and Log In
        Login login = new Login(driver);
        login.waitTillPageLoads();
        login.LoginToSite(emailAddress, badPasswd);

        // Check login failed
        WebElement errorMessage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("alert-danger")));
        String errorText = errorMessage.getText().substring(0,16);
        Assertions.assertEquals("There is 1 error", errorText);

    }

    @AfterEach
    public void closeDriver() {
        driver.quit();
    }

}
