package Snapdeal_login;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snapdeal_rigister {

    private WebDriver driver;
    private WebDriverWait wait;
    
    
    private static final Logger logger = LoggerFactory.getLogger(Snapdeal_rigister.class);

    @BeforeClass (groups = {"register"})
    public void setUp() {
        try {
        
            System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");

           
            driver = new ChromeDriver();

           
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));

         
            driver.manage().window().maximize();

         
            driver.get("https://www.snapdeal.com");
        } catch (Exception e) {
           
            Assert.fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    @Test(groups = {"register"})    
    public void testRegisterWithEmptyEmailField() {
        try {
            // Hover on the "Sign In" button
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Sign In']")));
            signInButton.click();

            //  Click on the "Register" button 
            WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='newUserRegister'])[1]")));
            registerButton.click();

            //Wait for the iframe and switch to it
            WebElement iframeRegister = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginIframe")));
            driver.switchTo().frame(iframeRegister);

            // valid mobile number
            WebElement mobileNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'userName')]")));
            mobileNumberField.sendKeys("8867547890");

            // Click the "Continue" button
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'checkUser')]")));
            continueButton.click();

            //  Clear the email field 
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'j_username_new')]")));
            emailField.clear();

            // Fill out the name 
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='j_name'])[1]")));
            nameField.sendKeys("naveenya");

            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@id='j_password'])[1]")));
            passwordField.sendKeys("badri@12345");

            //  Click the "Continue" button 
            WebElement continueButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='userSignup'])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton3);

           
        
            System.out.println("Test passed: Error message for empty email field is empty.");
            
            logger.info("Testcase is Passed");
            
        } 
        
        
        // Log for  the exception in "Register" and fail the test
        
        catch (Exception e) {
           
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	Assert.fail("Test failed due to unexpected conditions  ");
        } 

    }

    
@AfterMethod
    
    public void screenshot() throws IOException {
    	
    	TakesScreenshot ts= (TakesScreenshot) driver;
    	File sourcefile= ts.getScreenshotAs(OutputType.FILE);
    	File destinyFile=new File("./Screenshots./Output1");
    	FileUtils.copyFile(sourcefile, destinyFile);
    	
    	
    }
    
    
 
    
    @AfterClass
    public void tearDown() {
        try {
 
           
                driver.quit();
            
        } catch (Exception e) {
     
            System.err.println("Failed to close the browser: " + e.getMessage());
        }
    }
}