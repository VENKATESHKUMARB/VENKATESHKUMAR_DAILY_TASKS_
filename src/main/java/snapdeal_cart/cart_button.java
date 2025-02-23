package snapdeal_cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class cart_button {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set the path to your chromedriver executable
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.snapdeal.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testCartFunctionality() {
        try {
            // Click on the cart button
            WebElement cartButton = driver.findElement(By.xpath("(//span[@class='cartTextSpan'])[1]"));
            cartButton.click();
            
            // Assert that the cart is empty
            WebElement emptyCartMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Your cart is empty')]")));
            Assert.assertNotNull(emptyCartMessage, "Cart is not empty");

            System.out.println("TestCase Role: Cart is empty");
            
            Thread.sleep(3000);

            // Click on the "Start Shopping Now" button with explicit wait
            WebElement startShoppingNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[normalize-space()='START SHOPPING NOW'])[1]")));
            startShoppingNowButton.click();

            // Assert that we are redirected to the homepage or another page
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("snapdeal.com"), "Redirection to homepage failed");

            System.out.println("My CartButton is Working, TestCase executed successfully");
            
        } catch (Exception e) {
            // Catch any exceptions that occur during the test execution
            System.out.println("Test failed due to exception: " + e.getMessage());
            e.printStackTrace();  // Prints the stack trace of the exception
            Assert.fail("Test failed due to exception: " + e.getMessage());  // Fails the test if exception occurs
        }
    }

//    @AfterClass
//    public void tearDown() {
//        try {
//            if (driver != null) {
//                driver.quit();
//            }
//        } catch (Exception e) {
//            System.out.println("Failed to close the driver: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
}
