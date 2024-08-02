package com.example.flightreservationsystem.Classes;

import java.util.Date;

public class Flights {
    private String flightNumber;
    private String departurePlace;
    private String destination;
    private Date departureDate;
    private Date departureTime;
    private Date arrivalDate;
    private Date arrivalTime;
    private String duration;
    private String aircraftModel;
    private int currentReservations;
    private int maxSeats;
    private int missedFlights;
    private Date bookingOpenDate;
    private double economyPrice;
    private double businessPrice;
    private double extraBaggagePrice;
    private String recurrent; // Possible values: "NONE", "DAILY", "WEEKLY"

    public Flights(String flightNumber, String departurePlace, String destination, Date departureDate,
                  Date departureTime, Date arrivalDate, Date arrivalTime, String duration, String aircraftModel,
                  int maxSeats, Date bookingOpenDate, double economyPrice, double businessPrice,
                  double extraBaggagePrice, String recurrent) {

        this.flightNumber = flightNumber;
        this.departurePlace = departurePlace;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.aircraftModel = aircraftModel;
        this.currentReservations = 0;
        this.maxSeats = maxSeats;
        this.missedFlights = 0;
        this.bookingOpenDate = bookingOpenDate;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.extraBaggagePrice = extraBaggagePrice;
        this.recurrent = recurrent;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(String departurePlace) {
        this.departurePlace = departurePlace;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public int getCurrentReservations() {
        return currentReservations;
    }

    public void setCurrentReservations(int currentReservations) {
        this.currentReservations = currentReservations;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getMissedFlights() {
        return missedFlights;
    }

    public void setMissedFlights(int missedFlights) {
        this.missedFlights = missedFlights;
    }

    public Date getBookingOpenDate() {
        return bookingOpenDate;
    }

    public void setBookingOpenDate(Date bookingOpenDate) {
        this.bookingOpenDate = bookingOpenDate;
    }

    public double getEconomyPrice() {
        return economyPrice;
    }

    public void setEconomyPrice(double economyPrice) {
        this.economyPrice = economyPrice;
    }

    public double getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(double businessPrice) {
        this.businessPrice = businessPrice;
    }

    public double getExtraBaggagePrice() {
        return extraBaggagePrice;
    }

    public void setExtraBaggagePrice(double extraBaggagePrice) {
        this.extraBaggagePrice = extraBaggagePrice;
    }

    public String getRecurrent() {
        return recurrent;
    }

    public void setRecurrent(String recurrent) {
        this.recurrent = recurrent;
    }
}
