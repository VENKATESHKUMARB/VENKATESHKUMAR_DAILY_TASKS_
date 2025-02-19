package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.webdrivermanager;


public class Catagories_page {

    WebDriver driver;
    webdrivermanager utils;


    By categoryBanner = By.xpath("//div[contains(@class,'topCats')]");
    By categoriesAccess = By.xpath("//span[contains(@class,'catText')]");
    By backpacksAccess = By.xpath("//span[text()='Bags & Luggage']");
    By backpacksLink = By.xpath("//a[@class='rightMenuLink noHasTagWidth dp-widget-link visibleRgtBlkLnk']//span[@class='linkTest'][normalize-space()='Backpacks']");

    public Catagories_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }

   
    public boolean isCategoryBannerVisible() {
        WebElement categoryElement = utils.waitForElementVisible(categoryBanner, 20);
        String actualText = categoryElement.getText();
        String expectedText = "TOP CATEGORIES";
        return actualText.equals(expectedText);
    }

   
    public void hoverOverCategories() {
        WebElement categoriesElement = utils.waitForElementVisible(categoriesAccess, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(categoriesElement).perform();
    }

   
    public void hoverAndClickBackpacks() {
        WebElement backpacksElement = utils.waitForElementVisible(backpacksAccess, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(backpacksElement).perform();

        WebElement backpacksLinkElement = utils.waitForElementClickable(backpacksLink, 20);
        backpacksLinkElement.click();
    }
}
