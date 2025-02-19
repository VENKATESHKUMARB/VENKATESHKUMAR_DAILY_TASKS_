package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.webdrivermanager;  
import java.util.Set;

public class Products_page {

    WebDriver driver;
    webdrivermanager utils;

    // Locators
    By searchBar = By.id("inputValEnter");
    By searchIcon = By.className("searchTextSpan");
    By productLink = By.xpath("//p[@title='CHARTBUSTERS DISNEY GOLD Black 1000 watt Dry Iron']");
    By productDescription = By.xpath("//body[contains(@data-pagename,'ProductDetailPage')]");

   
    public Products_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }

    
    public void searchForProduct(String productName) {
        WebElement searchInput = utils.waitForElementVisible(searchBar, 20);
        searchInput.click();
        searchInput.sendKeys(productName);
        WebElement searchButton = utils.waitForElementClickable(searchIcon, 20);
        searchButton.click();
    }


    public void clickOnProduct() {
        WebElement product = utils.waitForElementClickable(productLink, 20);
        product.click();
    }


    public void switchToProductDetailPage() {
        String parentWindowHandle = driver.getWindowHandle();
        
        // Wait for the new window to open
        utils.waitForWindowToBe(2); 
        
        Set<String> windowHandles = driver.getWindowHandles();
        
        // Switch to the new window
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

   
    public boolean isProductDescriptionVisible() {
        WebElement productDesc = utils.waitForElementVisible(productDescription, 20);
        return productDesc.isDisplayed();
    }
}