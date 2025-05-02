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
@Listeners({AllureTestNg.class})
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
        if (ITestResult.FAILURE == result.getStatus()) {
            // Attach screenshots and logs on failure
            Util.attachScreenshot("Failure_Screenshot", getDriver()); // Take a screenshot on failure
            Util.attachTextLog("Failure_Log", "Test failed: " + result.getName());
        }

        getDriver().quit();
        tdriver.remove();
    }
}