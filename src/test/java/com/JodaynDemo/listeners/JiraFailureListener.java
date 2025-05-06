package com.JodaynDemo.listeners;

import com.JodaynDemo.utils.JiraRestClient;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JiraFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable error = result.getThrowable();
        if (error == null) return;

        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        String testCaseName = result.getInstanceName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String loggerName = "FailureLog-" + timestamp;
        String filename = "logs/ErrorLog_" + timestamp + "_" + testCaseName + "_" + testName + ".log";

        try {
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            Configuration config = context.getConfiguration();

            PatternLayout layout = PatternLayout.newBuilder()
                    .withPattern("[%d{yyyy-MM-dd HH:mm:ss}] %-5level - %msg%n")
                    .withConfiguration(config)
                    .build();

            FileAppender appender = FileAppender.newBuilder()
                    .setName(loggerName)
                    .withFileName(filename)
                    .withImmediateFlush(true)
                    .withAppend(false)
                    .setConfiguration(config)
                    .withLayout(layout)
                    .build();

            appender.start();
            config.addAppender(appender);

            // ✅ Create a dedicated logger (no root inheritance)
            LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.ERROR, loggerName,
                    "true", null, null, config, null);
            loggerConfig.addAppender(appender, Level.ERROR, null);
            config.addLogger(loggerName, loggerConfig);
            context.updateLoggers();

            Logger dynamicLogger = LogManager.getLogger(loggerName);
            String summary = "Test failed: " + testName + " | Class: " + className + " | TestCaseName: " + testCaseName;
            dynamicLogger.error(summary, error);

            System.out.println("Saved failure log to: " + filename);
        } catch (Exception e) {
            System.err.println("Failed to create dynamic log file for test failure: " + e.getMessage());
        }

        // Prepare Jira ticket
        String errorType = error.getClass().getName();
        String errorMessage = error.getMessage();
        String summary = "Test failed: " + result.getMethod().getMethodName() + " | Class: " + result.getInstanceName();
        String descriptionSummary = getDescription(error, errorMessage, errorType);

        System.out.println("Preparing to log JIRA issue with description:\n" + descriptionSummary);

        JiraRestClient.createIssue(
                "bimam9@gmail.com",
                "ATATT3xFfGF0NfG-5Sg5fKXV4k8ntlhwt29MtU3c2itS8iIDrqgajynmyahV8je2vMhEsOAv1JIEFCuJHq19mbLNlVQoSoaG5n4cAR07Pacfn8CnQ63w-4JaTQ3ebMjlDsaQk-QfnrvIXHaR3XpG3O0S4bDYOovDdQ_LsRBZ0fm1jeodtIB9pIg=A512AF1A",
                "https://bimam9.atlassian.net/",
                "JOD",
                summary,
                error
        );
    }

    private String getDescription(Throwable throwable, String message, String errorType) {
        String expected = "";
        String actual = "";

        if (throwable instanceof AssertionError && message != null && message.contains("expected")) {
            String[] lines = message.split("\\r?\\n");
            for (String line : lines) {
                if (line.toLowerCase().contains("expected")) expected = line.trim();
                if (line.toLowerCase().contains("actual")) actual = line.trim();
            }
        }

        return "Error Type: " + errorType + "\n"
                + "Error Message: " + message + "\n"
                + expected + "\n"
                + actual;
    }
}
