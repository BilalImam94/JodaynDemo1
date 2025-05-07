package com.JodaynDemo.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ContactUs {

    public WebElement getInTouch() {
        return driver.findElement(By.cssSelector("h2.title:nth-child(2)"));
    }

    public WebElement nameInput() {
        return driver.findElement(By.name("name"));
    }

    public WebElement emailInput() {
        return driver.findElement(By.name("email"));
    }

    public WebElement subjectInput() {
        return driver.findElement(By.name("subject"));
    }

    public WebElement messageInput() {
        return driver.findElement(By.id("message"));
    }

    public WebElement uploadFileInput() {
        return driver.findElement(By.name("upload_file"));
    }

    public WebElement submitButton() {
        return driver.findElement(By.name("submit"));
    }

    public WebElement alertSuccess() {
        return driver.findElement(By.cssSelector(".status.alert.alert-success"));
    }

    public WebElement homePageButton() {
        return driver.findElement(By.xpath("//a[@class='btn btn-success']"));
    }

    private WebDriver driver;

    public ContactUs(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getGetInTouch() {
        return getInTouch();
    }

    public ContactUs fillForm() {
        nameInput().sendKeys("Bilal");
        emailInput().sendKeys("Bilal@testtt.jdd");
        subjectInput().sendKeys("Test subject");
        messageInput().sendKeys("FIll Form Test Sample");
        uploadFileInput().sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\sample.txt");
        return this;
    }

    public ContactUs submitButtonClick() {
        submitButton().click();
        return this;
    }

    public ContactUs okButtonClick() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public WebElement getAlertSuccess() {
        return alertSuccess();
    }

    public Home homePageButtonClick() {
        homePageButton().click();
        return new Home(driver);
    }
}
