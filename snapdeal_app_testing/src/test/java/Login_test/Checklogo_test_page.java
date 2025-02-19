package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Check_logo;
import utils.ScreenshotUtils;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Checklogo_test_page {

    WebDriver driver;
   Check_logo logoPage;

   
   private static final Logger logger = LoggerFactory.getLogger(Checklogo_test_page.class);
   
    @BeforeClass(groups = "logo")
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update path
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.snapdeal.com");
            logoPage = new Check_logo(driver);
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
            Assert.fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    @Test(groups = "logo")
    public void testLogoVisibility() {
        try {
            
            boolean isLogoVisible = logoPage.isLogoVisible();
            Assert.assertTrue(isLogoVisible, "The Snapdeal logo is not visible on the website.");

            System.out.println("Test passed: Snapdeal logo is visible.");
            logger.info("Snapdeal Logo Testcase is Passed");
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

    @AfterClass(groups = "logo")
    public void shutDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed.");
            }
        } catch (Exception e) {
            System.out.println("Error during teardown: " + e.getMessage());
            Assert.fail("Error during teardown: " + e.getMessage());
        }
    }
}
