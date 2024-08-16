package com.example.flightreservationsystem.models;

import java.time.LocalDateTime;

public class Reservations {
    private int reservationID;
    private int flightID;
    private int userID;
    private String flightClass;
    private int extraBags;
    private String classType;
    private double totalPrice;
    private LocalDateTime reservationDate;

    private String foodPreference;

    public Reservations() {
    }

    public Reservations(int reservationID, int flightID, int userID, String flightClass, int extraBags, double totalPrice, String foodPreference , LocalDateTime reservationDate) {
        this.reservationID = reservationID;
        this.flightID = flightID;
        this.userID = userID;
        this.flightClass = flightClass;
        this.extraBags = extraBags;
        this.totalPrice = totalPrice;
        this.foodPreference = foodPreference;
        this.reservationDate = reservationDate;
    }

    public Reservations(int flightID, int userID, String flightClass, int extraBags, String foodPreference) {
        this.flightID = flightID;
        this.userID = userID;
        this.flightClass = flightClass;
        this.extraBags = extraBags;
        this.foodPreference = foodPreference;




    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getFoodPreference() {
        return foodPreference;
    }

    public void setFoodPreference(String foodPreference) {
        this.foodPreference = foodPreference;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public int getExtraBags() {
        return extraBags;
    }

    public void setExtraBags(int extraBags) {
        this.extraBags = extraBags;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }
}