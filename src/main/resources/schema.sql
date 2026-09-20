DROP TABLE IF EXISTS prescription;
DROP TABLE IF EXISTS appointment;
DROP TABLE IF EXISTS doctor;
DROP TABLE IF EXISTS patient;
DROP TABLE IF EXISTS admin;

CREATE TABLE admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE doctor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    specialty VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    available_times VARCHAR(255)
);

CREATE TABLE patient (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone_number VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE appointment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    doctor_id BIGINT,
    patient_id BIGINT,
    appointment_time DATETIME,
    status VARCHAR(50),
    FOREIGN KEY (doctor_id) REFERENCES doctor(id),
    FOREIGN KEY (patient_id) REFERENCES patient(id)
);

CREATE TABLE prescription (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    appointment_id BIGINT,
    medication VARCHAR(255),
    dosage VARCHAR(255),
    instructions TEXT,
    date_issued DATETIME,
    FOREIGN KEY (appointment_id) REFERENCES appointment(id)
);

DROP PROCEDURE IF EXISTS GetDailyAppointmentReportByDoctor;
DELIMITER //
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN input_date DATE, IN input_doctor_id BIGINT)
BEGIN
    SELECT COUNT(*) as appointment_count
    FROM appointment
    WHERE DATE(appointment_time) = input_date AND doctor_id = input_doctor_id;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByMonth;
DELIMITER //
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN target_year INT, IN target_month INT)
BEGIN
    SELECT d.id, d.first_name, d.last_name, COUNT(DISTINCT a.patient_id) as patient_count
    FROM doctor d
    JOIN appointment a ON d.id = a.doctor_id
    WHERE YEAR(a.appointment_time) = target_year AND MONTH(a.appointment_time) = target_month
    GROUP BY d.id
    ORDER BY patient_count DESC
    LIMIT 1;
END //
DELIMITER ;


DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByYear;
DELIMITER //
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN target_year INT)
BEGIN
    SELECT d.id, d.first_name, d.last_name, COUNT(DISTINCT a.patient_id) as patient_count
    FROM doctor d
    JOIN appointment a ON d.id = a.doctor_id
    WHERE YEAR(a.appointment_time) = target_year
    GROUP BY d.id
    ORDER BY patient_count DESC
    LIMIT 1;
END //
DELIMITER ;
