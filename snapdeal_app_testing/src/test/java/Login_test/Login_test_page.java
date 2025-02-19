package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Login_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Login_test_page {

    WebDriver driver;
    Login_page loginPage;
    
    private static final Logger logger = LoggerFactory.getLogger(Login_test_page.class);

    @BeforeClass(groups = {"login"})
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");
        loginPage = new Login_page(driver);
    }

    @Test(priority = 1, groups = {"login"})
    public void testLoginButtonVisibility() {
        try {
            loginPage.hoverOverSignInButton();
            String actualText = loginPage.getLoginButtonText();
            String expectedText = "LOGIN";
            Assert.assertEquals(actualText, expectedText, "Login button is not visible.");
            System.out.println("Test passed: 'Login' button is visible.");
        
            logger.info("'Login Button' is visible TestCase is Passed ");
        
        
        
        } catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }

    @Test(priority = 2, groups = {"login"})
    public void testLoginButtonClickable() {
        try {
            loginPage.hoverOverSignInButton();
            loginPage.clickLoginButton();
            System.out.println("Test passed: 'Login' button is clickable.");
        
            logger.info("'Login Button' is Clickable TestCase is Passed ");
        
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

    @AfterClass(groups = {"login"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
