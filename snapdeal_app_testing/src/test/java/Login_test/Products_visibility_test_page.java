package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Search_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Products_visibility_test_page {

    WebDriver driver;
    Search_page searchPage;

    private static final Logger logger = LoggerFactory.getLogger(Products_visibility_test_page.class);
    
    @BeforeClass(groups = "search_productvisibility")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");
        searchPage = new Search_page(driver);
    }

    @Test(priority = 1, groups = {"search_productvisibility"})
    public void testSearchAndProductVisibility() {
        try {
            searchPage.searchForProduct("earphone");

      
            boolean isVisible = searchPage.isProductVisible();
            Assert.assertTrue(isVisible, "The search products are not visible on the website.");

            System.out.println("Test passed: Products are visible after search.");

            logger.info(" 'My Searched Products' page is visible Testcase is Passed ");
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

    @AfterClass(groups = {"search_productvisibility"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
