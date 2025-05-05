package com.JodaynDemo.pages;

import com.JodaynDemo.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Cart {

    public List<WebElement> productName() {
        return driver.findElements(By.xpath("//td[contains(@class, 'cart_description')]//a"));
    }

    public List<WebElement> price() {
        return driver.findElements(By.xpath("//td[contains(@class, 'cart_price')]/p"));
    }

    public List<WebElement> quantity() {
        return driver.findElements(By.xpath("//td[contains(@class, 'cart_quantity')]/button"));
    }

    public List<WebElement> totalPrice() {
        return driver.findElements(By.xpath("//p[contains(@class, 'cart_total_price')]"));
    }

    public WebElement shoppingCart() {
        return driver.findElement(By.cssSelector("li[class='active']"));
    }

    public WebElement proceedToCheckoutButton() {
        return driver.findElement(By.cssSelector("a[class='btn btn-default check_out']"));
    }

    public WebElement registerLoginButton() {
        return driver.findElement(By.cssSelector("a[href='/login'] u"));
    }

    public WebElement xButton1() {
        return driver.findElement(By.cssSelector("a[data-product-id='1']"));
    }

    public WebElement xButton2() {
        return driver.findElement(By.cssSelector("a[data-product-id='2']"));
    }

    public WebElement emptyCartSpan() {
        return driver.findElement(By.id("empty_cart"));
    }

    public List<WebElement> xButtons() {
        return driver.findElements(By.cssSelector("a[class='cart_quantity_delete']"));
    }

    private WebDriver driver;

    public Cart(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<String> getProductsNames() {
        return productName()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getPrices() {
        return price()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getQuantity() {
        return quantity()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getTotalPrices() {
        return totalPrice()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getShoppingCart() {
        return shoppingCart();
    }

    public Cart proceedToCheckoutButtonClick() {
        proceedToCheckoutButton().click();
        return this;
    }

    public CheckOut proceedToCheckoutLoggedButtonClick() {
        proceedToCheckoutButton().click();
        return new CheckOut(driver);
    }

    public SignUpAndLogin registerLoginButtonClick() {
        registerLoginButton().click();
        return new SignUpAndLogin(driver);
    }

    public Cart xButtonClick() {
        xButton1().click();
        xButton2().click();
        return this;
    }

    public WebElement getEmptyCartSpan() {
        SeleniumHelper.waitForElementToBeVisible(driver, emptyCartSpan());
        return emptyCartSpan();
    }

    public Cart deleteAllAddedProducts() throws InterruptedException {
        int xButtonsSize = xButtons().size();
        for (int i = 0; i < xButtonsSize; i++) {
            xButtons().get(0).click();
            Thread.sleep(500);
        }
            return this;
    }
}
