package snapdeal_register_inputs;

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

public class empty_email{
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
    public void testRegisterWithEmpty_emailfield() throws InterruptedException {
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
        mobileNumberField.sendKeys("8778224570");
        
        // Wait for the "Continue" button to be clickable
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='checkUser'])[1]")));
        
        // Use JavascriptExecutor to click the "Continue" button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        
        WebElement emailField = driver.findElement(By.xpath("(//input[@id='j_username_new'])[1]"));
        emailField.clear();
        
        Thread.sleep(3000);
        
        System.out.println("Testcase role : Email Field is Empty");
        
        WebElement nameField = driver.findElement(By.xpath("(//input[@id='j_name'])[1]"));
        nameField.sendKeys("naveenya");
    
        WebElement passwordField = driver.findElement(By.xpath("(//input[@id='j_password'])[1]"));
        passwordField.sendKeys("badri@12345");
    
        // Wait for the "Continue" button to be clickable
        WebElement continueButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='userSignup'])[1]")));
    
        // Use JavascriptExecutor to click the "Continue" button
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton3);
        
        Thread.sleep(5000);
        System.out.println("My Empty_emailfield Testcase is Successfully executed"); 
        
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}