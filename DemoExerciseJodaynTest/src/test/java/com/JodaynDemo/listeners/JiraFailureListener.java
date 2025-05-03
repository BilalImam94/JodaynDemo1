package com.JodaynDemo.listeners;

import com.JodaynDemo.utils.JiraRestClient;
import org.jetbrains.annotations.NotNull;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class JiraFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable throwable = result.getThrowable();
        String errorType = throwable.getClass().getName(); // e.g., java.lang.AssertionError

        String errorDetails = throwable.getMessage(); // Message like: Verify that 'ACCOUNT DELETED!' is visible

        String description = getString(throwable, errorDetails, errorType);

        String summary = "Test failed: " + result.getMethod().getMethodName();

        JiraRestClient.createIssue(
                "bimam9@gmail.com",
                "ATATT3xFfGF0NfG-5Sg5fKXV4k8ntlhwt29MtU3c2itS8iIDrqgajynmyahV8je2vMhEsOAv1JIEFCuJHq19mbLNlVQoSoaG5n4cAR07Pacfn8CnQ63w-4JaTQ3ebMjlDsaQk-QfnrvIXHaR3XpG3O0S4bDYOovDdQ_LsRBZ0fm1jeodtIB9pIg=A512AF1A",
                "https://bimam9.atlassian.net/",
                "JOD",
                summary,
                description
        );
    }

    private static @NotNull String getString(Throwable throwable, String errorDetails, String errorType) {
        String expected = "";
        String actual = "";

        // Extract Expected and Actual if they are available in the message
        if (throwable instanceof AssertionError && errorDetails != null && errorDetails.contains("Expected")) {
            String[] lines = errorDetails.split("\\r?\\n");
            for (String line : lines) {
                if (line.trim().startsWith("Expected")) expected = line.trim();
                if (line.trim().startsWith("Actual")) actual = line.trim();
            }
        }

        String description = "Error Type: " + errorType + "\n"
                + "Error Message: " + errorDetails + "\n"
                + expected + "\n"
                + actual;
        return description;
    }
}
