package com.example.flightreservationsystem.Classes;

public class Validation {

    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("^\\d{10}$");
    }

    public static boolean isValidName(String name) {
        return name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    public static boolean validateConfirmPassword(String confirmPassword, String password) {
        return confirmPassword.equals(password);
    }

    public static boolean isValidPassportNumber(String passportNumber) {
        return passportNumber.matches("^[A-Z]{2}\\d{7}$");
    }

    public static boolean isValidFlightNumber(String flightNumber) {
        return flightNumber.matches("^[A-Z]{2}\\d{4}$");
    }

    public static boolean isValidSeatNumber(String seatNumber) {
        return seatNumber.matches("^[A-Z]\\d{1,2}$");
    }

    public static boolean isValidDate(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isValidTime(String time) {
        return time.matches("^\\d{2}:\\d{2}$");
    }

    public static boolean isValidPrice(String price) {
        return price.matches("^\\d+(\\.\\d{1,2})?$");
    }

    public static boolean isValidPassengerCount(String passengerCount) {
        return passengerCount.matches("^\\d{1,2}$");
    }

    public static boolean isValidPassengerType(String passengerType) {
        return passengerType.matches("^(Adult|Child|Infant)$");
    }

    public static boolean isValidPassengerSeat(String passengerSeat) {
        return passengerSeat.matches("^(Window|Middle|Aisle)$");
    }

    public static boolean isValidPassengerMeal(String passengerMeal) {
        return passengerMeal.matches("^(Veg|Non-Veg)$");
    }

    public static boolean isValidPassengerNationality(String passengerNationality) {
        return passengerNationality.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    public static boolean isValidPassengerDateOfBirth(String passengerDateOfBirth) {
        return passengerDateOfBirth.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isValidPassengerPassportIssueDate(String passengerPassportIssueDate) {
        return passengerPassportIssueDate.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isValidPassengerPassportIssuePlace(String passengerPassportIssuePlace) {
        return passengerPassportIssuePlace.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }

    public static boolean isValidPassengerPassportNumber(String passengerPassportNumber) {
        return passengerPassportNumber.matches("^[A-Z]{2}\\d{7}$");
    }

    public static boolean isValidPassengerPassportExpiryDate(String passengerPassportExpiryDate) {
        return passengerPassportExpiryDate.matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isValidPassengerPassportExpiryPlace(String passengerPassportExpiryPlace) {
        return passengerPassportExpiryPlace.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
    }




}
