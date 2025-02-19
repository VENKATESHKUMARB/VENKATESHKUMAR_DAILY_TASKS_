package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Products_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Products_test_page {

    WebDriver driver;
    Products_page productsPage;

    private static final Logger logger = LoggerFactory.getLogger(Products_test_page.class);
    
    @BeforeClass(groups = {"productsdisplay"})
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");
        productsPage = new Products_page(driver);
    }

    @Test(priority = 1, groups = {"productsdisplay"})
    public void testProductDetails() {
        try {
            productsPage.searchForProduct("iron");
            System.out.println("Test passed: Search performed successfully.");
        
            logger.info("'Products' are Visible Testcase is Passed");
        
        } catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

    @Test(priority = 2, groups = {"productsdisplay"})
    public void testProductDetailPage() {
        try {
            productsPage.searchForProduct("iron");
            productsPage.clickOnProduct();
            productsPage.switchToProductDetailPage();
            boolean isProductDescriptionVisible = productsPage.isProductDescriptionVisible();
            Assert.assertTrue(isProductDescriptionVisible, "Product description is not visible.");
            System.out.println("Test passed: Product detail page is visible.");
            logger.info("'Products Detail page' are Visible Testcase is Passed");
        
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

    @AfterClass(groups = {"productsdisplay"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
