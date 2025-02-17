package Snapdeal_Product_search;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

import io.github.bonigarcia.wdm.WebDriverManager;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class product_search {
   WebDriver driver;
    
   private static final Logger logger = LoggerFactory.getLogger(product_search.class);

    @BeforeClass (groups= {"validproduct"})
    public void setUp() {
        try {
           
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("Driver initialized successfully.");
        } 
        
        catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 1,groups= {"validproduct"})
    public void searchValidProduct() {
        try {
          
            driver.get("https://www.snapdeal.com/");
            System.out.println("Opened Snapdeal website.");

         
            WebElement searchBar = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("inputValEnter")));
            searchBar.click();
            System.out.println("Clicked on search bar.");

            // Enter a valid product name (for example, 'shoes')
            searchBar.sendKeys("shoes");
            System.out.println("Entered product name 'xer5' into search bar.");

      
            WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
            searchIcon.click();
            System.out.println("Clicked on search icon.");

            WebElement firstProduct = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class, 'product-tuple-image')])[1]")));
            
            Assert.assertTrue(firstProduct.isDisplayed(), "No products found for the search query.");
            
            System.out.println("Test Passed: Valid product search results are displayed.");
            
            logger.info("Testcase is Passed");
        } 
        
        
        catch (NoSuchElementException e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	Assert.fail("Test failed due to unexpected conditions  ");
        }
    }

    @Test(priority = 2,groups= {"invalidproduct"})
    public void searchInvalidProduct() {
        try {
         
            driver.get("https://www.snapdeal.com/");
            System.out.println("Opened Snapdeal website.");

      
            WebElement searchBar = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("inputValEnter")));
            searchBar.click();
            System.out.println("Clicked on search bar.");

            // Enter an invalid product name (for example, 'xhgr')
            searchBar.sendKeys("xhgr");
            System.out.println("Entered invalid product name 'xhgr' into search bar.");

       
            WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
            searchIcon.click();
            System.out.println("Clicked on search icon.");

          
            WebElement noResultsMessage = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'alert-heading')]")));
            
            
            
            
            String actualCartItem = noResultsMessage.getText(); 
            String expectedCartItem = "Oops! Looks like something went wrong, please try again in sometime.";
            Assert.assertEquals(actualCartItem, expectedCartItem, "There is No products is not Visible.");
            
            
            
            
            System.out.println("Test Passed: No products found for invalid product search.");
            logger.info("Testcase is Passed");
            
            
        } 
        
        catch (NoSuchElementException e) {
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
    
    
    
    
    

    // Close the browser
    
    @AfterClass
    public void tearDown() {
        try {
        
           
                driver.quit();
                System.out.println("Browser closed successfully.");
       
        } catch (Exception e) {
            System.out.println("Error during tearDown: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
