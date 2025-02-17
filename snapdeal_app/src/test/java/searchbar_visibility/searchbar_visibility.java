package searchbar_visibility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class searchbar_visibility {

    WebDriver driver;
    
    private static final Logger logger = LoggerFactory.getLogger(searchbar_visibility.class);

    @BeforeMethod(groups= {"searchbarclick"})
    public void setUp() {
  
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path

  
        driver = new ChromeDriver();

        driver.manage().window().maximize();

   
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 1,groups= {"searchbarvisible"})
    public void testSearchbarVisibility() {
        try {
            //search bar to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            
            WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("inputValEnter")));

       
            Assert.assertTrue(searchBar.isDisplayed(), "The search bar is not visible on the website.");

    
            System.out.println("Test passed: Search bar is visible.");
            
            logger.info("Testcase is Passed");
            
        } 
        
       
        
        catch (Exception e) {
         
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: ");
        }
    }

    @Test(priority = 2,groups= {"searchbarclick"})
    public void testSearchbarClickable() {
        try {
          
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

       
            WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("inputValEnter")));

            // Click the search bar
            searchBar.click();

            
            searchBar.sendKeys("fastrack");
            
            //Press the Enter Key
            searchBar.sendKeys(Keys.RETURN);


            System.out.println("Test passed: Search bar is clickable and search performed successfully.");
            logger.info("Testcase is Passed");
            
        } 
        
        
        // Log for the exception in searchbar_clickable and fail the test
        
        catch (Exception e) {
       
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: ");
        }
    }
    
    
//  ScreenShots
    
  @AfterMethod
  public void screenshot(ITestResult result) throws IOException {
  
      TakesScreenshot ts = (TakesScreenshot) driver;
      File sourceFile = ts.getScreenshotAs(OutputType.FILE);

 
      String methodName = result.getMethod().getMethodName();
      File destinyFile = new File("./Screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png");

    
      FileUtils.copyFile(sourceFile, destinyFile);
      
  }
    
    
    
    
    // Close the browser after the test

    @AfterClass
    public void tearDown() {

        
            driver.quit();
        
    }
}