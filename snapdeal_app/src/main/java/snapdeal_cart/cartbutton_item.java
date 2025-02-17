package snapdeal_cart;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class cartbutton_item {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path

        // Initialize the WebDriver
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Launch the Snapdeal website
        driver.get("https://www.snapdeal.com");

        // Initialize the WebDriverWait with a longer timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Increase timeout to 10 seconds
    }

    @Test
    public void testLogin() {
        try {
         
            // Tap on the search bar
            WebElement searchBar_cart = driver.findElement(By.id("inputValEnter"));
            searchBar_cart.click();

            // Enter search term "fastrack"
            searchBar_cart.sendKeys("fastrack");

            // Tap the search icon
            WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
            searchIcon.click();

            // Wait for product to appear and click on it
            WebElement product_click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//p[@title=\"Walrus Lmw-Tm-010907WAS PU Analog Men's Watch\"])[1]")));
            product_click.click();

            // Store the current window handle (parent window)
            String parentWindowHandle = driver.getWindowHandle();

            // Wait for the new window to open (product page)
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Switch to the new window (child window)
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(parentWindowHandle)) {
                    driver.switchTo().window(windowHandle); // Switch to the product page window
                    break;
                }
            }

            // Find and click the "Add to Cart" button on the product page
            WebElement addToCartButton = driver.findElement(By.id("add-cart-button-id")); // Update with the actual ID of the "Add to Cart" button
            addToCartButton.click();
          

            // Wait for the cart to update and view the cart
            WebElement viewcart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='btn btn-theme-secondary open-cart'])[1]")));
            viewcart.click();
            
            Thread.sleep(3000);
            
            System.out.println("completed");


        } 
        
        
        catch (Exception e) {
        	
            
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser after the test
        if (driver != null) {
            driver.quit();
        }
    }
}
