package Snapdeal_login;

import java.io.File;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
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
public class Snapdeal_logo {

    WebDriver driver;
    
    private static final Logger logger = LoggerFactory.getLogger(Snapdeal_logo.class);

    @BeforeClass (groups="logo")
    public void setUp() {
       
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); 

    
        driver = new ChromeDriver();

  
        driver.manage().window().maximize();

     
        driver.get("https://www.snapdeal.com");
    }

    @Test (groups= "logo" ,description="Verify the Brand Logo's are Displayed ")
    public void LogoVisibility() {
        try {
            //  logo to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

           
            WebElement snapdealLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[contains(@class,'notIeLogoHeader')]")));

        
    Assert.assertTrue(snapdealLogo.isDisplayed(), "The Snapdeal logo is not visible on the website.");

            
            
     
            System.out.println("Test passed: Snapdeal logo is visible.");
            
            logger.info("Testcase is Passed");
            
            
        } 
        
        // Log for the exception Snapdeal logo and fail the test
        
        catch (Exception e) {
         
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	Assert.fail("Test failedn due to unexpected conditions  ");
        } 
        
   
        Reporter.log("Verify the  Snapdeal_brand Logo's are displayed.",true);
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testLogoVisibility.png\">SnapdealLogo_Click</a>");
       
    }
    
    
    @AfterMethod
    public void screenshot(ITestResult result) throws IOException {
   
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

   
        String methodName = result.getMethod().getMethodName();
        File destinyFile = new File("./Screenshots/" + methodName + ".png");

       
        FileUtils.copyFile(sourceFile, destinyFile);
        

        
    }

    
 
 
 @AfterClass(groups= "logo")
 public void shutDown() {
     
         driver.quit();
     
 }
    
    
    
    
}