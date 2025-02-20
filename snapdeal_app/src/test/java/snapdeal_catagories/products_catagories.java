package snapdeal_catagories;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class products_catagories {

    WebDriver driver;
    
    
    private static final Logger logger = LoggerFactory.getLogger(products_catagories.class);




    @BeforeClass (groups= "catagories")
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path

     
        driver = new ChromeDriver();

        
        driver.manage().window().maximize();

   
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 1,groups= "catagories")
    public void testProduct_catagories_Visibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // "catagories" banner to be Hover
            WebElement catagory_banner = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'topCats')]")));

         

            String actualCartItem = catagory_banner.getText(); 
            String expectedCartItem = "TOP CATEGORIES";
            Assert.assertEquals(actualCartItem, expectedCartItem, "catogories banner is not Visible.");
            
            
         
            System.out.println("Test passed: 'Catagories Banner' is visible.");
            
            logger.info("Testcase is Passed");
        } 
        
        
        // Log the exception and fail the test
        
        catch (Exception e) {
           
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
             Assert.fail("Test failed due to exception: ");
        }
        
        
Reporter.log("Verify the  'Catagories Banner' is visible");
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testProduct_catagories_Visibility.png\">click</a>");
        
    }

    @Test(priority = 2,groups= "catagories")
    public void testCatagory_MensFashion_access() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
          
            WebElement catagories_access = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(@class,'catText')]")));

            // Hover the "Catagory banner" 
            actions.moveToElement(catagories_access).perform();
            
            
            WebElement backpacks_access = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[text()='Bags & Luggage']")));
            
            
            String actualCartItem = backpacks_access.getText(); 
            String expectedCartItem = "BAGS & LUGGAGE";
            Assert.assertEquals(actualCartItem, expectedCartItem, "Bags & Luggage catagory  is not Visible.");
            

            // Hover the "Sign In" button
            actions.moveToElement(backpacks_access).perform();
            
            
//              "Register" button to be clickable
        WebElement  Backpacks=  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='rightMenuLink noHasTagWidth dp-widget-link visibleRgtBlkLnk']//span[@class='linkTest'][normalize-space()='Backpacks']")));

          // Click the "Register" button
        Backpacks.click();
            
            

       
            System.out.println("Test passed: 'Backpacks' catagory is clickable.");
            
            logger.info("Testcase is Passed");
        } 
        
        
        // Log the exception and fail the test
        
        catch (Exception e) {
      
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: ");
        }
        
        Reporter.log("Verify the  'Backpacks' catagory is clickable.");
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testCatagory_MensFashion_access.png\">click</a>");
        
    }

    
//  ScreenShots
    
  @AfterMethod
  public void screenshot(ITestResult result) throws IOException {
     
      TakesScreenshot ts = (TakesScreenshot) driver;
      File sourceFile = ts.getScreenshotAs(OutputType.FILE);

    
      String methodName = result.getMethod().getMethodName();
      File destinyFile = new File("./Screenshots/" + methodName + ".png");

   
      FileUtils.copyFile(sourceFile, destinyFile);
      
  }
    
    
    
    
    // Close the browser after test execution
    
 @AfterClass(groups= "catagories")
    public void shutDown() {
            driver.quit();
        
    }
}