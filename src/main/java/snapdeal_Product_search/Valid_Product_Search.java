package snapdeal_Product_search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Valid_Product_Search {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Use WebDriverManager to manage ChromeDriver binary
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void searchValidProduct() {
        try {
            // Open the Snapdeal website
            driver.get("https://www.snapdeal.com/");

            // Tap on the search bar
            WebElement searchBar = driver.findElement(By.id("inputValEnter"));
            searchBar.click();

            // Enter a valid product name (for example, 'shoes')
            searchBar.sendKeys("shoes");

            // Tap the search icon
            WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
            searchIcon.click();

            // Wait for the search results to load and verify the result
            WebElement firstProduct = driver.findElement(By.xpath("(//div[contains(@class, 'product-tuple-image')])[1]"));

            // Assert that at least one product is displayed in the search results
            Assert.assertTrue(firstProduct.isDisplayed(), "No products found for the search query.");

            System.out.println("Test Passed: Valid product search results are displayed.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
   
  

    @Test(priority = 2)
    public void searchInvalidProduct() {
        try {
            // Open the Snapdeal website
            driver.get("https://www.snapdeal.com/");

            // Tap on the search bar
            WebElement searchBar = driver.findElement(By.id("inputValEnter"));
            searchBar.click();

            // Enter an invalid product name (for example, 'nonexistentproduct')
            searchBar.sendKeys("xhgr");

            // Tap the search icon
            WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
            searchIcon.click();

            // Add assertion to verify that the "No Results" message is displayed
            WebElement noResultsMessage = driver.findElement(By.xpath("//span[contains(@class,'alert-heading')]"));
            Assert.assertTrue(noResultsMessage.isDisplayed(), "No results message is not displayed for the invalid product search.");

            System.out.println("Test Passed: No products found for invalid product search.");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
