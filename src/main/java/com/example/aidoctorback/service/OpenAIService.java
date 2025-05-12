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

    // Hardcoded API details (just type these in your code)
    private static final String API_KEY = "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";  // Replace with your OpenAI API key
    private static final String API_URL = "https://api.openai.com/v1/completions";  // OpenAI API URL
    private static final String MODEL_NAME = "gpt-4o-mini";  // The model you want to use

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDiagnosis(String symptoms) {
        // Create the prompt that will be sent to the OpenAI API
        String prompt = "Given the following symptoms, provide a possible diagnosis and advice: " + symptoms;

        // Prepare the body of the request
        Map<String, Object> body = new HashMap<>();
        body.put("model", MODEL_NAME);  // Use the specified model
        body.put("prompt", prompt);
        body.put("max_tokens", 150);  // Set max tokens for response length
        body.put("temperature", 0.7);  // Control creativity in the response

        // Set up the headers with the API key
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // Send the request to the OpenAI API and receive the response
        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);

        // Check if the response is valid and return the diagnosis
        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && responseBody.containsKey("choices")) {
            // Extract the response from the 'choices' field
            return responseBody.get("choices").toString();
        } else {
            return "No diagnosis found. Please provide more specific symptoms.";
        }
    }
}
