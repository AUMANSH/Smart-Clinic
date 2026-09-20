INSERT INTO admin (username, password) VALUES ('admin', 'admin123');

INSERT INTO doctor (first_name, last_name, specialty, email, password, available_times) VALUES 
('John', 'Doe', 'Cardiology', 'john.doe@clinic.com', 'doc123', '09:00-12:00,13:00-17:00'),
('Jane', 'Smith', 'Pediatrics', 'jane.smith@clinic.com', 'doc123', '10:00-14:00');

INSERT INTO patient (first_name, last_name, email, phone_number, password) VALUES 
('Alice', 'Johnson', 'alice@example.com', '555-0101', 'pat123'),
('Bob', 'Williams', 'bob@example.com', '555-0102', 'pat123'),
('Charlie', 'Brown', 'charlie@example.com', '555-0103', 'pat123'),
('David', 'Davis', 'david@example.com', '555-0104', 'pat123'),
('Eve', 'Miller', 'eve@example.com', '555-0105', 'pat123'),
('Frank', 'Wilson', 'frank@example.com', '555-0106', 'pat123');

INSERT INTO appointment (doctor_id, patient_id, appointment_time, status) VALUES 
(1, 1, '2023-10-15 09:30:00', 'SCHEDULED'),
(1, 2, '2023-10-15 10:00:00', 'SCHEDULED'),
(2, 3, '2023-10-16 11:00:00', 'SCHEDULED'),
(1, 4, '2024-01-20 14:00:00', 'SCHEDULED'),
(1, 5, '2024-01-20 15:00:00', 'SCHEDULED'),
(1, 6, '2024-01-20 16:00:00', 'SCHEDULED');

INSERT INTO prescription (appointment_id, medication, dosage, instructions, date_issued) VALUES 
(1, 'Lisinopril', '10mg', 'Take once daily', '2023-10-15 10:00:00');
