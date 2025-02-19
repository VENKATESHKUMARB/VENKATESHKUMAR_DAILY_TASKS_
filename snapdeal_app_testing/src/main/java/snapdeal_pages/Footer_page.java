package snapdeal_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.webdrivermanager;

public class Footer_page {

    WebDriver driver;
    webdrivermanager utils;

 
    By footerPolicyInfo = By.xpath("//div[text()='Policy Info']");
    By privacyPolicy = By.xpath("//a[text()='Privacy Policy']");
    By termsOfSale = By.xpath("//a[text()='Terms of Sale']");
    By termsOfUse = By.xpath("//a[text()='Terms of Use']");
    By reportAbuse = By.xpath("//a[text()='Report Abuse & Takedown Policy']");
    By knowYourBISStandard = By.xpath("//a[text()='Know Your BIS Standard']");
    By productsUnderBISCertification = By.xpath("//a[text()='Products Under Cumpulsory BIS Certification']");
    By faq = By.xpath("//a[text()='FAQ']");

    public Footer_page(WebDriver driver) {
        this.driver = driver;
        this.utils = new webdrivermanager(driver);
    }


    public void hoverOverPolicyInfo() {
        WebElement footerPolicyElement = utils.waitForElementVisible(footerPolicyInfo, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(footerPolicyElement).perform();
    }

  
    public void hoverAndVerifyPrivacyPolicy() {
        WebElement privacyElement = utils.waitForElementVisible(privacyPolicy, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(privacyElement).perform();
    }

   
    public void hoverAndVerifyTermsOfSale() {
        WebElement termsOfSaleElement = utils.waitForElementVisible(termsOfSale, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(termsOfSaleElement).perform();
    }

    
    public void hoverAndVerifyTermsOfUse() {
        WebElement termsOfUseElement = utils.waitForElementVisible(termsOfUse, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(termsOfUseElement).perform();
    }

    
    public void hoverAndVerifyReportAbuse() {
        WebElement reportAbuseElement = utils.waitForElementVisible(reportAbuse, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(reportAbuseElement).perform();
    }

   
    public void hoverAndVerifyBISStandard() {
        WebElement bisElement = utils.waitForElementVisible(knowYourBISStandard, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(bisElement).perform();
    }

    
    public void hoverAndVerifyBISCertification() {
        WebElement bisCertificationElement = utils.waitForElementVisible(productsUnderBISCertification, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(bisCertificationElement).perform();
    }


    public void hoverAndVerifyFAQ() {
        WebElement faqElement = utils.waitForElementVisible(faq, 20);
        Actions actions = new Actions(driver);
        actions.moveToElement(faqElement).perform();
    }
}
