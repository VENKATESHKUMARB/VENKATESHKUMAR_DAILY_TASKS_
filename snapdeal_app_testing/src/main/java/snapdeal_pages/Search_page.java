package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.webdrivermanager;

public class Search_page {

    WebDriver driver;
    webdrivermanager utils;

   
    By searchBox = By.id("inputValEnter");
    By searchProduct = By.xpath("//div[contains(@class,'comp-right-wrapper')]");

    public Search_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }

   
    public void searchForProduct(String productName) {
        WebElement searchInput = utils.waitForElementVisible(searchBox, 20);
        searchInput.click();
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.RETURN);
    }

   
    public boolean isProductVisible() {
        WebElement productResults = utils.waitForElementVisible(searchProduct, 20);
        return productResults.isDisplayed();
    }
}
