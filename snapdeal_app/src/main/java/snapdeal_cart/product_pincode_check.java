package snapdeal_cart;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class product_pincode_check {

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

            Thread.sleep(5000);

            // Tap on the product
            WebElement product_click = driver.findElement(By.xpath("(//p[@title=\"Walrus Lmw-Tm-010907WAS PU Analog Men's Watch\"])[1]"));
            product_click.click();

            Thread.sleep(5000);
            
            System.out.print("start");



            Thread.sleep(5000);
            
            
            WebElement pinField = driver.findElement(By.xpath("//input[contains(@id,'pincode-check')]"));
            pinField.sendKeys("638183");

            // Now check the button for pincode verification
            WebElement check_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'pincode-check-bttn')]")));
            check_button.click();
            
            System.out.print("end");

            Thread.sleep(5000);

            // Add further actions or assertions if needed.

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
}
