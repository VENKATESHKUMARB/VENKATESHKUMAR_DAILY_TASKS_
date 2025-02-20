package snapdeal_app;


import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class emptyOtpfield {
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
        
        // Step 3: Wait for the iframe and switch to it
        WebElement iframeLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginIframe")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLogin));  // Switch to iframe

        // Step 4: Enter the username/email
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
        usernameField.sendKeys("9344800721");

        // Step 5: Click on "Continue" after entering username
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkUser")));
        continueButton.click();

        

        // Step 6: Wait for OTP field and manually enter OTP
      WebElement otpField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginOtpUC\"]/div[1]/input")));
      otpField.clear();  // Enter OTP (replace with actual OTP if needed)

        // Step 7: Click on "Continue" after entering OTP
        WebElement continueOtpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='loginUsingOtp'])[1]")));
        continueOtpButton.click();

    
    
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}