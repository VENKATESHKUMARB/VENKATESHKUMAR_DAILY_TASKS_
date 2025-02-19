package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Checklogin_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class Checklogin_test_page {

    WebDriver driver;
    Checklogin_page loginPage;
    
    private static final Logger logger = LoggerFactory.getLogger(Checklogin_test_page.class);

    @BeforeClass(groups = "functional_login")
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update path
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.snapdeal.com");
            loginPage = new Checklogin_page(driver);
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
            Assert.fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    @Test(groups = "functional_login")
    public void testLogin_Check() {
        try {
      
            loginPage.hoverAndClickLogin();

           
            loginPage.enterUsername("9025095675");
            loginPage.clickContinue();

          
            loginPage.enterOtpAndContinue("123456");

       
            String actualError = loginPage.getIncorrectOtpError();
            String expectedError = "Incorrect verification code";
            Assert.assertEquals(actualError, expectedError, "Login failed due to incorrect OTP");

            System.out.println("Test passed: Incorrect OTP error message is displayed.");
            
            logger.info("Check Login Testcase is Passed(if OTP is Correct Successfully Login)");
            
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

    @AfterClass(groups = "functional_login")
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
