package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Check_logo {

    WebDriver driver;
    WebDriverWait wait;

   
    By logoLocator = By.xpath("//img[contains(@class,'notIeLogoHeader')]");

    public Check_logo(WebDriver driver) {
        this.driver = driver;
       
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    
    public boolean isLogoVisible() {
        WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(logoLocator));
        return logo.isDisplayed();
    }
}
