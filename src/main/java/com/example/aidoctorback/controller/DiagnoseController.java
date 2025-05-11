package com.example.aidoctorback.controller;


import com.example.aidoctorback.model.DiagnoseRequest;
import com.example.aidoctorback.model.DiagnoseResponse;
import com.example.aidoctorback.service.DiagnoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiagnoseController {

    @Autowired
    private DiagnoseService diagnoseService;

    @PostMapping("/diagnose")
    public DiagnoseResponse diagnose(@RequestBody DiagnoseRequest diagnoseRequest) {
        return diagnoseService.diagnose(diagnoseRequest.getInput());
    }
}
