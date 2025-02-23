package products_visible;

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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class products_visibility {

    WebDriver driver;
    
    private static final Logger logger = LoggerFactory.getLogger(products_visibility.class);

    @BeforeClass(groups= "search_productvisibility")
    public void setUp() {
      
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path

        
        driver = new ChromeDriver();

       
        driver.manage().window().maximize();

       
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 1,groups= {"search_productvisibility"},description="Verify the Products are Visibility")
    public void SearchAndProductVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
           
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("inputValEnter")));
            
            Reporter.log("click on the search Box ",true);
      
            searchBox.click();
            searchBox.sendKeys("earphone");
            
            //Press the Enter key 
            searchBox.sendKeys(Keys.RETURN);

            Reporter.log("Enter the Keywords to find the products ",true);
            
         
            WebElement searchProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'comp-right-wrapper')]")));


            Assert.assertTrue(searchProduct.isDisplayed(), "The search products are not visible on the website.");
            		
            
            

            System.out.println("Test passed: Products are visible after search.");
            
            logger.info("Testcase is Passed");
            
            
        } 
        
  
        
        catch (Exception e) {
      
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: ");
        }
        
        
        Reporter.log("Verify the Products Serach & Products Visibility",true);
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testSearchAndProductVisibility.png\">Products_visibilityclick</a>");
        
        
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
    
    
    
    
    // Close the browser after the test

    @AfterClass
    public void shutDown() {
       
       
            driver.quit();
        
    }
}