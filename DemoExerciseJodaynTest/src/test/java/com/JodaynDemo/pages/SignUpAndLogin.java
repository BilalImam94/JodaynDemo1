package com.JodaynDemo.pages;

import com.JodaynDemo.tests.TestBasic;
import com.JodaynDemo.utils.JSONReader;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class SignUpAndLogin extends TestBasic {

    public WebElement loginToYourAccount() {
        return driver.findElement(By.cssSelector("div[class='login-form'] h2"));
    }

    public WebElement loginEmailInput() {
        return driver.findElement(By.cssSelector("input[data-qa='login-email']"));
    }

    public WebElement loginPasswordInput() {
        return driver.findElement(By.cssSelector("input[data-qa='login-password']"));
    }

    public WebElement loginButton() {
        return driver.findElement(By.cssSelector("button[data-qa='login-button']"));
    }

    public WebElement errorLogin() {
        return driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div/form/p"));
    }

    public WebElement newUserSignup() {
        return driver.findElement(By.cssSelector("div[class='signup-form'] h2"));
    }

    public WebElement signupNameInput() {
        return driver.findElement(By.cssSelector("input[data-qa='signup-name']"));
    }

    public WebElement signupEmailInput() {
        return driver.findElement(By.cssSelector("input[data-qa='signup-email']"));
    }

    public WebElement signupButton() {
        return driver.findElement(By.cssSelector("button[data-qa='signup-button']"));
    }

    public WebElement emailAddressAlreadyExist() {
        return driver.findElement(By.xpath("//section/div/div/div[3]/div/form/p"));
    }

    private WebDriver driver;

    public SignUpAndLogin(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getNewUserSignup() {
        return newUserSignup();
    }

    private void fillSignup(String name, String email) {
        signupNameInput().sendKeys(name);
        signupEmailInput().sendKeys(email);
        signupButton().click();
    }

    public AccountInformation fillCorrectSignup(String name, String email) {
        fillSignup(name, email);
        return new AccountInformation(driver);
    }

    public SignUpAndLogin fillIncorrectSignup() throws IOException, ParseException {
        fillSignup(JSONReader.existingUser("name"), JSONReader.existingUser("email"));
        return this;
    }

    public WebElement getLoginToYourAccount() {
        return loginToYourAccount();
    }

    private void fillLogin(String email, String password) {
        loginEmailInput().sendKeys(email);
        loginPasswordInput().sendKeys(password);
        loginButton().click();
    }

    public LoggedIn fillCorrectLogin(String email, String password) {
        fillLogin(email, password);
        return new LoggedIn(driver);
    }

    public SignUpAndLogin fillIncorrectLogin(String email, String password) {
        fillLogin(email, password);
        return this;
    }

    public WebElement getErrorLogin() {
        return errorLogin();
    }

    public WebElement getEmailAddressAlreadyExist() {
        return emailAddressAlreadyExist();
    }
}
