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


    private static final String API_KEY = "";  // Replace with your OpenAI API key
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String MODEL_NAME = "gpt-4o-mini";

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getDiagnosis(String symptoms) {
        String prompt = "Given the following symptoms, provide a possible diagnosis and advice: " + symptoms;


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
            return responseBody.get("choices").toString();
        } else {
            return "No diagnosis found. Please provide more specific symptoms.";
        }
    }
}
