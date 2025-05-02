package com.JodaynDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CreateAccount {

    public WebElement accountCreated() {
        return driver.findElement(By.cssSelector("h2[data-qa='account-created']"));
    }

    public WebElement continueButton() {
        return driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
    }

    private WebDriver driver;

    public CreateAccount(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getAccountCreated() {
        return accountCreated();
    }

    public LoggedIn continueButtonClick() {
        continueButton().click();
        return new LoggedIn(driver);
    }
}
