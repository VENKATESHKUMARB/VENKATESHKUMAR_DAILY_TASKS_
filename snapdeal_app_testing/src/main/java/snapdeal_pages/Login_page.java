package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.webdrivermanager;

public class Login_page {

    WebDriver driver;
    webdrivermanager utils;

   
    By signInButton = By.xpath("//span[text()='Sign In']");
    By loginButton = By.xpath("//a[text()='login']");

    
    public Login_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }

   
    public void hoverOverSignInButton() {
        WebElement signInBtn = utils.waitForElementVisible(signInButton, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(signInBtn).perform();
    }

  
    public String getLoginButtonText() {
        WebElement loginBtn = utils.waitForElementVisible(loginButton, 20);
        return loginBtn.getText();
    }

  
    public void clickLoginButton() {
        WebElement loginBtn = utils.waitForElementClickable(loginButton, 20);
        loginBtn.click();
    }
}
