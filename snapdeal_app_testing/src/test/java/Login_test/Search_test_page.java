package Login_test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import snapdeal_pages.Search_page;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Search_test_page {

    WebDriver driver;
    Search_page searchPage;

    private static final Logger logger = LoggerFactory.getLogger(Search_test_page.class);

    @BeforeClass(groups = "search_productvisibility")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");

        
        searchPage = new Search_page(driver);
    }

    @Test(priority = 1, groups = {"search_productvisibility"})
    public void testSearchAndProductVisibility() {
        try {
           
            searchPage.searchForProduct("earphone");

          
            Assert.assertTrue(searchPage.isProductVisible(), "The search products are not visible on the website.");

            System.out.println("Test passed: Products are visible after search.");
            logger.info("Testcase is Passed");

        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void screenshot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);

            String methodName = result.getMethod().getMethodName();
            File destinyFile = new File("./Screenshots/" + methodName + "_" + System.currentTimeMillis() + ".png");

            FileUtils.copyFile(sourceFile, destinyFile);
        }
    }

    @AfterClass
    public void shutDown() {
        driver.quit();
    }
}
