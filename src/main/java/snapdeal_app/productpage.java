package snapdeal_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class productpage {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Use WebDriverManager to manage ChromeDriver binary
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void searchanvalidProduct() {
        // Open the Snapdeal app (website)
        driver.get("https://www.snapdeal.com/");

        // Tap on the search bar
        WebElement searchBar = driver.findElement(By.id("inputValEnter"));
        searchBar.click();

        // Enter an invalid product name
        searchBar.sendKeys("shoes");

        // Tap the search icon
        WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
        searchIcon.click();

        // Add assertions if needed to verify the result
        // Example: Verify that no products found message is displayed
        WebElement noResultsMessage = driver.findElement(By.className("nnn"));
        assert noResultsMessage.isDisplayed();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}