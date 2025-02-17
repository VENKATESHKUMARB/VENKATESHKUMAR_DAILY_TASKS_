package snapdeal_app;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Register_ {
    private WebDriver driver;
    private String baseUrl;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set the path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");

        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        // Initialize WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Set the base URL for Snapdeal
        baseUrl = "https://www.snapdeal.com/";

        // Launch the Snapdeal website
        driver.get(baseUrl);
    }

    @Test
    public void testRegisterWithValidMobileNumber() throws InterruptedException {
        // Click on the "Sign In" button
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement signInButton = driver.findElement(By.xpath("//span[text()='Sign In']"));
        signInButton.click();

        // Click on the "Register" button in the popup
        WebElement registerButton = driver.findElement(By.xpath("(//span[@class='newUserRegister'])[1]"));
        registerButton.click();

        // Wait for the iframe and switch to it
        WebElement iframeRegister = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginIframe")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeRegister));

        // Enter valid mobile number/email
        WebElement mobileNumberField = driver.findElement(By.xpath("(//input[@id='userName'])[1]"));
        mobileNumberField.sendKeys("7200349055");
        
        // Wait for the "Continue" button to be clickable
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='checkUser'])[1]")));
        
        // Use JavascriptExecutor to click the "Continue" button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        
        WebElement emailField = driver.findElement(By.xpath("(//input[@id='j_username_new'])[1]"));
        emailField.sendKeys("2k19eee026@kiot.ac.in");
        
        WebElement nameField = driver.findElement(By.xpath("(//input[@id='j_name'])[1]"));
        nameField.sendKeys("naveenya");
    
        WebElement passwordField = driver.findElement(By.xpath("(//input[@id='j_password'])[1]"));
        passwordField.sendKeys("Saranaravid@12345");
    
        // Wait for the "Continue" button to be clickable
        WebElement continueButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='userSignup'])[1]")));
    
        // Use JavascriptExecutor to click the "Continue" button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton3);
    
        // Prompt for manual OTP entry
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the OTP: ");
        String otp = scanner.nextLine();

        // Enter the OTP
        WebElement otpFieldRegister = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@class='verifyInput'])[1]")));
        otpFieldRegister.sendKeys(otp);

        // Wait for 3 seconds and then click on "Continue" after entering OTP
        Thread.sleep(3000);
        WebElement continueOtpButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginUsingOtp")));
        continueOtpButton.click();

        // Continue with the registration process
        WebElement registerContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='registerUser'])[1]")));
    
        // Use JavascriptExecutor to click the "Continue" button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", registerContinueButton);
    
        // Optionally add assertions to verify successful registration
        // You can add code here to verify that the registration page moves to the next step after clicking "Continue".
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}