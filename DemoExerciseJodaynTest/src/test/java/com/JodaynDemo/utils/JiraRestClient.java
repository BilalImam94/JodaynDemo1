package com.JodaynDemo.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class JiraRestClient {

    public static void createIssue(String email, String apiToken, String jiraBaseUrl,
                                   String projectKey, String summary, String description) {

        try {
            String auth = email + ":" + apiToken;
            String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

            //Sanitize summary and description
            String safeSummary = summary.replace("\"", "\\\"");
            String safeDescription = description.replace("\"", "\\\"").replace("\n", "\\n");

            String json = "{\n" +
                    "  \"fields\": {\n" +
                    "    \"project\": {\"key\": \"" + projectKey + "\"},\n" +
                    "    \"summary\": \"" + safeSummary + "\",\n" +
                    "    \"description\": \"" + safeDescription + "\",\n" +
                    "    \"issuetype\": {\"name\": \"Bug\"}\n" +
                    "  }\n" +
                    "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(jiraBaseUrl + "rest/api/2/issue"))
                    .header("Authorization", "Basic " + encodedAuth)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int status = response.statusCode();
            System.out.println("Status Code: " + status);
            System.out.println("Response: " + response.body());

            if (status == 201) {
                System.out.println("✅ JIRA ticket created.");
            } else {
                System.err.println("❌ Failed to create ticket. Status: " + status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
