
# ✈️ Simple Flight Reservation System

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
  - [General Features](#general-features)
  - [Admin Features](#admin-features)
  - [Passenger Features](#passenger-features)
  - [Additional Functionalities](#additional-functionalities)
- [Design and UI/UX](#design-and-uiux)
- [Technologies Used](#technologies-used)
- [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

## Project Overview
This project involves developing an **Android application** to assist an airline company in managing their flight reservations efficiently. The application caters to two types of users:

- **Airline Admin**
- **Passenger**

Each user role has specific functionalities designed to streamline flight scheduling, booking, and management processes.

---

## Features

### General Features
- [X] **User Authentication**
  - [X] **Sign Up**
    - [X] Users can register as either **Admin** or **Passenger**.
    - [X] Validations for all input fields including email format, password strength, and required personal information.
  - [X] **Sign In**
    - [X] Users can log in using registered email and password.
    - [X] "Remember Me" functionality using Shared Preferences.
  - [X] **Logout**
    - [X] Securely logs out the user and clears session data.
  - [ ] **Edit Profile**
    - [ ] Users can update their personal information.
    - [ ] Real-time validation for updated information.

    
- [ ] **Data Loading and Error Handling**
  - [X] Application loads initial flight data from a custom API upon startup.
  - [ ] Implements error handling for data loading failures with a retry mechanism.
  - [ ] Displays the **five closest upcoming flights** on successful data load.

---

### Admin Features
- [ ] **Flight Management**
  - [X] **Create New Flight**
    - [X] Input all necessary flight details including:
      - [X] Flight Number
      - [X] Departure & Arrival Cities
      - [X] Departure & Arrival Dates and Times
      - [X] Duration and Aircraft Model
      - [X] Seat Capacity and Current Reservations
      - [X] Booking Open Date
      - [X] Pricing Details for Economy and Business Classes
      - [X] Extra Baggage Pricing
      - [X] Recurrence Settings (None, Daily, Weekly)
      
  - [ ] **Edit Existing Flight**
    - [ ] Update any flight details as needed.
    - [ ] Validation to prevent conflicting schedules or overbooking.
          
  - [ ] **Delete Flight**
    - [ ] Secure deletion with confirmation prompts.
    - [ ] Handles cascading effects on existing reservations.
          
  - [X] **View Flights**
    - [X] **Available Flights:** List of all flights open for reservation.
    - [X] **Upcoming Flights:** Flights scheduled but not yet open for reservation.
    - [X] **Flight Archive:** History of past flights with details.
          
  - [ ] **Reservation Management**
    - [X] View all reservations for a specific flight.
    - [ ] Search and filter reservations by passenger name, booking date, etc.
          
  - [X] **Flight Filtering**
    - [X] Filter flights based on:
      - [X] Departure/Arrival Cities
      - [X] Departure/Arrival Dates


---

### Passenger Features
- [ ] **Flight Search and Booking**
  - [ ] **Search Flights**
    - [ ] Search by:
      - [X] Departure and Arrival Cities
      - [X] Departure Date
      - [ ] Trip Type: One-way or Round-trip
      - [ ] Return Date (if Round-trip is selected)
    - [ ] **Sorting Options:**
      - [ ] Lowest Cost
      - [ ] Shortest Duration
  - [ ] **Flight Details View**
    - [ ] View comprehensive details of selected flights.
  - [ ] **Make Reservation**
    - [ ] Select:
      - [ ] Flight Class: Economy or Business
      - [X] Number of Extra Baggage

    - [ ] Auto-fill personal details from profile with editable food preferences.
    - [ ] Display reservation summary with total cost calculation.

  - [ ] **Manage Reservations**
    - [ ] **View Current Reservations:**
      - [ ] Details of all upcoming booked flights.
    - [ ] **Reschedule Reservation:**
      - [ ] Modify flight dates/times subject to availability.
      - [ ] Recalculate costs and handle any additional charges or refunds.
    - [ ] **Cancel Reservation:**
      - [ ] Secure cancellation with confirmation and refund processing.
    - [X] **View Past Reservations:**
      - [X] History of all completed trips.
- [ ] **Notifications**
  - [ ] **Reservation Updates:**
    - [ ] Receive alerts for any changes in flight schedules or statuses.
  - [ ] **Flight Reminders:**
    - [ ] Reminder notifications 24 hours before departure.
  - [ ] **Promotional Notifications:**
    - [ ] Receive updates about special offers and discounts.

---


- [X] **Data Storage and Management**
  - [X] Use **SQLite** for real-time database management and authentication.
    - [X] Secure and scalable data handling.
    - [X] Offline data persistence support.
  - [X] Implement proper **data validation** and **error handling** mechanisms.
- [X] **API Development**
  - [X] Develop a custom **RESTful API** for initial flight data loading.
    
---




