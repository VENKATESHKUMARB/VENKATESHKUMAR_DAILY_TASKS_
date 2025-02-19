package snapdeal_pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.webdrivermanager;

public class Cart_page {

    WebDriver driver;
    webdrivermanager utils;

  
    By searchBox = By.id("inputValEnter");
    By searchIcon = By.className("searchTextSpan");
    By productLink = By.xpath("//img[@title=\"Walrus Lmw-Tm-010907WAS PU Analog Men's Watch\"]");
    By addToCartButton = By.id("add-cart-button-id");
    By viewCartButton = By.xpath("(//div[@class='btn btn-theme-secondary open-cart'])[1]");
    By cartItem = By.xpath("//a[@title=\"Walrus Lmw-Tm-010907WAS PU Analog Men's Watch\"]");

    public Cart_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }


    public void addProductToCart(String productName) {
        WebElement searchBar = utils.waitForElementVisible(searchBox, 20);
        searchBar.click();
        searchBar.sendKeys(productName);
        WebElement searchIconElement = utils.waitForElementClickable(searchIcon, 20);
        searchIconElement.click();

        WebElement product = utils.waitForElementClickable(productLink, 20);
        product.click();
        String parentWindowHandle = driver.getWindowHandle();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Switching to the new window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Adding product to cart
        WebElement addToCart = utils.waitForElementClickable(addToCartButton, 20);
        addToCart.click();
    }

    
    public boolean verifyProductInCart(String productName) {
        WebElement viewCart = utils.waitForElementClickable(viewCartButton, 20);
        viewCart.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(cartItem, productName));

        WebElement cartProduct = driver.findElement(cartItem);
        String cartProductName = cartProduct.getText();
        return cartProductName.equals(productName);
    }
}
