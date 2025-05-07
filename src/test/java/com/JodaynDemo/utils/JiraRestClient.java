package com.JodaynDemo.utils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class JiraRestClient {

    public static void createIssue(String email, String apiToken, String jiraBaseUrl,
                                   String projectKey, String summary, Throwable error) {

        try {
            String auth = email + ":" + apiToken;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            error.printStackTrace(pw);

            JSONObject jsonPayload = getJsonObject(projectKey, summary, sw);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(jiraBaseUrl + "rest/api/2/issue"))
                    .header("Authorization", "Basic " + encodedAuth)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload.toString()))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int status = response.statusCode();
            System.out.println("Status Code: " + status);
            System.out.println("Response: " + response.body());

            if (status == 201) {
                System.out.println("JIRA ticket created.");
            } else {
                System.err.println("Failed to create ticket. Status: " + status);
            }

        } catch (Exception e) {
            System.err.println("Error creating JIRA issue: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static @NotNull JSONObject getJsonObject(String projectKey, String summary, StringWriter sw) {
        String stackTrace = sw.toString();

        JSONObject fields = new JSONObject();
        fields.put("summary", summary);
        fields.put("description", stackTrace);

        JSONObject project = new JSONObject();
        project.put("key", projectKey);
        fields.put("project", project);

        JSONObject issueType = new JSONObject();
        issueType.put("name", "Bug");
        fields.put("issuetype", issueType);

        JSONObject payload = new JSONObject();
        payload.put("fields", fields);

        return payload;
    }
}
