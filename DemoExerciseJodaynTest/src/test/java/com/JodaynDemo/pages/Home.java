package com.JodaynDemo.pages;

import com.JodaynDemo.tests.TestBasic;
import com.JodaynDemo.utils.JSONReader;
import com.JodaynDemo.utils.SeleniumHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class Home extends TestBasic {


    public WebElement girlImgResponsive() {
        return driver.findElement(By.cssSelector("div.item.active img[alt='demo website for practice']"));
    }

    public WebElement signupLoginButton() {
        return driver.findElement(By.cssSelector("a[href='/login']"));
    }

    public WebElement contactUsButton() {
        return driver.findElement(By.cssSelector("a[href='/contact_us']"));
    }

    public WebElement testCasesButton() {
        return driver.findElement(By.cssSelector("a[href='/test_cases']"));
    }

    public WebElement productsButton() {
        return driver.findElement(By.cssSelector("a[href='/products']"));
    }

    public WebElement cartButton() {
        return driver.findElement(By.cssSelector("a[href='/view_cart']"));
    }

    public WebElement viewProduct1Button() {
        return driver.findElement(By.cssSelector("a[href='/product_details/1']"));
    }

    public WebElement categories() {
        return driver.findElement(By.id("accordian"));
    }

    public WebElement womenCategory() {
        return driver.findElement(By.xpath("//*[@id='accordian']/div[1]/div[1]/h4/a/span/i"));
    }

    public WebElement dressCategory() {
        return driver.findElement(By.cssSelector("a[href='/category_products/1']"));
    }

    public WebElement recommendedItems() {
        return driver.findElement(By.cssSelector("div[class='recommended_items'] h2"));
    }

    public WebElement blueTopAddToCartButton() {
        return driver.findElement(By.cssSelector("div[id='recommended-item-carousel'] a[class='btn btn-default add-to-cart']"));
    }

    public WebElement viewCartButton() {
        return driver.findElement(By.cssSelector("div[class='modal-content'] a[href='/view_cart']"));
    }

    public WebElement scrollUpButton() {
        return driver.findElement(By.id("scrollUp"));
    }

    public WebElement fullFledgedPracticeWebsiteForAutomationEngineers() {
        return driver.findElement(By.xpath("//section[1]/div/div/div/div/div/div[1]/div[1]/h2"));
    }

    // Footer elements
    public WebElement subscription() {
        return driver.findElement(By.cssSelector("div[class='single-widget'] h2"));
    }

    public WebElement subscribeEmailInput() {
        return driver.findElement(By.id("susbscribe_email"));
    }

    public WebElement subscribeButton() {
        return driver.findElement(By.id("subscribe"));
    }

    public WebElement alertSuccessSubscribe() {
        return driver.findElement(By.id("success-subscribe"));
    }

    private WebDriver driver;

    public Home(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

        public WebElement homePageIsVisible() {
            return girlImgResponsive();
        }

    public SignUpAndLogin signupLoginClick() {
        signupLoginButton().click();
        return new SignUpAndLogin(driver);
    }

    public ContactUs contactUsButtonClick() {
        contactUsButton().click();
        return new ContactUs(driver);
    }

    public TestCases testCasesButtonClick() {
        testCasesButton().click();
        return new TestCases(driver);
    }

    public Products productsButtonClick() {
        productsButton().click();
        return new Products(driver);
    }

    public Cart cartButtonClick() {
        cartButton().click();
        return new Cart(driver);
    }

    public ProductDetails viewProduct1ButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, viewProduct1Button());
        viewProduct1Button().click();
        return new ProductDetails(driver);
    }

    public WebElement getCategories() {
        return categories();
    }

    public Home womenCategoryClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, womenCategory());
        womenCategory().click();
        return this;
    }

    public Products dressCategoryClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, dressCategory());
        dressCategory().click();
        return new Products(driver);
    }

    public WebElement getRecommendedItems() {
        return recommendedItems();
    }

    public Home blueTopAddToCartButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, blueTopAddToCartButton());
        blueTopAddToCartButton().click();
        return this;
    }

    public Cart viewCartButtonClick() {
        SeleniumHelper.waitForElementToBeVisible(driver, viewCartButton());
        viewCartButton().click();
        return new Cart(driver);
    }

    public Home scrollUpButtonClick() {
        scrollUpButton().click();
        return this;
    }

    public WebElement getFullFledgedPracticeWebsiteForAutomationEngineers() {
        SeleniumHelper.waitForElementToBeVisible(driver, fullFledgedPracticeWebsiteForAutomationEngineers());
        return fullFledgedPracticeWebsiteForAutomationEngineers();
    }


    //footer
    public WebElement getSubscription() {
        return subscription();
    }

    public Home fillSubscribe() throws IOException, ParseException {
        subscribeEmailInput().sendKeys(JSONReader.existingUser("email"));
        SeleniumHelper.waitForElementToBeClickable(driver, subscribeButton());
        subscribeButton().click();
        return this;
    }

    public WebElement getAlertSuccessSubscribe() {
        return alertSuccessSubscribe();
    }
}


