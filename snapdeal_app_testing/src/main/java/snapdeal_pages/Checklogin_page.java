package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Checklogin_page {

    WebDriver driver;
    WebDriverWait wait;

   
    By signInButton = By.xpath("//span[text()='Sign In']");
    By loginButton = By.xpath("//a[text()='login']");
    By iframeLogin = By.id("loginIframe");
    By usernameField = By.id("userName");
    By continueButton = By.id("checkUser");
    By otpField = By.xpath("//*[@id=\"loginOtpUC\"]/div[1]/input");
    By continueOtpButton = By.xpath("(//button[@id='loginUsingOtp'])[1]");
    By incorrectOtpError = By.xpath("//div[contains(@class,'authError ')]");

    public Checklogin_page(WebDriver driver) {
        this.driver = driver;
       
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void hoverAndClickLogin() {
        WebElement signInElement = driver.findElement(signInButton);
        Actions actions = new Actions(driver);
        actions.moveToElement(signInElement).perform();

        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginElement.click();
    }

    
    public void enterUsername(String username) {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLogin));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
        
        WebElement usernameFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameFieldElement.sendKeys(username);
    }

   
    public void clickContinue() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();
    }

    
    public void enterOtpAndContinue(String otp) {
        WebElement otpFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(otpField));
        otpFieldElement.sendKeys(otp);

        WebElement continueOtpBtn = wait.until(ExpectedConditions.elementToBeClickable(continueOtpButton));
        continueOtpBtn.click();
    }

    // Get the error message if OTP is incorrect
    public String getIncorrectOtpError() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectOtpError));
        return errorElement.getText();
    }
}
