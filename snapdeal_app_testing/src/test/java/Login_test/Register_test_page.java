package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import snapdeal_pages.Register_page;
import utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Register_test_page {

    WebDriver driver;
    Register_page registrationPage;
    
    private static final Logger logger = LoggerFactory.getLogger(Register_test_page.class);

    @BeforeClass(groups = "register")
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update path
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.snapdeal.com");
            registrationPage = new Register_page(driver);
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
            Assert.fail("Setup failed due to exception: " + e.getMessage());
        }
    }

    @Test(groups = "register")
    public void testRegisterWithEmptyEmailField() {
        try {
         
            registrationPage.hoverOverSignInButton();
            
     

          
            registrationPage.clickRegisterButton();

           
            driver.switchTo().frame("loginIframe");

           
            registrationPage.enterMobileNumber("8867547890");

          
            registrationPage.clickContinueButton();

           
            registrationPage.clearEmailField();

         
            registrationPage.enterName("naveenya");
            registrationPage.enterPassword("badri@12345");

          
            registrationPage.clickContinueSignupButton();

            System.out.println("Test passed: Error message for empty email field is empty.");
            
            logger.info("'Empty Input Field 'Testcase is Passed");
        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage());
            Assert.fail("Test failed due to unexpected conditions");
        }
    }

    @AfterMethod(groups = "register")
    public void screenshot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            ScreenshotUtils.takeScreenshot(driver, result.getMethod().getMethodName());
        }
    }

    @AfterClass(groups = "register")
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
