package snapdeal_app;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class empty_field {
    private WebDriver driver;
    private String baseUrl;

    @BeforeClass
    public void setUp() {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        // Set the base URL for Snapdeal
        baseUrl = "https://www.snapdeal.com/";

        // Launch the Snapdeal website
        driver.get(baseUrl);
    }

    @Test
    public void testLoginWithEmptyUsername() {
    	
    	
        // Hover over the "Sign In" button
        
        WebElement loginButton = driver.findElement(By.xpath("//span[text()='Sign In']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(loginButton).perform();
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        
        // Click on the "Login" button in the popup
        WebElement loginPopupButton = driver.findElement(By.xpath("//a[text()='login']"));
        loginPopupButton.click();
        
        
        WebElement iframeLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginIframe")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLogin));  // Switch to iframe

        // Step 4: Enter the username/email
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
        usernameField.clear();

        // Step 5: Click on "Continue" after entering username
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkUser")));
        continueButton.click();

       
    
    
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}