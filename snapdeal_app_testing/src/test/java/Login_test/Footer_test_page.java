package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Footer_page;
import utils.ScreenshotUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class Footer_test_page {

    WebDriver driver;
    Footer_page footerPolicyPage;

    private static final Logger logger = LoggerFactory.getLogger(Footer_test_page.class);
    
    @BeforeClass(groups = "Policyinfo")
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update path
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.snapdeal.com");
            footerPolicyPage = new Footer_page(driver);
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
            Assert.fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 1, groups = "Policyinfo")
    public void testFooterPolicyInfo() {
        try {
            footerPolicyPage.hoverOverPolicyInfo();
            System.out.println("Test passed: Footer 'Policy Info' is visible.");
            logger.info("Footer 'Policy Info' Test Case is Passed");
        
        } 
        
        catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

    @Test(priority = 2, groups = "Policyinfo")
    public void testFooterPolicyList() {
        try {
            footerPolicyPage.hoverAndVerifyPrivacyPolicy();
            System.out.println("Test passed: 'Privacy Policy' is visible.");
            
            footerPolicyPage.hoverAndVerifyTermsOfSale();
            System.out.println("Test passed: 'Terms of Sale' is visible.");
            
            footerPolicyPage.hoverAndVerifyTermsOfUse();
            System.out.println("Test passed: 'Terms of Use' is visible.");
            
            footerPolicyPage.hoverAndVerifyReportAbuse();
            System.out.println("Test passed: 'Report Abuse & Takedown Policy' is visible.");
            
            footerPolicyPage.hoverAndVerifyBISStandard();
            System.out.println("Test passed: 'Know Your BIS Standard' is visible.");
            
            footerPolicyPage.hoverAndVerifyBISCertification();
            System.out.println("Test passed: 'Products Under Compulsory BIS Certification' is visible.");
            
            footerPolicyPage.hoverAndVerifyFAQ();
            System.out.println("Test passed: 'FAQ' is visible.");
            
            logger.info("Footer 'Policy Info List' Test Case is Passed");
            
        } catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

   
    @AfterMethod
    public void screenshot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

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
