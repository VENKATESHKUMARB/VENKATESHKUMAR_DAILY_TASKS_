package Login_test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Catagories_page;
import utils.ScreenshotUtils;

import java.io.IOException;

public class Categories_test_page {

    WebDriver driver;
    Catagories_page categoriesPage;

    private static final Logger logger = LoggerFactory.getLogger(Categories_test_page.class);
    
    @BeforeClass(groups = "catagories")
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.snapdeal.com");
            categoriesPage = new Catagories_page(driver);
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
            Assert.fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 1, groups = "catagories")
    public void testCategoryBannerVisibility() {
        try {
            boolean isVisible = categoriesPage.isCategoryBannerVisible();
            Assert.assertTrue(isVisible, "Categories banner is not visible.");
            System.out.println("Test passed: 'Categories Banner' is visible.");
            
            logger.info("Catagories banner Testcase is Passed");
        } catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: ");
        }
    }

    @Test(priority = 2, groups = "catagories")
    public void testCategoryAccessAndClick() {
        try {
            categoriesPage.hoverOverCategories();
            categoriesPage.hoverAndClickBackpacks();
            System.out.println("Test passed: 'Backpacks' category is clickable.");
            logger.info("catagories Backpacks Testcase is Passed");
        
        } 
        
        catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: ");
        }
    }

 
    @AfterMethod
    public void screenshot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass(groups = "catagories")
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
