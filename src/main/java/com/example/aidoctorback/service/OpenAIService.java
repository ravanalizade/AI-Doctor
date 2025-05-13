package com.example.aidoctorback.service;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {

    private static final String API_KEY = "";  // Add here your own api key, i dont wanna share mine(
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String MODEL_NAME = "gpt-4o-mini";

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDiagnosis(String symptoms) {
        String prompt = "Given the following symptoms, provide a possible diagnosis and advice, and don't exceed 2 advices: " + symptoms;

        Map<String, Object> body = new HashMap<>();
        body.put("model", MODEL_NAME);
        body.put("prompt", prompt);
        body.put("max_tokens", 150);
        body.put("temperature", 0.7);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("choices")) {
            Object choices = responseBody.get("choices");
            if (choices instanceof java.util.List) {
                java.util.List<?> choiceList = (java.util.List<?>) choices;
                if (!choiceList.isEmpty()) {
                    Map<String, Object> firstChoice = (Map<String, Object>) choiceList.get(0);
                    String text = firstChoice.get("text").toString().trim();

                    return cleanResponse(text);
                }
            }
        }

        return "No diagnosis found. Please provide more specific symptoms.";
    }

    private String cleanResponse(String responseText) {
        responseText = responseText.replace(",", ""); // Remove commas
        responseText = responseText.replaceAll("\\n+", "");

        responseText = responseText.trim();

        int firstPeriodIndex = responseText.indexOf(".");
        if (firstPeriodIndex != -1) {
            responseText = responseText.substring(firstPeriodIndex + 1).trim();
        }
        return responseText;
    }
}
