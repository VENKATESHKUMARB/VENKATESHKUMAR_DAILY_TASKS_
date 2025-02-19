package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.webdrivermanager;

public class signin_page {

    WebDriver driver;
    webdrivermanager utils;

 
    By signInButton = By.xpath("//span[text()='Sign In']");


    public signin_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }


    public String getSignInButtonText() {
        WebElement signInBtn = utils.waitForElementVisible(signInButton, 20);
        return signInBtn.getText();
    }

  
    public void hoverAndClickSignInButton() {
        WebElement signInBtn = utils.waitForElementVisible(signInButton, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(signInBtn).perform();
        signInBtn = utils.waitForElementClickable(signInButton, 20);
        signInBtn.click();
    }
}
