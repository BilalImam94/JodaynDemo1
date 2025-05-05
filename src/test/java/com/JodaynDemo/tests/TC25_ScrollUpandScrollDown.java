package com.JodaynDemo.tests;

import com.JodaynDemo.pages.Home;
import io.qameta.allure.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    public void verifyScrollUpUsingArrowButtonAndScrollDownFunctionality() throws InterruptedException {
        TC1_UserRegistration.verifyThatHomePageIsVisibleSuccessfully();
        verifySubscriptionIsVisible();
        verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen();
    }

    @Step("Verify 'SUBSCRIPTION' is visible")
    public static void verifySubscriptionIsVisible() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", new Home(getDriver()).getSubscription());
        boolean subscriptionIsDisplayed = new Home(getDriver()).getSubscription().isDisplayed();
        Assert.assertTrue(subscriptionIsDisplayed, "Verify 'SUBSCRIPTION' is visible");
    }

    @Step("Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen")
    private void verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen() throws InterruptedException {
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        boolean fullFledgedTextIsDisplayed = new Home(getDriver())
                .scrollUpButtonClick()
                .getFullFledgedPracticeWebsiteForAutomationEngineers()
                .isDisplayed();
        Assert.assertTrue(fullFledgedTextIsDisplayed, "Verify that 'Full-Fledged practice website for Automation Engineers' text is visible on screen");
        double value = (double) js.executeScript("return window.pageYOffset;");
        Assert.assertTrue(value < 2500, "Verify that page is scrolled up");
        System.out.println(value);
    }
}
