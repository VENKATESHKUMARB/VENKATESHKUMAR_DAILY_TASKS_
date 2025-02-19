package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.signin_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Signin_test_page {

    WebDriver driver;
    signin_page signInPage;

    private static final Logger logger = LoggerFactory.getLogger(Signin_test_page.class);
    
    @BeforeClass(groups = {"signin"})
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");
        signInPage = new signin_page(driver);
    }

    @Test(priority = 1, groups = {"signin"})
    public void testSignInButtonVisibility() {
        try {
            String actualText = signInPage.getSignInButtonText();
            String expectedText = "Sign In";
            Assert.assertEquals(actualText, expectedText, "Sign In button is not visible.");
            System.out.println("Test passed: 'Sign In' button is visible.");
        
            logger.info("SignIn oprion is Visisble TestCase is Passed");
        } catch (Exception e) {
        	 logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
             Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

    @Test(priority = 2, groups = {"signin"})
    public void testSignInButtonClickable() {
        try {
            signInPage.hoverAndClickSignInButton();
            System.out.println("Test passed: 'Sign In' button is clickable.");
            logger.info("SignIn oprion is Clickable TestCase is Passed");
        
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

    @AfterClass(groups = {"signin"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
