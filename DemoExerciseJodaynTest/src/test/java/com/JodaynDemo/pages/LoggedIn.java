package com.JodaynDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoggedIn {

    public WebElement username() {
        return driver.findElement(By.xpath("//header/div/div/div/div[2]/div/ul/li[10]/a"));
    }

    public WebElement deleteAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//header/div/div/div/div[2]/div/ul/li[5]/a")));
    }

    public WebElement logoutButton() {
        return driver.findElement(By.xpath("//header/div/div/div/div[2]/div/ul/li[4]/a"));
    }

    private WebDriver driver;

    public LoggedIn(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getUsername() {
        return username();
    }

    public DeleteAccount deleteAccountButtonClick() {
        deleteAccountButton().click();
        return new DeleteAccount(driver);
    }

    public SignUpAndLogin logoutButtonClick() {
        logoutButton().click();
        return new SignUpAndLogin(driver);
    }
}
