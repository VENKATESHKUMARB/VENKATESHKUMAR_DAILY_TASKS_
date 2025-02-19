package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Valid_invalidproducts_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;

public class Valid_invaliproducts_test_page {

    WebDriver driver;
    Valid_invalidproducts_page productSearchPage;
    
    private static final Logger logger = LoggerFactory.getLogger(Valid_invaliproducts_test_page.class);

    @BeforeClass(groups = "productsearch")
    public void setUp() {
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            productSearchPage = new Valid_invalidproducts_page(driver);
            System.out.println("Driver initialized successfully.");
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
            Assert.fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 1, groups = "productsearch")
    public void searchValidProduct() {
        try {
            driver.get("https://www.snapdeal.com/");
            System.out.println("Opened Snapdeal website.");

           
            productSearchPage.clickSearchBar();
            productSearchPage.enterProductName("shoes");
            productSearchPage.clickSearchIcon();

            
            boolean isProductDisplayed = productSearchPage.isFirstProductDisplayed();
            Assert.assertTrue(isProductDisplayed, "No products found for the search query.");
            
            System.out.println("Test Passed: Valid product search results are displayed.");
        
            logger.info("'Valid Products' TestCase is Passed");
        
        } catch (Exception e) {
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
             Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

    @Test(priority = 2, groups = "productsearch")
    public void searchInvalidProduct() {
        try {
            driver.get("https://www.snapdeal.com/");
            System.out.println("Opened Snapdeal website.");

            // Perform search for an invalid product 'xhgr'
            productSearchPage.clickSearchBar();
            productSearchPage.enterProductName("xhgr");
            productSearchPage.clickSearchIcon();

           
            String noResultsMessage = productSearchPage.getNoResultsMessage();
            String expectedMessage = "Oops! Looks like something went wrong, please try again in sometime.";
            Assert.assertEquals(noResultsMessage, expectedMessage, "No products found message is incorrect.");
            
            System.out.println("Test Passed: No products found for invalid product search.");
        
            logger.info("'Invalid Products' TestCase is Passed");
        
        } catch (Exception e) {
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
             Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

    
    @AfterMethod(groups = "productsearch")
    public void screenshot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass(groups = "productsearch")
    public void shutDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error during teardown: " + e.getMessage());
            Assert.fail("Error during teardown: " + e.getMessage());
        }
    }
}
