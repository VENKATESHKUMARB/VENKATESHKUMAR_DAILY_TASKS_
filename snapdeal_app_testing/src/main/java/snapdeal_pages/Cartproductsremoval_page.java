package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.webdrivermanager;

public class Cartproductsremoval_page{

    WebDriver driver;
    webdrivermanager utils;

   
    By searchBox = By.id("inputValEnter");
    By searchIcon = By.className("searchTextSpan");
    By productLink = By.xpath("//p[@title=\"HASTEN Dark Grey Men's Lifestyle Shoes\"]");
    By addToCartButton = By.id("add-cart-button-id");
    By viewCartButton = By.xpath("(//div[@class='btn btn-theme-secondary open-cart'])[1]");
    By removeCartButton = By.xpath("//span[contains(@class,'remove-item-shortlist')]");
    By emptyCartMessage = By.xpath("//h3[text()='Shopping Cart is empty!']");

    public Cartproductsremoval_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }

  
    public void searchForProduct(String productName) {
        WebElement searchInput = utils.waitForElementVisible(searchBox, 20);
        searchInput.click();
        searchInput.sendKeys(productName);
        utils.waitForElementClickable(searchIcon, 20).click();
    }

   
    public void clickProduct() {
        WebElement product = utils.waitForElementVisible(productLink, 20);
        product.click();
    }

  
    public void addToCart() {
        WebElement addToCart = utils.waitForElementClickable(addToCartButton, 20);
        addToCart.click();
    }

  
    public void viewCart() {
        WebElement viewCart = utils.waitForElementClickable(viewCartButton, 20);
        viewCart.click();
    }

    
    public void removeProductFromCart() {
        WebElement removeButton = utils.waitForElementVisible(removeCartButton, 20);
        removeButton.click();
    }

    
    public boolean isCartEmpty() {
        WebElement emptyMessage = utils.waitForElementVisible(emptyCartMessage, 20);
        return emptyMessage.isDisplayed();
    }
}
