package com.example.flightreservationsystem.models;

public class Validation {

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"); // email format
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^\\d{10}$"); // 10 digits
    }

    public static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"); // only letters, spaces, hyphens, apostrophes, periods, commas
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"); // 8 characters, 1 uppercase, 1 lowercase, 1 number, 1 special character
    }

    public static boolean validateConfirmPassword(String confirmPassword, String password) {
        return confirmPassword.equals(password); // confirm password matches password
    }

    public static boolean isValidPassportNumber(String passportNumber) {
        return passportNumber.matches("^[A-Z]{2}\\d{7}$"); // AA1234567
    }

    public static boolean isValidFlightNumber(String flightNumber) {
        return flightNumber.matches("^[A-Z]{2}\\d{4}$"); // AA1234
    }

    public static boolean isValidDate(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$"); // yyyy-MM-dd
    }

    public static boolean isValidTime(String time) {
        return time.matches("^\\d{2}:\\d{2}$"); // 00:00
    }


    public static boolean isValidDuration(String duration) {
        return duration.matches("^\\d{2}h\\d{2}m$"); // 00h00m
    }

    public static boolean isValidAirCraftModel(String airCraftModel) {
        // not empty
        return !airCraftModel.isEmpty();
    }

    public static boolean isValidRecurrence(String recurrence) {
        return recurrence.matches("^(Daily|Weekly|No)$"); // Daily, Weekly, No
    }

}
