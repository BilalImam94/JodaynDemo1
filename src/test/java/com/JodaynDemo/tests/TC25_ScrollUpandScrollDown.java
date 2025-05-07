package com.JodaynDemo.tests;

import com.JodaynDemo.pages.Home;
import io.qameta.allure.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Epic("Regression Tests")
@Feature("Verify")
public class TC25_ScrollUpandScrollDown extends TestBasic {

    @Test(description = "Test Case 25: Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Verify Scroll Up using 'Arrow' button and Scroll Down functionality")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Scroll down page to bottom
            5. Verify 'SUBSCRIPTION' is visible
            6. Click on arrow at bottom right side to move upward
            7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen""")
    public void verifyScrollUpUsingArrowButtonAndScrollDownFunctionality() {
        TC1_UserRegistration.verifyThatHomePageIsVisibleSuccessfully();
        verifySubscriptionIsVisible();
        verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen();
    }

    @Step("Verify 'SUBSCRIPTION' is visible")
    public static void verifySubscriptionIsVisible() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Home home = new Home(getDriver());

        js.executeScript("arguments[0].scrollIntoView();", home.getSubscription());

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement subscription = wait.until(ExpectedConditions.visibilityOf(home.getSubscription()));

        Assert.assertTrue(subscription.isDisplayed(), "Verify 'SUBSCRIPTION' is visible");
    }

    @Step("Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen")
    private void verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen() {
        Home home = new Home(getDriver());
        home.scrollUpButtonClick();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement fullFledgedText = wait.until(ExpectedConditions.visibilityOf(
                home.getFullFledgedPracticeWebsiteForAutomationEngineers()));

        Assert.assertNotNull(fullFledgedText, "Element should not be null");
        Assert.assertTrue(fullFledgedText.isDisplayed(),
                "Verify that 'Full-Fledged practice website for Automation Engineers' text is visible on screen");

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Long yOffset = (Long) js.executeScript("return window.pageYOffset;");
        Assert.assertNotNull(yOffset, "Scroll offset should not be null");
        double value = yOffset.doubleValue();
        System.out.println("Current scroll position: " + value);
        Assert.assertTrue(value < 2500, "Verify that page is scrolled up");
    }
}
