package com.JodaynDemo.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util {

    public static String generateCurrentDateAndTime() {
        return new java.text.SimpleDateFormat("ddMMyyyyHHmmssSSS").format(new java.util.Date());
    }

    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public static byte[] attachScreenshot(String failureScreenshot, WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String attachTextLog(String message, String s) {
        return message;
    }

}
