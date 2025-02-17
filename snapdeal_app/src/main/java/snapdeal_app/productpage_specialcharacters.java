package snapdeal_app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class productpage_specialcharacters {
    WebDriver driver;
    String baseUrl = "https://www.snapdeal.com/";

    @BeforeClass
    public void setUp() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testSpecialCharacterSearch() {
        // Open the Snapdeal app
        driver.get(baseUrl);

        // Tap on the search bar
        WebElement searchBar = driver.findElement(By.id("inputValEnter"));
        searchBar.click();

        // Enter special characters (e.g., "@#$%")
        searchBar.sendKeys("@#$%");

        // Tap the search icon
        WebElement searchIcon = driver.findElement(By.className("searchTextSpan"));
        searchIcon.click();

        // Verify the search results
        WebElement searchResult = driver.findElement(By.className("nnn"));
        Assert.assertTrue(searchResult.isDisplayed(), "Search results are not displayed.");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}