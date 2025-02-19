package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Searchbar_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Searchbar_test_page {

    WebDriver driver;
    Searchbar_page searchBarPage;
    
    private static final Logger logger = LoggerFactory.getLogger(Searchbar_test_page.class);

    @BeforeClass(groups = "searchbarclick")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");
        searchBarPage = new Searchbar_page(driver);
    }

    @Test(priority = 1, groups = {"searchbarvisible"})
    public void testSearchBarVisibility() {
        try {
          
            boolean isVisible = searchBarPage.isSearchBarVisible();
            Assert.assertTrue(isVisible, "The search bar is not visible on the website.");

            System.out.println("Test passed: Search bar is visible.");
        
            logger.info("My SearchBar is Visible TestCase is Passed");
        
        } catch (Exception e) {
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
             Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

    @Test(priority = 2, groups = {"searchbarclick"})
    public void testSearchBarClickable() {
        try {
       
            searchBarPage.performSearch("fastrack");

            System.out.println("Test passed: Search bar is clickable and search performed successfully.");

            logger.info("My SearchBar is Visible TestCase is Passed");
        } catch (Exception e) {
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
             Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

    @AfterMethod
    public void screenshot(ITestResult result) throws Exception {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass(groups = "searchbarclick")
    public void shutDown() {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("Browser closed.");
            }
        } catch (Exception e) {
            System.out.println("Error during teardown: " + e.getClass().getSimpleName());
            Assert.fail("Error during teardown: " + e.getMessage());
        }
    }
}
