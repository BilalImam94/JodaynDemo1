package com.JodaynDemo.listeners;

import com.JodaynDemo.utils.JiraRestClient;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JiraFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Throwable error = result.getThrowable();
        if (error == null) return;

        String testName = result.getMethod().getMethodName();
        String testCaseName = result.getInstanceName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String dynamicFilename = "logs/ErrorLog_" + timestamp + "_" + testName + "_" + testCaseName + ".log";
        String staticFilename = "logs/Error.log";  // Static log file that accumulates all errors

        try {

            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            Configuration config = context.getConfiguration();

            PatternLayout layout = PatternLayout.newBuilder()
                    .withPattern("[%d{yyyy-MM-dd HH:mm:ss}] %-5level - %msg%n")
                    .withConfiguration(config)
                    .build();

            FileAppender dynamicAppender = FileAppender.newBuilder()
                    .withFileName(dynamicFilename)
                    .withName("TestFailureAppender_" + timestamp)
                    .withImmediateFlush(true)
                    .withAppend(false)
                    .withLayout(layout)
                    .setConfiguration(config)
                    .build();

            dynamicAppender.start();
            Logger dynamicLogger = (Logger) LogManager.getLogger("TestFailureLogger_" + timestamp);
            dynamicLogger.addAppender(dynamicAppender);

            // Log the failure details to the dynamic log
            dynamicLogger.error("Test Failed: {} - {}", testName, result.getInstanceName());
            dynamicLogger.error("Error: {}", error.toString());
            for (StackTraceElement element : error.getStackTrace()) {
                dynamicLogger.error(element.toString());
            }

            dynamicAppender.stop();
            context.updateLoggers();
            logToStaticFile(staticFilename, error, testName, result);

            System.out.println("Test failure logged to dynamic file: " + dynamicFilename);
            System.out.println("Test failure logged to static file: " + staticFilename);

        } catch (Exception e) {
            System.err.println("Error while logging failure: " + e.getMessage());
        }

        String errorType = error.getClass().getName();
        String errorMessage = error.getMessage();
        String summary = "Test failed: " + testName + " " + testCaseName;
        String descriptionSummary = getDescription(error, errorMessage, errorType);

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

    //This method logs to the static Error.log file
    private void logToStaticFile(String filename, Throwable error, String testName, ITestResult result) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("Test Failed: " + testName + " - " + result.getInstanceName());
            writer.newLine();
            writer.write("Error: " + error.toString());
            writer.newLine();
            for (StackTraceElement element : error.getStackTrace()) {
                writer.write(element.toString());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error while writing to static log file: " + e.getMessage());
        }
    }
}
