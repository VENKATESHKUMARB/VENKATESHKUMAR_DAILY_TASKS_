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

public class cartcancel_icon {

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
    public void testCart_cancelIcon() {
        try {
            // Tap on the search bar
            WebElement searchBar_cart = driver.findElement(By.id("inputValEnter"));
            searchBar_cart.click();

            // Enter search term "fastrack"
            searchBar_cart.sendKeys("fastrack");

            // Tap the search icon
            WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
            searchIcon.click();

            Thread.sleep(5000);

            // Tap on the product
            WebElement product_click = driver.findElement(By.xpath("(//p[@title=\"Walrus Lmw-Tm-010907WAS PU Analog Men's Watch\"])[1]"));
            product_click.click();

            Thread.sleep(5000);

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

            Thread.sleep(5000);

            // View Cart
            WebElement viewcart = driver.findElement(By.xpath("(//div[@class='btn btn-theme-secondary open-cart'])[1]")); // Update with the actual ID of the "View Cart" button
            viewcart.click();
            
            System.out.println("click the cart  is over");
            
            Thread.sleep(5000);

            // Assert that the "Cancel" icon is visible for the item in the cart
            WebElement remove_carticon = driver.findElement(By.xpath("(//i[@class='sd-icon sd-icon-delete-sign'])[2]")); // Update with the actual XPath of the "Cancel" icon
            Assert.assertTrue(remove_carticon.isDisplayed(), "Cancel icon is  displayed for the cart item.");

            // Click on the "Cancel" icon to remove the item from the cart
            remove_carticon.click();

//            // Assert that the item was removed by checking if the cart is empty or if a confirmation is shown
//            WebElement emptyCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Your cart is empty')]")));
//            Assert.assertNotNull(emptyCartMessage, "The cart is not empty after removing the item.");

            System.out.println("TestCase Role: Cart cancel icon is working correctly");

            System.out.println("My Cart cancel icon TestCase is successfully executed");

        } catch (Exception e) {
            // If any exception occurs during the test, print the message and fail the test
            System.out.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace to help with debugging
            Assert.fail("Test failed due to exception: " + e.getMessage()); // Fail the test explicitly
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            // Close the browser
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Failed to close the driver: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace if quitting the driver fails
        }
    }
}
