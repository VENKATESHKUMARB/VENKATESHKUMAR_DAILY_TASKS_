package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Valid_invalidproducts_page {

    WebDriver driver;
    WebDriverWait wait;


    By searchBar = By.id("inputValEnter");
    By searchIcon = By.className("searchTextSpan");
    By firstProduct = By.xpath("(//div[contains(@class, 'product-tuple-image')])[1]");
    By noResultsMessage = By.xpath("//span[contains(@class,'alert-heading')]");

    public Valid_invalidproducts_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

 
    public void clickSearchBar() {
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchBar));
        search.click();
    }

    
    public void enterProductName(String productName) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
        search.sendKeys(productName);
    }

   
    public void clickSearchIcon() {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
        searchBtn.click();
    }


    public boolean isFirstProductDisplayed() {
        try {
            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
            return product.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

   
    public String getNoResultsMessage() {
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(noResultsMessage));
        return message.getText();
    }
}
