package com.smartclinic.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String specialty;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    
    @ElementCollection
    private java.util.List<String> availableTimes;
}
