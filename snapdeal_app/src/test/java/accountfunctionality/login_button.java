package accountfunctionality;

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
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

public class login_button {

    WebDriver driver;
    
    private static final Logger logger = LoggerFactory.getLogger(login_button.class);
    
   
    
    

    @BeforeClass(groups= {"login"})
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); 

  
        driver = new ChromeDriver();

       
        driver.manage().window().maximize();

    
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 1,groups= {"login"},description="Verify the login Button is Visible ")
    public void LoginButtonVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
            //  "Sign In" button to be Hover
            WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Sign In']")));
          
            actions.moveToElement(signInButton).perform();
            
            Reporter.log("Verify the SignIn is  Hover",true);

            WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='login']")));
            
          
            

            
            String actualCartItem = login.getText(); 
            String expectedCartItem = "LOGIN";
            Assert.assertEquals(actualCartItem, expectedCartItem, "Login Button is not Visible.");

       
            System.out.println("Test passed: 'login' button is visible.");
            
            logger.info("Testcase is Passed");
    
            
        } 
        
        // Log the exception and fail the test
        
        catch (Exception e) {
         
            logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
           Assert.fail("Test failedn due to unexpected conditions  ");
        }
        
        Reporter.log("Verify the login Button is Visible");
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testLoginButtonVisibility.png\">Login_button_Click</a>");

    }
    

    @Test(priority = 2,groups= {"login"},description="Verify the login Button is Clickable")
    public void LoginButtonClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
        	
        	
        	 WebElement signInButton_login = wait.until(ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//span[text()='Sign In']")));
        	 
        	 Reporter.log("Verify the SignIn is Hover",true);
   
             actions.moveToElement(signInButton_login).perform();

          
            WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='login']")));

            
            actions.moveToElement(loginButton).perform();

            //  "login" button to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));

          
            loginButton.click();
            
           
            
            Thread.sleep(5000);

         
            System.out.println("Test passed: 'Login' button is clickable.");
            
            logger.info("Testcase is Passed");
            
            
        } 
        
        

        
        
        
        
        
        // Log the exception and fail the test
        catch (Exception e) {
      
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	Assert.fail("Test failedn due to unexpected conditions  ");
        }
        
//        String imgTag = "<img src='C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testLoginButtonClickable.png'  alt='Screenshot for  width='500' height='300'/>";
//        Reporter.log(imgTag);
        
        Reporter.log("Verify the login Button is Clickable",true);
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testLoginButtonClickable.png\">Login_button_Click</a>");
        
    }

    
    
    
    
    
//    ScreenShots
    
    @AfterMethod
    public void screenshot(ITestResult result) throws IOException {
   
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

   
        String methodName = result.getMethod().getMethodName();
        File destinyFile = new File("./Screenshots/" + methodName + ".png");

       
        FileUtils.copyFile(sourceFile, destinyFile);
        

        
    }

    
  
    
    
    // Close the browser after Test execution
    
    @AfterClass(groups= {"login"})
    public void shutDown() {
        try {
           
                driver.quit();
            
        } 
        catch (Exception e) {
            System.out.println("Failed to close the driver: " + e.getMessage());
        }
    }
}