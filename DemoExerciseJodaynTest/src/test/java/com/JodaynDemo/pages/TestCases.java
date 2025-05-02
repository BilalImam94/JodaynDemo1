package com.JodaynDemo.pages;

import com.JodaynDemo.utils.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TestCases {

    public WebElement testCases() {
        return driver.findElement(By.cssSelector("h2[class='title text-center'] b"));
    }

    private WebDriver driver;

    public TestCases(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getTestCases() {
        SeleniumHelper.waitForElementToBeVisible(driver, testCases());
        return testCases();
    }
}
