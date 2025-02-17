package Snapdeal_cart;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnapdealCart_page {

    WebDriver driver;
    WebDriverWait wait;
    
    private static final Logger logger = LoggerFactory.getLogger(SnapdealCart_page.class);

    @BeforeClass (groups= {"cartpage"})
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); 

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://www.snapdeal.com");

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));  
    }

    @Test(groups= {"cartpage"})
    public void testcart_Item() throws InterruptedException {
        try {

            WebElement searchBar_cart = driver.findElement(By.id("inputValEnter"));
            searchBar_cart.click();

            searchBar_cart.sendKeys("fastrack");

            WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
            searchIcon.click();

            Thread.sleep(5000);
            
            
            WebElement product_click = driver.findElement(By.xpath("//p[@title=\"Walrus Lmw-Tm-010907WAS PU Analog Men's Watch\"]"));
            product_click.click();

            Thread.sleep(5000);

            String parentWindowHandle = driver.getWindowHandle();

            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(parentWindowHandle)) {
                    driver.switchTo().window(windowHandle); 
                    break;
                }
            }

            WebElement addToCartButton = driver.findElement(By.id("add-cart-button-id")); 
            addToCartButton.click();

            Thread.sleep(3000);

            WebElement viewcart = driver.findElement(By.xpath("(//div[@class='btn btn-theme-secondary open-cart'])[1]"));
            viewcart.click();

            Thread.sleep(3000);

            WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'item-name')]"))); 
            String actualCartItem = cartItem.getText(); 
            String expectedCartItem = "Walrus Lmw-Tm-010907WAS PU Analog Men's Watch";
            Assert.assertEquals(actualCartItem, expectedCartItem, "The item in the cart is incorrect.");

            System.out.println("My cartItem TestCase is Successfully Executed");
            
            logger.info("Testcase is Passed");

        } catch (NoSuchElementException e) {
        	logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
        	Assert.fail("Test failedn due to unexpected conditions  ");
        }
    }
    
    
    @AfterMethod
    
    public void screenshot() throws IOException {
    	
    	TakesScreenshot ts= (TakesScreenshot) driver;
    	File sourcefile= ts.getScreenshotAs(OutputType.FILE);
    	File destinyFile=new File("./Screenshots./Output1");
    	FileUtils.copyFile(sourcefile, destinyFile);
    	
    	
    }

    @AfterClass
    public void tearDown() {
        try {
           
                driver.quit();
            
        } catch (Exception e) {
            System.out.println("Failed to close the driver: " + e.getMessage());
        }
    }
}
