package com.JodaynDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CheckOut {

    public List<WebElement> addressDelivery() {
        return driver.findElements(By.xpath("//ul[contains(@id, 'address_delivery')]//li"));
    }

    public List<WebElement> addressInvoice() {
        return driver.findElements(By.xpath("//ul[contains(@id, 'address_invoice')]//li"));
    }

    public WebElement totalAmount() {
        return driver.findElement(By.xpath("//section/div/div[5]/table/tbody/tr[3]/td[4]/p"));
    }

    public WebElement comment() {
        return driver.findElement(By.cssSelector("textarea[name='message']"));
    }

    public WebElement placeOrderButton() {
        return driver.findElement(By.cssSelector("a[href='/payment']"));
    }

    private WebDriver driver;

    public CheckOut(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public List<String> getAddressDelivery() {
        return addressDelivery()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAddressInvoice() {
        return addressInvoice()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getTotalAmount() {
        return totalAmount();
    }

    public Payments enterComment(String text) {
        comment().sendKeys(text);
        placeOrderButton().click();
        return new Payments(driver);
    }
}
