package products_details;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class products_detail_page {

    WebDriver driver;
    WebDriverWait wait;
    
    private static final Logger logger = LoggerFactory.getLogger(products_detail_page.class);

    @BeforeClass(groups= {"productsdisplay"})
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
    }

    @Test(priority = 1,groups= {"productsdisplay"},description="Verify the Products are Visible")
    public void testProduct_details() {
        try {
            WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputValEnter")));
            searchBar.click();
            Reporter.log("Click the SearchBar ",true);
            searchBar.sendKeys("iron");
            Reporter.log("Enter the Keywords in the SearchBar ",true);
            WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.className("searchTextSpan")));
            searchIcon.click();
            Reporter.log("Click the Search Icon ",true);
            System.out.println("Test passed: Search performed successfully.");
            logger.info("Testcase is Passed");
           
            
            
        } catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	Assert.fail("Test failedn due to unexpected conditions  ");
        }
        
        
        Reporter.log("Verify the Products are Visible",true);
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testProduct_details.png\">click</a>");
        
    }

    @Test(priority = 2,groups= {"productsdisplay"},description="Verify the Products detail page are Visible")
    public void ProductDetailPage() {
        try {
            WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputValEnter")));
            searchBar.click();
            Reporter.log("Click the SearchBar ",true);
            searchBar.sendKeys("iron");
            Reporter.log("Enter the Keywords in the SearchBar ",true);
            WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.className("searchTextSpan")));
            searchIcon.click();
            Reporter.log("Click the Search Icon ",true);
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement product = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[@title='CHARTBUSTERS DISNEY GOLD Black 1000 watt Dry Iron']")));
            product.click();
            String parentWindowHandle = driver.getWindowHandle();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(parentWindowHandle)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            WebElement productDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body[contains(@data-pagename,'ProductDetailPage')]")));
            System.out.println("Test passed: Product detail page is visible.");
            logger.info("Testcase is Passed");
            
            
        } 
        
        
        catch (Exception e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	Assert.fail("Test failed due to unexpected conditions  ");
        }
        
        Reporter.log("Verify the Products Detail Page",true);
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\testProductDetailPage.png\">ProductDetail_click</a>");
        
        
    }

    @AfterMethod
    public void screenshot(ITestResult result) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        String methodName = result.getMethod().getMethodName();
        File destinyFile = new File("./Screenshots/" + methodName + ".png");
        FileUtils.copyFile(sourceFile, destinyFile);
    }

    @AfterClass(groups="productsdisplay")
    public void shutDown() {
      
            driver.quit();
        
    }
}
