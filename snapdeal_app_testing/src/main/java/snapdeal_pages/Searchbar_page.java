package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.webdrivermanager;

public class Searchbar_page {

    WebDriver driver;
    webdrivermanager utils;

    
    By searchBox = By.id("inputValEnter");

    public Searchbar_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }

   
    public boolean isSearchBarVisible() {
        WebElement searchElement = utils.waitForElementVisible(searchBox, 20);
        return searchElement.isDisplayed();
    }

    
    public void performSearch(String productName) {
        WebElement searchElement = utils.waitForElementVisible(searchBox, 20);
        searchElement.click();
        searchElement.sendKeys(productName);
        searchElement.sendKeys(Keys.RETURN);
    }
}
