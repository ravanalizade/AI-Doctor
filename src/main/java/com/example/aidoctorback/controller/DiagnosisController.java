package com.example.aidoctorback.controller;

import com.example.aidoctorback.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnosis")
@RequiredArgsConstructor
public class DiagnosisController {

    private final OpenAIService openAIService;

    @PostMapping
    public ResponseEntity<String> getDiagnosis(@RequestBody String symptoms) {
        System.out.println("Controller received symptoms: " + symptoms);

        String response = openAIService.getDiagnosis(symptoms);

        return ResponseEntity.ok(response);
    }
}
