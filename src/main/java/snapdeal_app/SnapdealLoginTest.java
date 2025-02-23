package snapdeal_app;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.util.Scanner;
import java.util.Set;

public class SnapdealLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "./snapdealdriver/chromedriver.exe"); // Update this path

        // Initialize the WebDriver
        driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Launch the Snapdeal website
        driver.get("https://www.snapdeal.com");
    }

    @Test
    public void testLoginAndSearch() {
        try {
            // Create an Actions object to perform the hover action
            Actions actions = new Actions(driver);

            // Step 1: Hover over the "Sign In" button to make it clickable
            WebElement signInButton = driver.findElement(By.xpath("//span[text()='Sign In']"));
            actions.moveToElement(signInButton).perform();  // Hover over the button

            // Wait for the "Sign In" button to become clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(signInButton));

            // Click on the "Sign In" button after hover
            signInButton.click();

            // Step 2: Wait and click the "Login" button
            WebElement loginButton = driver.findElement(By.xpath("//a[text()='login']"));
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));  // Explicit wait
            loginButton.click();

            // Step 3: Wait for the iframe and switch to it
            WebElement iframeLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginIframe")));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeLogin));  // Switch to iframe

            // Step 4: Enter the username/email
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
            usernameField.sendKeys("9025095675");

            // Step 5: Click on "Continue" after entering username
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkUser")));
            continueButton.click();
//
            // Step 6: Wait for OTP field and manually enter OTP
            WebElement otpField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginOtpUC\"]/div[1]/input")));
            otpField.sendKeys("123456");  
            
//         // Step 6: Prompt for manual OTP entry
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("Please enter the OTP: ");
//            String otp = scanner.nextLine();
//
//            // Step 7: Enter the OTP
//            WebElement otpField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"loginOtpUC\"]/div[1]/input")));
//            otpField.sendKeys(otp);

            // Step 7: Click on "Continue" after entering OTP
            WebElement continueOtpButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@id='loginUsingOtp'])[1]")));
            continueOtpButton.click();

            // Step 8: Wait for the user profile to be visible
            WebElement userProfile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'venkat')]")));

            // Verify that user profile is displayed to confirm login success
            Assert.assertTrue(userProfile.isDisplayed(), "Login was not successful!");
            
            
//            Set<Cookie> cookies = driver.manage().getCookies();
//            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cookies.dat"))) {
//                out.writeObject(cookies);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            
//            
//            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("cookies.dat"))) {
//                Set<Cookie> cookiesed = (Set<Cookie>) in.readObject();
//                for (Cookie cookie : cookiesed) {
//                    driver.manage().addCookie(cookie);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            
            
            
            
            
            
            



        }
        catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
