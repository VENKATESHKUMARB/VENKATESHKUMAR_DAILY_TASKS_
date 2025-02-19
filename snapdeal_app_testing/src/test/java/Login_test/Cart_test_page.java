package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Cart_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cart_test_page {

    WebDriver driver;
    Cart_page cartPage;

    private static final Logger logger = LoggerFactory.getLogger(Cart_test_page.class);
    @BeforeClass(groups = "cartpage")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");
        cartPage = new Cart_page(driver);
    }

    @Test(groups = "cartpage")
    public void testCartItem() throws InterruptedException {
        try {
            cartPage.addProductToCart("fastrack");
            boolean isProductInCart = cartPage.verifyProductInCart("Walrus Lmw-Tm-010907WAS PU Analog Men's Watch");
            Assert.assertTrue(isProductInCart, "The item was not added to the cart correctly.");

            System.out.println("My cartItem TestCase is Successfully Executed");
            logger.info("Testcase is Passed");
        } catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	Assert.fail("Test failed due to unexpected conditions  ");
        }
    }

    @AfterMethod
    public void screenshot(ITestResult result) throws Exception {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass(groups = "cartpage")
    public void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
