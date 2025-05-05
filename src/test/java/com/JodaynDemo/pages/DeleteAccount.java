package com.JodaynDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DeleteAccount {

    public WebElement accountDeleted() {
        return driver.findElement(By.cssSelector("h2[data-qa='account-deleted']"));
    }

    public WebElement continueButton() {
        return driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
    }

    private WebDriver driver;

    public DeleteAccount(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getAccountDeleted() {
        return accountDeleted();
    }

    public Home continueButtonClick() {
        continueButton().click();
        return new Home(driver);
    }
}
