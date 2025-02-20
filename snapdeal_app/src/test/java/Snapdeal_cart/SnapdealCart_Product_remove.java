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
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnapdealCart_Product_remove {

	WebDriver driver;
	WebDriverWait wait;
	
	 private static final Logger logger = LoggerFactory.getLogger(SnapdealCart_Product_remove.class);

	@BeforeClass (groups= "product_remove")
	public void setUp() {
		try {
			System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://www.snapdeal.com");
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		} catch (Exception e) {
			System.out.println("Error during setup: " + e.getMessage());
			Assert.fail("Setup failed due to exception: " + e.getMessage());
		}
	}

	@Test (groups= "product_remove")
	public void test_removeCart() throws InterruptedException {
		try {
			WebElement searchBar_cart = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputValEnter")));
			Assert.assertTrue(searchBar_cart.isDisplayed(), "Search bar is not visible.");
			searchBar_cart.click();
			searchBar_cart.sendKeys("shoes");

			WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(By.className("searchTextSpan")));
			searchIcon.click();

			System.out.println("Search for product initiated.");

			Thread.sleep(5000);

			WebElement product_clicks = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//p[@title=\"HASTEN Dark Grey Men's Lifestyle Shoes\"]")));
			product_clicks.click();
			System.out.println("Product clicked: HASTEN Dark Grey Men's Lifestyle Shoes ");

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

			WebElement addToCartButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("add-cart-button-id")));
			addToCartButton.click();
			System.out.println("Product added to cart.");

			Thread.sleep(5000);

			WebElement viewcart = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("(//div[@class='btn btn-theme-secondary open-cart'])[1]")));
			viewcart.click();

			Thread.sleep(5000);

			// click the "Remove Cart"

			WebElement remove_cart = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//span[contains(@class,'remove-item-shortlist')]")));
			Assert.assertTrue(remove_cart.isDisplayed(), "The 'Remove' button is not visible for the item.");
			remove_cart.click();

			System.out.println("Product removed from cart.");

			WebElement emptyCartMessage = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Shopping Cart is empty!']")));
			Assert.assertTrue(emptyCartMessage.isDisplayed(), "The cart is not empty after removing the item.");
			String actualCartItem = emptyCartMessage.getText();
			String expectedCartItem = "Shopping Cart is empty!";
			Assert.assertEquals(actualCartItem, expectedCartItem, "Your Cart is Not empty");
			System.out.println("Cart is empty after product removal.");
			
			 logger.info("Testcase is Passed");

			Thread.sleep(5000);

		}

		catch (NoSuchElementException e) {
			logger.error("Test failed due to exception: " + e.getClass().getSimpleName());
			Assert.fail("Test failedn due to unexpected conditions  ");
		}
		
		
		Reporter.log("Verify the  Remove Products form the Cart");
        
        Reporter.log("<a href= \"C:\\Users\\venkatesh.baskaran\\eclipse-workspace\\snapdeal_app\\Screenshots\\test_removeCart.png\">click</a>");
		
		
	}
	
	
//  ScreenShots
    
  @AfterMethod
  public void screenshot(ITestResult result) throws IOException {
     
      TakesScreenshot ts = (TakesScreenshot) driver;
      File sourceFile = ts.getScreenshotAs(OutputType.FILE);

    
      String methodName = result.getMethod().getMethodName();
      File destinyFile = new File("./Screenshots/" + methodName + ".png");

   
      FileUtils.copyFile(sourceFile, destinyFile);
      
  }
	
	
	
	
	

	@AfterClass(groups="product_remove")
	public void shutDown() {
		try {
			
				driver.quit();
				System.out.println("Browser closed.");
			
		}

		catch (NoSuchElementException e) {
			System.out.println("Error during teardown: " + e.getMessage());
			Assert.fail("Error during teardown: " + e.getMessage());
		}
	}
}
