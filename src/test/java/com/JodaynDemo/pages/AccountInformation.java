package com.JodaynDemo.pages;

import com.JodaynDemo.tests.TestBasic;
import com.JodaynDemo.utils.ExcelReader;
import com.JodaynDemo.utils.JSONReader;
import com.JodaynDemo.utils.Util;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.Map;

public class AccountInformation extends TestBasic {

    private WebDriver driver;

    public WebElement enterAccountInformation() {
        return driver.findElement(By.xpath("/html/body/section/div/div/div/div[1]/h2/b"));
    }

    public WebElement titleMrCheckbox() {
        return driver.findElement(By.id("id_gender1"));
    }

    public WebElement passwordInput() {
        return driver.findElement(By.id("password"));
    }

    public WebElement daysSelect() {
        return driver.findElement(By.id("days"));
    }

    public WebElement monthsSelect() {
        return driver.findElement(By.id("months"));
    }

    public WebElement yearsSelect() {
        return driver.findElement(By.id("years"));
    }

    public WebElement newsletterCheckbox() {
        return driver.findElement(By.id("newsletter"));
    }

    public WebElement specialOffersCheckbox() {
        return driver.findElement(By.id("optin"));
    }

    public WebElement firstNameInput() {
        return driver.findElement(By.id("first_name"));
    }

    public WebElement lastNameInput() {
        return driver.findElement(By.id("last_name"));
    }

    public WebElement companyInput() {
        return driver.findElement(By.id("company"));
    }

    public WebElement address1Input() {
        return driver.findElement(By.id("address1"));
    }

    public WebElement address2Input() {
        return driver.findElement(By.id("address2"));
    }

    public WebElement countrySelect() {
        return driver.findElement(By.id("country"));
    }

    public WebElement stateInput() {
        return driver.findElement(By.id("state"));
    }

    public WebElement cityInput() {
        return driver.findElement(By.id("city"));
    }

    public WebElement zipcodeInput() {
        return driver.findElement(By.id("zipcode"));
    }

    public WebElement mobileNumberInput() {
        return driver.findElement(By.id("mobile_number"));
    }

    public WebElement createAccountButton() {
        return driver.findElement(By.cssSelector("button[data-qa='create-account']"));
    };

    public AccountInformation(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getEnterAccountInformation() {
        return enterAccountInformation();
    }

    public CreateAccount fillAccountDetails() throws IOException, ParseException {
        String password = "pass" + Util.generateCurrentDateAndTime();
        titleMrCheckbox().click();
        passwordInput().sendKeys(password);
        Select days = new Select(daysSelect());
        days.selectByValue(JSONReader.accountDetails("day"));
        Select months = new Select(monthsSelect());
        months.selectByValue(JSONReader.accountDetails("month"));
        Select years = new Select(yearsSelect());
        years.selectByValue(JSONReader.accountDetails("year"));
        newsletterCheckbox().click();
        specialOffersCheckbox().click();
        firstNameInput().sendKeys(JSONReader.accountDetails("firstName"));
        lastNameInput().sendKeys(JSONReader.accountDetails("lastName"));
        companyInput().sendKeys(JSONReader.accountDetails("company"));
        address1Input().sendKeys(JSONReader.accountDetails("address1"));
        address2Input().sendKeys(JSONReader.accountDetails("address2"));
        Select countrySelector = new Select(countrySelect());
        countrySelector.selectByValue(JSONReader.accountDetails("country"));
        stateInput().sendKeys(JSONReader.accountDetails("state"));
        cityInput().sendKeys(JSONReader.accountDetails("city"));
        zipcodeInput().sendKeys(JSONReader.accountDetails("zipcode"));
        mobileNumberInput().sendKeys(JSONReader.accountDetails("mobileNumber"));
        createAccountButton().click();
        return new CreateAccount(driver);
    }

    public CreateAccount fillAccountDetailsFromExcel() throws IOException {
        Map<String, String> accountData = ExcelReader.readDetails("AccountDetails");

        // Log the data read from Excel
        System.out.println("Account Data from Excel:");
        accountData.forEach((k, v) -> System.out.println(k + " => " + v));

        String password = "pass" + Util.generateCurrentDateAndTime();
        titleMrCheckbox().click();
        passwordInput().sendKeys(password);

        selectValueIfPresent(daysSelect(), accountData.get("day"));
        selectValueIfPresent(monthsSelect(), accountData.get("month"));
        selectValueIfPresent(yearsSelect(), accountData.get("year"));

        newsletterCheckbox().click();
        specialOffersCheckbox().click();

        sendKeysIfPresent(firstNameInput(), accountData.get("firstName"));
        sendKeysIfPresent(lastNameInput(), accountData.get("lastName"));
        sendKeysIfPresent(companyInput(), accountData.get("company"));
        sendKeysIfPresent(address1Input(), accountData.get("address1"));
        sendKeysIfPresent(address2Input(), accountData.get("address2"));

        selectValueIfPresent(countrySelect(), accountData.get("country"));

        sendKeysIfPresent(stateInput(), accountData.get("state"));
        sendKeysIfPresent(cityInput(), accountData.get("city"));
        sendKeysIfPresent(zipcodeInput(), accountData.get("zipcode"));
        sendKeysIfPresent(mobileNumberInput(), accountData.get("mobileNumber"));

        createAccountButton().click();
        return new CreateAccount(driver);
    }

    private void sendKeysIfPresent(WebElement element, String value) {
        if (value != null && !value.trim().isEmpty()) {
            element.sendKeys(value.trim());
        } else {
            System.out.println("Warning: Skipping field due to missing value.");
        }
    }

    private void selectValueIfPresent(WebElement dropdown, String value) {
        if (value != null && !value.trim().isEmpty()) {
            new Select(dropdown).selectByValue(value.trim());
        } else {
            System.out.println("Warning: Skipping dropdown due to missing value.");
        }
    }

}
