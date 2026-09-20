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
    @GetMapping("/{user}/{doctorId}/availability/{token}")
    public ResponseEntity<Map<String, Object>> getDoctorAvailability(
            @PathVariable String user,
            @PathVariable Long doctorId,
            @RequestParam("date") String dateString,
            @PathVariable String token) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            tokenService.extractEmail(token); // Validate it's a valid token
        } catch (Exception e) {
            response.put("error", "Invalid token");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        LocalDate date = LocalDate.parse(dateString);
        java.util.List<String> availability = doctorService.getAvailableTimeSlots(doctorId, date);
        
        response.put("doctorId", doctorId);
        response.put("date", date);
        response.put("availableTimes", availability);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getDoctorsBySpecialtyAndTime(
            @RequestParam String specialty,
            @RequestParam String time) {
        return ResponseEntity.ok(doctorService.getDoctorsBySpecialtyAndTime(specialty, time));
    }

    @GetMapping
    public ResponseEntity<?> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
