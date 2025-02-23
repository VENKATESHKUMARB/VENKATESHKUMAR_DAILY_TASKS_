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
public class signin_visibility {

    WebDriver driver;
    
    
    private static final Logger logger = LoggerFactory.getLogger(signin_visibility.class);

    @BeforeClass (groups= {"signin"})
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path

     
        driver = new ChromeDriver();

        
        driver.manage().window().maximize();

   
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 1,groups= {"signin"},description="Verify the SignIn Button is Visible")
    public void SignInButtonVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // "Sign In" button to be Hover
            WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Sign In']")));

         

            String actualCartItem = signInButton.getText(); 
            String expectedCartItem = "Sign In";
            Assert.assertEquals(actualCartItem, expectedCartItem, "Sign In Button is not Visible.");
            
            Reporter.log("Verify the SignIn  is Hover",true);
         
            System.out.println("Test passed: 'Sign In' button is visible.");
            
            logger.info("Testcase is Passed");
            
            
            Reporter.log("Verify the Register  is Visible",true);
            
            
        } 
        
        
        // Log the exception and fail the test
        
        catch (Exception e) {
           
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
             Assert.fail("Test failed due to exception: ");
             
            
        }
    
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\SignInButtonVisibility.png\">SignIn_Visible_Click</a>");
    }

    @Test(priority = 2,groups= {"signin"},description="Verify the Signin Button is Clickable")
    public void SignInButtonClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
          
            WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Sign In']")));

            // Hover the "Sign In" button
            actions.moveToElement(signInButton).perform();
            
            
            Reporter.log("Verify the SignIn  is Hover",true);
            
            

            //  "Register" button to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(signInButton));

            // Click the "Register" button
            signInButton.click();

       
            System.out.println("Test passed: 'Sign In' button is clickable.");
            
            logger.info("Testcase is Passed");
            
            
            
            Reporter.log("Verify the Register  is Clickable",true);
            
        } 
        
        
        // Log the exception and fail the test
        
        catch (Exception e) {
      
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: ");
            
        }
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\SignInButtonClickable.png\">SignIn_Click</a>");
    }

    
//  ScreenShots
    
  @AfterMethod
  public void screenshot(ITestResult result) throws IOException 
  {
      // Take a screenshot after each test
      TakesScreenshot ts = (TakesScreenshot) driver;
      File sourceFile = ts.getScreenshotAs(OutputType.FILE);

      String methodName = result.getMethod().getMethodName();
      File destinyFile = new File("./Screenshots/" + methodName + ".png");

      FileUtils.copyFile(sourceFile, destinyFile);
      
  }

    
    
    
    
    // Close the browser after test execution
    
    @AfterClass(groups= {"signin"})
    public void shutDown() {

        
            driver.quit();
        
    }
}