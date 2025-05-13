package com.example.aidoctorback.controller;

import com.example.aidoctorback.service.OpenAIService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {

    private final OpenAIService openAIService;

    @PostMapping
    public ResponseEntity<Map<String, String>> getDiagnosis(@RequestBody QuestionRequest questionRequest) {
        System.out.println("Controller received question: " + questionRequest.getQuestion());

        String responseText = openAIService.getDiagnosis(questionRequest.getQuestion());

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("answer", responseText);

        return ResponseEntity.ok(responseMap);
    }


    @Setter
    @Getter
    public static class QuestionRequest {
        // Getter and Setter
        private String question;

    }
}
