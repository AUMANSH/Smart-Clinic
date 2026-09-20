package com.smartclinic.repository;

import com.smartclinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    // Derived query to retrieve patient by email
    Optional<Patient> findByEmail(String email);
    
    // Custom query to retrieve patient using either email or phone number
    @Query("SELECT p FROM Patient p WHERE p.email = :identifier OR p.phoneNumber = :identifier")
    Optional<Patient> findByEmailOrPhoneNumber(@Param("identifier") String identifier);
}
