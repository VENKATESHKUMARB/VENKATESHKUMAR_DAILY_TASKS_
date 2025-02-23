package snapdeal_app;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class productdetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set the path to the geckodriver executable
        System.setProperty("webdriver.gecko.driver", "./snapdealdriver/geckodriver.exe");

        // Initialize the FirefoxDriver
        driver = new FirefoxDriver();
        
        // Initialize the WebDriverWait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    @Test
    public void searchFastrackWatch() {
        // Step 1: Open the Snapdeal website
        driver.get("https://www.snapdeal.com");

        // Step 2: Click the search box
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("keyword")));
        searchBox.click();

        // Step 3: Search for the fastrack watch
        searchBox.sendKeys("fastrack");
        searchBox.sendKeys(Keys.RETURN); // Press Enter key

        // Step 4: Filter the watch under 500
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Under ₹ 300']")));
        WebElement filterUnder300 = driver.findElement(By.xpath("//input[@value='Under ₹ 300']"));
        filterUnder300.click();

        // Step 5: Wait for the product to be visible and click the desired product
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@title='Lorenz Silver Stainless Steel Analog Men\'s Watch'])[1]")));
        WebElement product = driver.findElement(By.xpath("(//p[@title='Lorenz Silver Stainless Steel Analog Men\'s Watch'])[1]"));
        product.click();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
