package com.JodaynDemo.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Util {


    private static final Logger logger = LogManager.getLogger(Util.class);

    public static String generateCurrentDateAndTime() {
        return new java.text.SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new java.util.Date());
    }

    public static void attachScreenshot(WebDriver driver) {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshotBytes));
        } catch (Exception e) {
            System.err.println("❌ Failed to capture screenshot for Allure: " + e.getMessage());
        }
    }



    public static void attachTextLog(String name, String message) {
        try {
            Allure.addAttachment(name, message);
        } catch (Exception e) {
            System.err.println("❌ Failed to attach text log: " + e.getMessage());
            logger.error("Failed to attach text log: ", e);
        }
    }

    public static void logFailure(String failureMessage) {
        logger.error("Test failed: {}", failureMessage);
    }

}
