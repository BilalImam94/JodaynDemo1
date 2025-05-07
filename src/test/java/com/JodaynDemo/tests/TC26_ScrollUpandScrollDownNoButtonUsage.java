package com.JodaynDemo.tests;

import com.JodaynDemo.pages.Home;
import io.qameta.allure.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Regression Tests")
@Feature("Verify")
public class TC26_ScrollUpandScrollDownNoButtonUsage extends TestBasic {

    @Test(description = "Test Case 26: Verify Scroll Up without 'Arrow' button and Scroll Down functionality")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("Verify Scroll Up without 'Arrow' button and Scroll Down functionality")
    @Description("""
            1. Launch browser
            2. Navigate to url 'http://automationexercise.com'
            3. Verify that home page is visible successfully
            4. Scroll down page to bottom
            5. Verify 'SUBSCRIPTION' is visible
            6. Scroll up page to top
            7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen""")
    public void verifyScrollUpWithoutArrowButtonAndScrollDownFunctionality() throws InterruptedException {
        TC1_UserRegistration.verifyThatHomePageIsVisibleSuccessfully();
        TC25_ScrollUpandScrollDown.verifySubscriptionIsVisible();
        verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen();
    }

    @Step("Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen")
    private void verifyThatPageIsScrolledUpAndFullFledgedPracticeWebsiteForAutomationEngineersTextIsVisibleOnScreen() throws InterruptedException {

        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView();", new Home(getDriver()).getFullFledgedPracticeWebsiteForAutomationEngineers());
        boolean fullFledgedTextIsDisplayed = new Home(getDriver()).getFullFledgedPracticeWebsiteForAutomationEngineers().isDisplayed();
        Assert.assertTrue(fullFledgedTextIsDisplayed, "Verify that 'Full-Fledged practice website for Automation Engineers' text is visible on screen");

        Long yOffset = (Long) js.executeScript("return window.pageYOffset;");
        Assert.assertNotNull(yOffset, "Scroll offset should not be null");
        double value = yOffset.doubleValue();
        System.out.println("Current scroll position: " + value);
        Assert.assertTrue(value < 2500, "Verify that page is scrolled up");
    }
}
