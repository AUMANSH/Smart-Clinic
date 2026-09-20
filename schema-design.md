# Database Schema Design

The Smart Clinic Management System uses a relational database (MySQL) to manage entities.

## Tables

### 1. Doctor
Stores doctor information.
- `id` (BIGINT, Primary Key, Auto Increment)
- `first_name` (VARCHAR)
- `last_name` (VARCHAR)
- `specialty` (VARCHAR)
- `email` (VARCHAR, Unique)
- `password` (VARCHAR)
- `available_times` (VARCHAR) - Stores available times, e.g., "09:00-12:00,13:00-17:00"

### 2. Patient
Stores patient information.
- `id` (BIGINT, Primary Key, Auto Increment)
- `first_name` (VARCHAR)
- `last_name` (VARCHAR)
- `email` (VARCHAR, Unique)
- `phone_number` (VARCHAR, Unique)
- `password` (VARCHAR)

### 3. Appointment
Stores appointment details, linking Doctors and Patients.
- `id` (BIGINT, Primary Key, Auto Increment)
- `doctor_id` (BIGINT, Foreign Key referencing Doctor.id)
- `patient_id` (BIGINT, Foreign Key referencing Patient.id)
- `appointment_time` (DATETIME)
- `status` (VARCHAR) - e.g., "SCHEDULED", "COMPLETED", "CANCELLED"

### 4. Prescription
Stores prescriptions given by doctors to patients.
- `id` (BIGINT, Primary Key, Auto Increment)
- `appointment_id` (BIGINT, Foreign Key referencing Appointment.id)
- `medication` (VARCHAR)
- `dosage` (VARCHAR)
- `instructions` (TEXT)
- `date_issued` (DATETIME)

### 5. Admin
Stores administrator credentials.
- `id` (BIGINT, Primary Key, Auto Increment)
- `username` (VARCHAR, Unique)
- `password` (VARCHAR)

## Entity-Relationship Notes
- A **Doctor** can have multiple **Appointments** (One-to-Many).
- A **Patient** can have multiple **Appointments** (One-to-Many).
- An **Appointment** belongs to exactly one **Doctor** and one **Patient** (Many-to-One).
- An **Appointment** can have one or more **Prescriptions** (One-to-Many).
