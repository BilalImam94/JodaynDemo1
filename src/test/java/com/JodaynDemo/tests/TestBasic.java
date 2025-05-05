package com.JodaynDemo.tests;

import com.JodaynDemo.utils.BrowserManager;
import com.JodaynDemo.utils.PropertiesLoader;
import com.JodaynDemo.utils.Util;
import io.qameta.allure.Allure;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;

public class TestBasic {

    protected static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeMethod
    public void setup() throws IOException {
        String url = PropertiesLoader.loadProperty("url");
        WebDriver driver = BrowserManager.doBrowserSetup();
        tdriver.set(driver);
        getDriver().get(url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        WebDriver driver = getDriver();

        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            try {
                Util.attachScreenshot(driver); // Correct signature
                Util.attachTextLog("Failure Reason", result.getThrowable().toString());
            } catch (Exception e) {
                System.err.println("❌ Error during attachment: " + e.getMessage());
            }
        }

        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.err.println("❌ Error quitting driver: " + e.getMessage());
        } finally {
            tdriver.remove();
        }
    }
}