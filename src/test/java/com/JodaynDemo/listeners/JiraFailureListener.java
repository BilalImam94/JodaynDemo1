package com.JodaynDemo.listeners;

import com.JodaynDemo.utils.JiraRestClient;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class JiraFailureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable error = result.getThrowable();
        if (error == null) return;

        String errorType = error.getClass().getName();
        String errorMessage = error.getMessage();
        String summary = "Test failed: " + result.getMethod().getMethodName() + " " +result.getInstanceName();

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
