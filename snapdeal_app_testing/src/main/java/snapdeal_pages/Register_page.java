package snapdeal_pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Register_page {

    WebDriver driver;
    WebDriverWait wait; 

    
    By signInButton = By.xpath("//span[text()='Sign In']");
    By registerButton = By.xpath("(//span[@class='newUserRegister'])[1]");
    By mobileNumberField = By.xpath("//input[contains(@id,'userName')]");
    By continueButton = By.xpath("//button[contains(@id,'checkUser')]");
    By emailField = By.xpath("//input[contains(@id,'j_username_new')]");
    By nameField = By.xpath("(//input[@id='j_name'])[1]");
    By passwordField = By.xpath("(//input[@id='j_password'])[1]");
    By continueSignupButton = By.xpath("(//button[@id='userSignup'])[1]");

    public Register_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));  
    }

    
    public void hoverOverSignInButton() {
    	
        WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));
        Actions actions = new Actions(driver);
        actions.moveToElement(signInBtn).perform();
    }


    public void clickRegisterButton() {
        WebElement register = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        register.click();
    }

    
    public void enterMobileNumber(String mobileNumber) {
        WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumberField));
        mobileField.sendKeys(mobileNumber);
    }


    public void clickContinueButton() {
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();
    }


    public void clearEmailField() {
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        email.clear();
    }

    
    public void enterName(String name) {
        WebElement nameFieldElem = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        nameFieldElem.sendKeys(name);
    }


    public void enterPassword(String password) {
        WebElement passwordFieldElem = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordFieldElem.sendKeys(password);
    }

   
    public void clickContinueSignupButton() {
        WebElement continueSignupBtn = wait.until(ExpectedConditions.elementToBeClickable(continueSignupButton));
        continueSignupBtn.click();
    }
}
