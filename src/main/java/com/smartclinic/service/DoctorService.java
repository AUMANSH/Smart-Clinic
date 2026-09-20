package com.smartclinic.service;

import com.smartclinic.model.Doctor;
import com.smartclinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // Method returns available time slots for doctor on a given date.
    public String getAvailableTimeSlots(Long doctorId, LocalDate date) {
        Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
        if (doctorOpt.isPresent()) {
            // For simplicity, we just return the availableTimes string from the doctor record.
            // In a real app, this would check against existing appointments for the specific date.
            return doctorOpt.get().getAvailableTimes();
        }
        return "Doctor not found";
    }

    // Method validates doctor login credentials and returns structured response.
    public Map<String, Object> validateLogin(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);
        
        if (doctorOpt.isPresent() && doctorOpt.get().getPassword().equals(password)) {
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("doctor", doctorOpt.get());
        } else {
            response.put("success", false);
            response.put("message", "Invalid email or password");
        }
        return response;
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
