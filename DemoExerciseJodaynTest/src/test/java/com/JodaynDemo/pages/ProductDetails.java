package com.JodaynDemo.pages;

import com.JodaynDemo.utils.JSONReader;
import com.JodaynDemo.utils.SeleniumHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ProductDetails {

    public WebElement productName() {
        return driver.findElement(By.cssSelector("div[class='product-information'] h2"));
    }

    public WebElement productCategory() {
        return driver.findElement(By.xpath("//section/div/div/div[2]/div[2]/div[2]/div/p[1]"));
    }

    public WebElement productPrice() {
        return driver.findElement(By.cssSelector("div[class='product-information'] span span"));
    }

    public WebElement productAvailability() {
        return driver.findElement(By.xpath("//section/div/div/div[2]/div[2]/div[2]/div/p[2]"));
    }

    public WebElement productCondition() {
        return driver.findElement(By.xpath("//section/div/div/div[2]/div[2]/div[2]/div/p[3]"));
    }

    public WebElement productBrand() {
        return driver.findElement(By.xpath("//section/div/div/div[2]/div[2]/div[2]/div/p[4]"));
    }

    public WebElement quantityInput() {
        return driver.findElement(By.id("quantity"));
    }

    public WebElement addToCartButton() {
        return driver.findElement(By.cssSelector("button[class='btn btn-default cart']"));
    }

    public WebElement viewCartButton() {
        return driver.findElement(By.cssSelector("a[href='/view_cart'] u"));
    }

    public WebElement writeYourReview() {
        return driver.findElement(By.cssSelector("a[href='#reviews']"));
    }

    public WebElement yourNameInput() {
        return driver.findElement(By.id("name"));
    }

    public WebElement emailAddress() {
        return driver.findElement(By.id("email"));
    }

    public WebElement addReviewHere() {
        return driver.findElement(By.id("review"));
    }

    public WebElement submitButton() {
        return driver.findElement(By.id("button-review"));
    }

    public WebElement successMessage() {
        return driver.findElement(By.cssSelector("div[class='alert-success alert'] span"));
    }

    private WebDriver driver;

    public ProductDetails(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getProductName() {
        return productName();
    }

    public WebElement getProductCategory() {
        return productCategory();
    }

    public WebElement getProductPrice() {
        return productPrice();
    }

    public WebElement getProductAvailability() {
        return productAvailability();
    }

    public WebElement getProductCondition() {
        return productCondition();
    }

    public WebElement getProductBrand() {
        return productBrand();
    }

    public ProductDetails increaseQuantity(String value) {
        quantityInput().clear();
        quantityInput().sendKeys(value);
        return this;
    }

    public ProductDetails addToCartButtonClick() {
        addToCartButton().click();
        return this;
    }

    public Cart viewCartButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, viewCartButton());
        viewCartButton().click();
        return new Cart(driver);
    }

    public WebElement getWriteYourReview() {
        return writeYourReview();
    }

    public ProductDetails fillReview() throws IOException, ParseException {
        yourNameInput().sendKeys(JSONReader.existingUser("name"));
        emailAddress().sendKeys(JSONReader.existingUser("email"));
        addReviewHere().sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Sed viverra, elit quis interdum feugiat, mi urna aliquam est, at venenatis quam odio et nisl." +
                " In at massa sit amet dui hendrerit mattis ac sit amet erat.");
        submitButton().click();
        return this;
    }

    public WebElement getSuccessMessage() {
        return successMessage();
    }
}
