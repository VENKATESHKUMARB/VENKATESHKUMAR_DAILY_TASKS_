package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import snapdeal_pages.Cartproductsremoval_page;
import utils.ScreenshotUtils;

import java.io.IOException;

public class Cartproductsremoval_test_page {

    WebDriver driver;
    Cartproductsremoval_page cartPage;
    private static final Logger logger = LoggerFactory.getLogger(Cartproductsremoval_test_page.class);

    @BeforeClass(groups = "product_remove")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");

       
        cartPage = new Cartproductsremoval_page(driver);
    }

    @Test(groups = "product_remove")
    public void testRemoveProductFromCart() throws InterruptedException {
        try {
        
            cartPage.searchForProduct("shoes");
            cartPage.clickProduct();
            cartPage.addToCart();

         
            cartPage.viewCart();
            cartPage.removeProductFromCart();

         
            Assert.assertTrue(cartPage.isCartEmpty(), "The cart is not empty after removing the product.");
            logger.info("Test passed: Cart is empty after product removal.");
        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void screenshot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass(groups = "product_remove")
    public void shutDown() {
        driver.quit();
    }
}
