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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class signin_visibility {

    WebDriver driver;
    
    
    private static final Logger logger = LoggerFactory.getLogger(signin_visibility.class);

    @BeforeMethod (groups= {"signinvisible"})
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path

     
        driver = new ChromeDriver();

        
        driver.manage().window().maximize();

   
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 1,groups= {"signinvisible"})
    public void testSignInButtonVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // "Sign In" button to be Hover
            WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Sign In']")));

         

            String actualCartItem = signInButton.getText(); 
            String expectedCartItem = "Sign In";
            Assert.assertEquals(actualCartItem, expectedCartItem, "Sign In Button is not Visible.");
            
            
         
            System.out.println("Test passed: 'Sign In' button is visible.");
            
            logger.info("Testcase is Passed");
        } 
        
        
        // Log the exception and fail the test
        
        catch (Exception e) {
           
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
             Assert.fail("Test failed due to exception: ");
        }
    }

    @Test(priority = 2,groups= {"signinclickable"})
    public void testSignInButtonClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
          
            WebElement signInButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Sign In']")));

            // Hover the "Sign In" button
            actions.moveToElement(signInButton).perform();

            //  "Register" button to be clickable
            wait.until(ExpectedConditions.elementToBeClickable(signInButton));

            // Click the "Register" button
            signInButton.click();

       
            System.out.println("Test passed: 'Sign In' button is clickable.");
            
            logger.info("Testcase is Passed");
        } 
        
        
        // Log the exception and fail the test
        
        catch (Exception e) {
      
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: ");
        }
    }

    
//  ScreenShots
    
  @AfterMethod
  public void screenshot(ITestResult result) throws IOException 
  {
      // Take a screenshot after each test
      TakesScreenshot ts = (TakesScreenshot) driver;
      File sourceFile = ts.getScreenshotAs(OutputType.FILE);

      String methodName = result.getMethod().getMethodName();
      File destinyFile = new File("./Screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png");

      FileUtils.copyFile(sourceFile, destinyFile);
      
  }

    
    
    
    
    // Close the browser after test execution
    
    @AfterClass
    public void tearDown() {

        
            driver.quit();
        
    }
}