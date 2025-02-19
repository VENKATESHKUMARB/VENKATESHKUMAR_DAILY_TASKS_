package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class webdrivermanager {
    WebDriver driver;

    public webdrivermanager(WebDriver driver) {
        this.driver = driver;
    }

   
    public void waitForWindowToBe(int numberOfWindows) {
        long timeout = System.currentTimeMillis() + 5000; 
        Set<String> windowHandles;

        while (System.currentTimeMillis() < timeout) {
            windowHandles = driver.getWindowHandles();
            if (windowHandles.size() == numberOfWindows) {
                break;
            }
        }
    }

   
    public WebElement waitForElementClickable(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

   
    public WebElement waitForElementVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
