package com.smartclinic.controller;

import com.smartclinic.model.Prescription;
import com.smartclinic.repository.PrescriptionRepository;
import com.smartclinic.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private TokenService tokenService;

    // POST endpoint saves a prescription with token and request body validation.
    @PostMapping
    public ResponseEntity<Map<String, Object>> createPrescription(
            @RequestBody Prescription prescription,
            @RequestHeader(value="Authorization", required=false) String authHeader) {
            
        Map<String, Object> response = new HashMap<>();
        
        // Returns structured success or error messages using ResponseEntity.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("success", false);
            response.put("message", "Unauthorized access");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        String token = authHeader.substring(7);
        try {
            tokenService.extractEmail(token);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Invalid token");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        if (prescription.getMedication() == null || prescription.getMedication().isEmpty()) {
            response.put("success", false);
            response.put("message", "Medication name is required");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        Prescription saved = prescriptionRepository.save(prescription);
        response.put("success", true);
        response.put("message", "Prescription saved successfully");
        response.put("prescription", saved);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
