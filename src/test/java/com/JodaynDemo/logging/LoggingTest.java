package com.JodaynDemo.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingTest {
    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    public static void main(String[] args) {
        System.setProperty("log4j2.debug", "true"); // Enable debug to see config loading
        logger.error("This is a test ERROR log message!");
    }
}
