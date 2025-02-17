package Snapdeal_login;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snapdeal_login {

    WebDriver driver;
    
    private static final Logger logger = LoggerFactory.getLogger(Snapdeal_login.class);

    @BeforeMethod (groups= {"functional_login"})
    public void setUp() {
     
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); 

        driver = new ChromeDriver();

      
        driver.manage().window().maximize();

      
        driver.get("https://www.snapdeal.com");
    }

    @Test (groups= {"functional_login"})
    public void test_Login() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
            //  Hover over the "Sign In" button 
            WebElement signInButton = driver.findElement(By.xpath("//span[text()='Sign In']"));
            actions.moveToElement(signInButton).perform();

            // Step 2: Wait and click the "Login" button
            WebElement loginButton = driver.findElement(By.xpath("//a[text()='login']"));
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();

            //  iframe and switch to it
            WebElement iframeLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginIframe")));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLogin));

            //Enter the username/email
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
            usernameField.sendKeys("9025095675");

            // Click on "Continue" after entering username/email
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkUser")));
            continueButton.click();

            // Wait for OTP field and duplicate enter OTP
            WebElement otpField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginOtpUC\"]/div[1]/input")));
            otpField.sendKeys("123456");

            // Step 7: Click on "Continue" after entering OTP
            WebElement continueOtpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='loginUsingOtp'])[1]")));
            continueOtpButton.click();
            
            WebElement incorrectotp=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'authError ')]")));
            
            String actualCartItem = incorrectotp.getText(); 
            String expectedCartItem = "Incorrect verification code";
            Assert.assertEquals(actualCartItem, expectedCartItem, "Login is not Successfully Done");
            
            
            logger.info("Testcase is Passed");
        } 
        
        
        catch (Exception e) {
           
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	 Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

 
@AfterMethod
    
    public void screenshot() throws IOException {
    	
    	TakesScreenshot ts= (TakesScreenshot) driver;
    	File sourcefile= ts.getScreenshotAs(OutputType.FILE);
    	File destinyFile=new File("./Screenshots./Output1");
    	FileUtils.copyFile(sourcefile, destinyFile);
    	
    	
    }
    
    
    
    
    
    @AfterMethod
    public void tearDown() {
       
      
            driver.quit();
        
    }
}
