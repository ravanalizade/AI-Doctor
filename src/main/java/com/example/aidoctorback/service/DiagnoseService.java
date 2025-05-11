package com.example.aidoctorback.service;


import com.example.aidoctorback.model.DiagnoseResponse;
import org.springframework.stereotype.Service;

@Service
public class DiagnoseService {

    public DiagnoseResponse diagnose(String input){
        String lowerInput = input.toLowerCase();

        if (lowerInput.contains("fever") || lowerInput.contains("cough") || lowerInput.contains("chills")) {
            return new DiagnoseResponse("Flu", "Rest, stay hydrated, and consider paracetamol.");
        } else if (lowerInput.contains("headache") || lowerInput.contains("nausea")) {
            return new DiagnoseResponse("Migraine", "Rest in a quiet dark room and drink water.");
        } else if (lowerInput.contains("pneumonia")) {
            return new DiagnoseResponse("Pneumonia", "See a doctor immediately. Antibiotics may be needed.");
        } else {
            return new DiagnoseResponse("Unknown", "Sorry, I need more information or clearer symptoms.");
         }
    }
}
