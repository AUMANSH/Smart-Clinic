package com.smartclinic.controller;

import com.smartclinic.service.DoctorService;
import com.smartclinic.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    // Exposes a GET endpoint for doctor availability using dynamic parameters.
    @GetMapping("/{id}/availability")
    public ResponseEntity<Map<String, Object>> getDoctorAvailability(
            @PathVariable Long id,
            @RequestParam("date") String dateString,
            @RequestHeader(value="Authorization", required=false) String authHeader) {
        
        Map<String, Object> response = new HashMap<>();
        
        // Validates token and returns a structured response using ResponseEntity.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.put("error", "Unauthorized access");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        String token = authHeader.substring(7);
        try {
            tokenService.extractEmail(token); // Validate it's a valid token
        } catch (Exception e) {
            response.put("error", "Invalid token");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        LocalDate date = LocalDate.parse(dateString);
        String availability = doctorService.getAvailableTimeSlots(id, date);
        
        response.put("doctorId", id);
        response.put("date", date);
        response.put("availableTimes", availability);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
