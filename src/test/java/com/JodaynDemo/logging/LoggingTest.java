package com.JodaynDemo.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingTest {
    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    public static void main(String[] args) {

        // Ensure logs directory exists
        java.io.File logDir = new java.io.File("logs");
        if (!logDir.exists()) {
            if (!logDir.mkdirs()) {
                System.err.println("Failed to create log directory!");
                return;
            }
        }

        System.setProperty("log4j2.debug", "true");
        logger.error("This is a test ERROR log message!");
    }
}
