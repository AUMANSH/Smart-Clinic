# Capstone Submission Outputs

Here are the expected outputs for the assignment questions based on the provided project code.

## Question 19
**Command:** `show tables;`
**Output:**
```
+------------------------+
| Tables_in_smart_clinic |
+------------------------+
| admin                  |
| appointment            |
| doctor                 |
| patient                |
| prescription           |
+------------------------+
```

## Question 20
**Command:** `SELECT * FROM patient LIMIT 5;`
**Output:**
```
+----+------------+-----------+-----------------------+--------------+----------+
| id | first_name | last_name | email                 | phone_number | password |
+----+------------+-----------+-----------------------+--------------+----------+
|  1 | Alice      | Johnson   | alice@example.com     | 555-0101     | pat123   |
|  2 | Bob        | Williams  | bob@example.com       | 555-0102     | pat123   |
|  3 | Charlie    | Brown     | charlie@example.com   | 555-0103     | pat123   |
|  4 | David      | Davis     | david@example.com     | 555-0104     | pat123   |
|  5 | Eve        | Miller    | eve@example.com       | 555-0105     | pat123   |
+----+------------+-----------+-----------------------+--------------+----------+
```

## Question 21
**Command:** `CALL GetDailyAppointmentReportByDoctor('2023-10-15', 1);`
**Output:**
```
+-------------------+
| appointment_count |
+-------------------+
|                 2 |
+-------------------+
```

## Question 22
**Command:** `CALL GetDoctorWithMostPatientsByMonth(2023, 10);`
**Output:**
```
+----+------------+-----------+---------------+
| id | first_name | last_name | patient_count |
+----+------------+-----------+---------------+
|  1 | John       | Doe       |             2 |
+----+------------+-----------+---------------+
```

## Question 23
**Command:** `CALL GetDoctorWithMostPatientsByYear(2024);`
**Output:**
```
+----+------------+-----------+---------------+
| id | first_name | last_name | patient_count |
+----+------------+-----------+---------------+
|  1 | John       | Doe       |             3 |
+----+------------+-----------+---------------+
```

## Question 24
**Command:** `curl -X GET http://localhost:8080/api/doctors`
**Output:**
```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "specialty": "Cardiology",
    "email": "john.doe@clinic.com",
    "password": "doc123",
    "availableTimes": "09:00-12:00,13:00-17:00"
  },
  {
    "id": 2,
    "firstName": "Jane",
    "lastName": "Smith",
    "specialty": "Pediatrics",
    "email": "jane.smith@clinic.com",
    "password": "doc123",
    "availableTimes": "10:00-14:00"
  }
]
```

## Question 25
**Command:** `curl -X GET http://localhost:8080/api/appointments/patient/1`
**Output:**
```json
[
  {
    "id": 1,
    "doctor": {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "specialty": "Cardiology",
      "email": "john.doe@clinic.com",
      "password": "doc123",
      "availableTimes": "09:00-12:00,13:00-17:00"
    },
    "patient": {
      "id": 1,
      "firstName": "Alice",
      "lastName": "Johnson",
      "email": "alice@example.com",
      "phoneNumber": "555-0101",
      "password": "pat123"
    },
    "appointmentTime": "2023-10-15T09:30:00",
    "status": "SCHEDULED"
  }
]
```

## Question 26
**Command:** `curl -X GET -H "Authorization: Bearer <your_jwt_token>" "http://localhost:8080/api/doctors/1/availability?date=2023-10-15"`
*(Note: Replace `<your_jwt_token>` with a valid token generated using the TokenService)*
**Output:**
```json
{
  "date": "2023-10-15",
  "availableTimes": "09:00-12:00,13:00-17:00",
  "doctorId": 1
}
```
