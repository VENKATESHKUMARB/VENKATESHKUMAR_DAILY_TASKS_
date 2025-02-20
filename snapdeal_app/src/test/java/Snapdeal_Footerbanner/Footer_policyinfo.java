package Snapdeal_Footerbanner;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class Footer_policyinfo {
	
	
WebDriver driver;
    
    private static final Logger logger = LoggerFactory.getLogger(Footer_policyinfo.class);
    
    @BeforeClass(groups= "Policyinfo")
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); 

  
        driver = new ChromeDriver();

       
        driver.manage().window().maximize();

    
        driver.get("https://www.snapdeal.com");
    }

    @Test(priority = 1,groups= {"Policyinfo"})
    public void testfooter_policyinfo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
  
            
            //  "Footer"  to be Hover
            WebElement Footer_policy = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[text()='Policy Info']")));

         
            actions.moveToElement(Footer_policy).perform();

            

 

            
            String actualCartItem = Footer_policy.getText(); 
            String expectedCartItem = "POLICY INFO";
            Assert.assertEquals(actualCartItem, expectedCartItem, "Policy Info is not Visible.");

       
            System.out.println("Test passed: Footer in 'Policy info' is visible.");
            
             logger.info("Testcase is Passed");
            
        } 
        
        // Log the exception and fail the test
        
        catch (Exception e) {
         
            logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
           Assert.fail("Test failedn due to unexpected conditions  ");
        }
        
        
        
Reporter.log("Verify the  Footer in 'Policy info' is visible.");
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testfooter_policyinfo.png\">click</a>");
        
        
    }
    

    @Test(priority = 2,groups= "Policyinfo")
    public void testFooter_policyList() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Actions actions = new Actions(driver);

        try {
  
            
            //  "Footer-Privacy list"  to be Hover
            WebElement policyList_privacy = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Privacy Policy']")));

         
            actions.moveToElement(policyList_privacy).perform();

            

            

            
            String actuallistItem = policyList_privacy.getText(); 
            String expectedlistItem = "Privacy Policy";
            Assert.assertEquals(actuallistItem, expectedlistItem, "Privacy Policy is not Visible.");

       
            System.out.println("Test passed: Footer in 'Privacy Policy' is visible.");
            
            
            
            
            
        //  "Terms of Sale"  to be Hover
            WebElement policyList_termsofsale = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Terms of Sale']")));

         
            actions.moveToElement(policyList_termsofsale).perform();
            

            
            String actualCartItem2 = policyList_termsofsale.getText(); 
            String expectedCartItem2= "Terms of Sale";
            Assert.assertEquals(actualCartItem2, expectedCartItem2, "Terms of Sale is not Visible.");

       
            System.out.println("Test passed: Footer in 'Terms of Sale' is visible.");
            
            
        //  "Terms of use"  to be Hover
            WebElement policyList_termsofuse = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Terms of Use']")));

         
            actions.moveToElement(policyList_termsofuse).perform();
            

            
            String actuallistItem3 = policyList_termsofuse.getText(); 
            String expectedlistItem3= "Terms of Use";
            Assert.assertEquals(actuallistItem3, expectedlistItem3, "Terms of use is not Visible.");

       
            System.out.println("Test passed: Footer in 'Terms of use' is visible.");
            
            
            
        //  "Takedown Policy"  to be Hover
            WebElement policyList_takedownpolicy = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Report Abuse & Takedown Policy']")));

         
            actions.moveToElement(policyList_takedownpolicy).perform();
            

            
            String actuallistItem4 =policyList_takedownpolicy.getText(); 
            String expectedlistItem4= "Report Abuse & Takedown Policy";
            Assert.assertEquals(actuallistItem4, expectedlistItem4, "Takedown Policy is not Visible.");

       
            System.out.println("Test passed: Footer in 'Takedown Policy' is visible.");
            
            
            
            
            //  "BIS Standard Policy"  to be Hover
            WebElement policyList_BIS = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Know Your BIS Standard']")));

         
            actions.moveToElement(policyList_BIS).perform();
            

            
            String actuallistItem5 =policyList_BIS.getText(); 
            String expectedlistItem5= "Know Your BIS Standard";
            Assert.assertEquals(actuallistItem5, expectedlistItem5, "BIS Standard Policy is not Visible.");

       
            System.out.println("Test passed: Footer in 'BIS Standard Policy' is visible.");
            
            
            
            
        //  " BIS Certification Policy"  to be Hover
            WebElement policyList_BISCertification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='Products Under Cumpulsory BIS Certification']")));

         
            actions.moveToElement(policyList_BISCertification).perform();
            

            
            String actuallistItem6 =policyList_BISCertification.getText(); 
            String expectedlistItem6= "Products Under Cumpulsory BIS Certification";
            Assert.assertEquals(actuallistItem6, expectedlistItem6, "policyList_BISCertification is not Visible.");

       
            System.out.println("Test passed: Footer in 'Products Under Cumpulsory BIS Certification' is visible.");
            
            
            //  " FAQ"  to be Hover
            WebElement policyList_FAQ = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[text()='FAQ']")));

         
            actions.moveToElement(policyList_FAQ).perform();
            

            
            String actuallistItem7 =policyList_FAQ.getText(); 
            String expectedlistItem7= "FAQ";
            Assert.assertEquals(actuallistItem7, expectedlistItem7, "policyList_FAQ is not Visible.");

       
            System.out.println("Test passed: Footer in 'FAQ' is visible.");
            
            
            
    
            
           
            
            
             logger.info("Footer policy Info List Testcase is Passed");
            
        } 
        
        // Log the exception and fail the test
        
        catch (Exception e) {
         
            logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
           Assert.fail("Test failedn due to unexpected conditions  ");
        }
        
        
Reporter.log("Verify the  Footer policy Info List");
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testFooter_policyList.png\">click</a>");
        
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
    
  
    
    
    // Close the browser after Test execution
    
    @AfterClass(groups="Policyinfo")
    public void shutDown() {
        try {
           
                driver.quit();
            
        } 
        catch (Exception e) {
            System.out.println("Failed to close the driver: " + e.getMessage());
        }
    }
	
	
	

}
