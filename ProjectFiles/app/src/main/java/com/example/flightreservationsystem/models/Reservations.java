package com.example.flightreservationsystem.models;

import java.time.LocalDate;

public class Reservations {
    private int reservationID;
    private int flightID;
    private int userID;
    private String seatClass;
    private int extraBaggage;
    private String classType;
    private double totalPrice;
    private LocalDate reservationDate;

    private String foodPreference;

    public Reservations() {
    }

    public Reservations(int reservationID, int flightID, int userID, String seatClass, int extraBaggage, double totalPrice, String foodPreference , LocalDate reservationDate) {
        this.reservationID = reservationID;
        this.flightID = flightID;
        this.userID = userID;
        this.seatClass = seatClass;
        this.extraBaggage = extraBaggage;
        this.totalPrice = totalPrice;
        this.foodPreference = foodPreference;
        this.reservationDate = reservationDate;
    }

    public Reservations(int flightID, int userID, String seatClass, int extraBaggage , String foodPreference) {
        this.flightID = flightID;
        this.userID = userID;
        this.seatClass = seatClass;
        this.extraBaggage = extraBaggage;
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

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getExtraBaggage() {
        return extraBaggage;
    }

    public void setExtraBaggage(int extraBaggage) {
        this.extraBaggage = extraBaggage;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}