package com.example.aidoctorback.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnoseResponse {

    private String diagnosedDisease;

    private String advice;
}
