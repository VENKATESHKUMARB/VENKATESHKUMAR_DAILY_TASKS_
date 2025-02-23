package my_orders;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class confirm_order {

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
    public void LoginAndSearch() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Set<Cookie> cookies = driver.manage().getCookies();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cookies.dat"))) {
                out.writeObject(cookies);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("cookies.dat"))) {
                Set<Cookie> cookiesed = (Set<Cookie>) in.readObject();
                for (Cookie cookie : cookiesed) {
                    driver.manage().addCookie(cookie);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Step 9: Click on the search box after login
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputValEnter")));
            searchBox.click();

            // Step 10: Enter "FastTrack" into the search box
            searchBox.sendKeys("mobile cover moto");

            // Step 11: Click the search button
            WebElement searchButton = driver.findElement(By.xpath("//button[@class='searchformButton col-xs-4 rippleGrey']"));
            searchButton.click();

            // Select the Product
            Thread.sleep(5000);

            WebElement product_select = driver.findElement(By.xpath("(//p[@title='BEING STYLISH Bumper Cases Compatible For Silicon Moto g85 5G ( Pack of 1 )'])[1]"));
            product_select.click();

            Thread.sleep(5000);

            // Store the current window handle (parent window)
            String parentWindowHandle = driver.getWindowHandle();

            // Wait for the new window to open (product page)
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            // Switch to the new window (child window)
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                if (!windowHandle.equals(parentWindowHandle)) {
                    driver.switchTo().window(windowHandle); // Switch to the product page window
                    break;
                }
            }

            // Find and click the "Add to Cart" button on the product page
            WebElement addToCartButton = driver.findElement(By.id("add-cart-button-id")); // Update with the actual ID of the "Add to Cart" button
            addToCartButton.click();

            Thread.sleep(5000);

            // View cart
            WebElement viewcart = driver.findElement(By.xpath("(//div[@class='btn btn-theme-secondary open-cart'])[1]")); // Update with the actual ID of the "Add to Cart" button
            viewcart.click();

            Thread.sleep(5000);

            WebElement proceedtopay = driver.findElement(By.xpath("(//input[@id='rzp-cart-button'])[1]")); // Update with the actual ID of the "Add to Cart" button
            proceedtopay.click();

            Thread.sleep(5000);

            driver.switchTo().frame(driver.findElement(By.xpath("//div[contains(@class,'razorpay-container')]//iframe")));

            Thread.sleep(3000);

            // Wait and enter phone number at checkout
            WebElement checkoutPhoneNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'phone-field-one-click-checkout')]")));
            checkoutPhoneNumber.sendKeys("9025095675");

            Thread.sleep(5000);

            // Click on the continue button
            WebElement checkoutContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'redesign-v15-cta')]")));
            checkoutContinueButton.click();

            Thread.sleep(5000);

            // Step 6: Prompt for manual OTP entry
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter the checkout OTP: ");
            String checkoutpage_otp = scanner.nextLine();

            // Step 7: Enter the OTP
            WebElement checkout_otpfiled = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'input svelte-ohv3ey')]")));
            checkout_otpfiled.sendKeys(checkoutpage_otp);
            
            
//            Thread.sleep(2000);
//            WebElement checkoutotpContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'redesign-v15-cta')]")));
//            checkoutotpContinueButton.click();

           
            
            
//            Thread.sleep(5000);
            
            // Wait for the loader to disappear
//            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class,'loader-backdrop']")));
            
            System.out.println("ask the Delivery address");
           
            Thread.sleep(20000);
//            
            WebElement checkout_address = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'address-add')]")));
            checkout_address.click();
//            
            System.out.println("Step 1 is Completed");

            System.out.println("Step 2 is Started");

            // Delivery address Page Started Here

            // Fullname
            
            WebElement checkout_name=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'error-field-one-click-checkout')]")));
            checkout_name.clear();
            
            
            WebElement checkout_fullname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@class,'error-field-one-click-checkout')]")));
            checkout_fullname.sendKeys("Naveenya");
            
            Thread.sleep(5000);

            // Phone number
            WebElement checkoutclear_phno = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@pattern,'^\\d{10}$')]")));
            checkoutclear_phno.clear();

            Thread.sleep(5000);
            
            // Step 6: Prompt for manual checkout_phoneno entry
            Scanner phno = new Scanner(System.in);
            System.out.print("Please enter the checkout phone number: ");
            String checkoutpage_phno = scanner.nextLine();

            // Step 7: Enter the phone number
            WebElement checkout_phno = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@pattern,'^\\d{10}$')]")));
            checkout_phno.sendKeys(checkoutpage_phno);
            
            Thread.sleep(5000);

            // Email
            WebElement checkout_email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'email')]")));
            checkout_email.sendKeys("Naveenya@gmail.com");
            	
            Thread.sleep(5000);
            
            
            // Pincode
            Scanner zipcode = new Scanner(System.in);
            System.out.println("Enter the User Zipcode or Pincode");
            String checkout_zipcode = scanner.nextLine();

            WebElement checkoutpage_zipcode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name,'zipcode')]")));
            checkoutpage_zipcode.sendKeys(checkout_zipcode);
            
            
            Thread.sleep(5000);

            // Apartment name
            Scanner checkout_home = new Scanner(System.in);
            System.out.println("Enter the User Apartment Name");
            String checkout_homeline = scanner.nextLine();

            WebElement checkoutpage_houseline = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'line1')]")));
            checkoutpage_houseline.sendKeys(checkout_homeline);
            
            Thread.sleep(5000);

            // Apartment name 2
            Scanner checkout_home2 = new Scanner(System.in);
            System.out.println("Enter the User Apartment Name");
            String checkout_homeline2 = scanner.nextLine();

            WebElement checkoutpage_houseline2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[contains(@id,'line2')]")));
            checkoutpage_houseline2.sendKeys(checkout_homeline2);
//            
//            Thread.sleep(5000);

            // Continue Button
//            WebElement AddressContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'redesign-v15-cta')]")));
//            AddressContinueButton.click();
//            
//            
//            System.out.println("UPi process is Starting");
//            
//            //Select UPI
//            
//            WebElement Selection_process=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'vertical')]")));
//            Selection_process.click();
//
//            WebElement SelectionContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'redesign-v15-cta')]")));
//          SelectionContinueButton.click();
//
//            // Last payment selection Page
//            WebElement paymentselection_option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'slotted-radio')]")));
//            paymentselection_option.click();
//
//            WebElement paymentselection_empty = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'gpay-phone')]")));
//            paymentselection_empty.clear();
//
//            // User phone number
//            Scanner bankselection = new Scanner(System.in);
//            System.out.println("Enter the Gpay Number");
//            String bank_gpay = scanner.nextLine();
//
//            WebElement paymentselection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'gpay-phone')]")));
//            paymentselection.sendKeys(bank_gpay);
//
//            // Click on the continue button
//            WebElement bankContinueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@id,'redesign-v15-cta')]")));
//            bankContinueButton.click();
//
//            Thread.sleep(3000);
//
//            WebElement cancel_payment = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Cancel Payment']")));
//            cancel_payment.click();
//
//            Thread.sleep(3000);
//
//            WebElement payment_cancelation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'positiveBtn')]")));
//            payment_cancelation.click();
//
//            System.out.println("My TestCase is Successfully Completed");

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

//    @AfterMethod
//    public void tearDown() {
//        // Close the browser
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}