package com.JodaynDemo.pages;

import com.JodaynDemo.utils.ExcelReader;
import com.JodaynDemo.utils.JSONReader;
import com.JodaynDemo.utils.SeleniumHelper;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.Map;

public class Payments {

    public WebElement nameOnCardInput() {
        return driver.findElement(By.cssSelector("input[data-qa='name-on-card']"));
    }

    public WebElement cardNumberInput() {
        return driver.findElement(By.cssSelector("input[data-qa='card-number']"));
    }

    public WebElement cvcInput() {
        return driver.findElement(By.cssSelector("input[data-qa='cvc']"));
    }

    public WebElement expirationMonthInput() {
        return driver.findElement(By.cssSelector("input[data-qa='expiry-month']"));
    }

    public WebElement expirationYearInput() {
        return driver.findElement(By.cssSelector("input[data-qa='expiry-year']"));
    }

    public WebElement payAndConfirmOrderButton() {
        return driver.findElement(By.cssSelector("button[data-qa='pay-button']"));
    }

    public WebElement alertSuccess() {
        return driver.findElement(By.xpath("//div[contains(@id, 'success_message')]/div"));
    }

    public WebElement successMessage() {
        return driver.findElement(By.cssSelector("div[class='col-sm-9 col-sm-offset-1'] p"));
    }

    public WebElement downloadInvoiceButton() {
        return driver.findElement(By.cssSelector("a[class='btn btn-default check_out']"));
    }

    public WebElement continueButton() {
        return driver.findElement(By.cssSelector("a[data-qa='continue-button']"));
    }

    private WebDriver driver;

    public Payments(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public Payments fillPaymentDetails() throws IOException, ParseException {
        nameOnCardInput().sendKeys(JSONReader.paymentDetails("nameOnCard"));
        cardNumberInput().sendKeys(JSONReader.paymentDetails("cardNumber"));
        cvcInput().sendKeys(JSONReader.paymentDetails("cvc"));
        expirationMonthInput().sendKeys(JSONReader.paymentDetails("expirationMonth"));
        expirationYearInput().sendKeys(JSONReader.paymentDetails("expirationYear"));
        payAndConfirmOrderButton().click();
        return this;
    }

    public Payments fillPaymentDetailsFromExcel() throws IOException {
        Map<String, String> paymentData = ExcelReader.readDetails("PaymentDetails");

        // Log the data read from Excel
        System.out.println("Payment Data from Excel:");
        paymentData.forEach((k, v) -> System.out.println(k + " => " + v));

        sendKeysIfPresent(nameOnCardInput(), paymentData.get("nameOnCard"));
        sendKeysIfPresent(cardNumberInput(), paymentData.get("cardNumber"));
        sendKeysIfPresent(cvcInput(), paymentData.get("cvc"));
        sendKeysIfPresent(expirationMonthInput(), paymentData.get("expirationMonth"));
        sendKeysIfPresent(expirationYearInput(), paymentData.get("expirationYear"));

        payAndConfirmOrderButton().click();
        return this;
    }

    private void sendKeysIfPresent(WebElement element, String value) {
        if (value != null && !value.trim().isEmpty()) {
            element.sendKeys(value.trim());
        } else {
            System.out.println("Warning: Skipping field due to missing value.");
        }
    }

    public WebElement getSuccessMessage() {
        return successMessage();
    }

    public Payments downloadInvoiceButtonClick() {
        downloadInvoiceButton().click();
        return this;
    }

    public Home continueButtonClick() {
        SeleniumHelper.waitForElementToBeClickable(driver, continueButton());
        continueButton().click();
        return new Home(driver);
    }
}
