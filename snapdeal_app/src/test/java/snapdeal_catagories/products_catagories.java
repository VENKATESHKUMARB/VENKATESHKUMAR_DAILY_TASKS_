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
import org.testng.annotations.AfterClass;
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

    @BeforeMethod (groups= {"catagories_visible"})
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path

     
        driver = new ChromeDriver();

        
        driver.manage().window().maximize();

   
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 1,groups= {"catagories_visible"})
    public void testProduct_catagories_Visibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // "Sign In" button to be Hover
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
    }

    @Test(priority = 2,groups= {"MensFashion_access"})
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