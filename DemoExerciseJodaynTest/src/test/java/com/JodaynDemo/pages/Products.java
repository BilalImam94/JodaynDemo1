package com.JodaynDemo.pages;

import com.JodaynDemo.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class Products {

    public WebElement titleTextCenter() {
        return driver.findElement(By.cssSelector(".title.text-center"));
    }

    public WebElement viewProductOfFirstProductButton() {
        return driver.findElement(By.cssSelector("a[href='/product_details/1']"));
    }

    public WebElement searchProductInput() {
        return driver.findElement(By.id("search_product"));
    }

    public WebElement submitSearchInput() {
        return driver.findElement(By.id("submit_search"));
    }

    public List<WebElement> searchResultsNames() {
        return driver.findElements(By.xpath("//div[contains(@class, 'productinfo text-center')]//p"));
    }

    public WebElement addToCartButton1() {
        return driver.findElement(By.cssSelector("a[data-product-id='1']"));
    }

    public WebElement addToCartButton2() {
        return driver.findElement(By.cssSelector("a[data-product-id='2']"));
    }

    public WebElement continueShoppingButton() {
        return driver.findElement(By.cssSelector("button[data-dismiss='modal']"));
    }

    public WebElement viewCartButton() {
        return driver.findElement(By.cssSelector("a[href='/view_cart'] u"));
    }

    public WebElement menCategory() {
        return driver.findElement(By.cssSelector("a[href='#Men']"));
    }

    public WebElement tShirtsCategory() {
        return driver.findElement(By.cssSelector("a[href='/category_products/3']"));
    }

    public WebElement brands() {
        return driver.findElement(By.cssSelector("div[class='brands-name']"));
    }

    public WebElement poloBrand() {
        return driver.findElement(By.cssSelector("a[href='/brand_products/Polo']"));
    }

    public WebElement madameBrand() {
        return driver.findElement(By.cssSelector("a[href='/brand_products/Madame']"));
    }

    public List<WebElement> addButtons() {
        return driver.findElements(By.cssSelector("a[class='btn btn-default add-to-cart']"));
    }

    private WebDriver driver;

    public Products(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getTitleTextCenter() {
        return titleTextCenter();
    }

    public ProductDetails viewProductOfFirstProductButtonClick() {
        viewProductOfFirstProductButton().click();
        return new ProductDetails(driver);
    }

    public Products fillSearchProductInput(String searchProduct) {
        searchProductInput().sendKeys(searchProduct);
        submitSearchInput().click();
        return this;
    }

    public List<String> getProductsSearchNames() {
        return searchResultsNames()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public Cart addProductsToCart() {
        SeleniumHelper.waitForElementToBeClickable(driver, addToCartButton1());
        addToCartButton1().click();
        SeleniumHelper.waitForElementToBeClickable(driver, continueShoppingButton());
        continueShoppingButton().click();
        SeleniumHelper.waitForElementToBeClickable(driver, addToCartButton2());
        addToCartButton2().click();
        SeleniumHelper.waitForElementToBeClickable(driver, viewCartButton());
        viewCartButton().click();
        return new Cart(driver);
    }

    public Products menCategoryClick() {
        menCategory().click();
        return this;
    }

    public Products tShirtsCategoryClick() {
        tShirtsCategory().click();
        return this;
    }

    public WebElement getBrands() {
        return brands();
    }

    public Products poloBrandClick() {
        poloBrand().click();
        return this;
    }

    public Products madameBrandClick() {
        madameBrand().click();
        return this;
    }

    public Products addAllProducts() {
        for (int i = 0; i < addButtons().size(); i = i + 2) {
            SeleniumHelper.waitForElementToBeClickable(driver, addButtons().get(i));
            addButtons().get(i).click();
            SeleniumHelper.waitForElementToBeClickable(driver, continueShoppingButton());
            continueShoppingButton().click();
        }
        return this;
    }
}

