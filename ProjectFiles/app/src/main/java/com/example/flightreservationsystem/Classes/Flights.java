package com.example.flightreservationsystem.Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Flights {
    private int flight_id;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private String duration;
    private String aircraftModel;
    private int maxSeats;
    private int currentReservations;
    private int peopleMissed;
    private LocalDate bookingOpenDate;
    private double economyPrice;
    private double businessPrice;
    private double extraBaggagePrice;
    private String isRecurrent;

    public Flights() {
    }

    public Flights(int flight_id ,String flightNumber, String departureCity, String arrivalCity, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, String duration, String aircraftModel, int maxSeats, int currentReservations, int peopleMissed, LocalDate bookingOpenDate, double economyPrice, double businessPrice, double extraBaggagePrice, String isRecurrent) {
        this.flight_id = flight_id;
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.aircraftModel = aircraftModel;
        this.maxSeats = maxSeats;
        this.currentReservations = currentReservations;
        this.peopleMissed = peopleMissed;
        this.bookingOpenDate = bookingOpenDate;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.extraBaggagePrice = extraBaggagePrice;
        this.isRecurrent = isRecurrent;
    }

    public Flights(String flightNumber, String departureCity, String arrivalCity, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, String duration, String aircraftModel, int maxSeats, int currentReservations, int peopleMissed, LocalDate bookingOpenDate, double economyPrice, double businessPrice, double extraBaggagePrice, String isRecurrent) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.aircraftModel = aircraftModel;
        this.maxSeats = maxSeats;
        this.currentReservations = currentReservations;
        this.peopleMissed = peopleMissed;
        this.bookingOpenDate = bookingOpenDate;
        this.economyPrice = economyPrice;
        this.businessPrice = businessPrice;
        this.extraBaggagePrice = extraBaggagePrice;
        this.isRecurrent = isRecurrent;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
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

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public int getCurrentReservations() {
        return currentReservations;
    }

    public void setCurrentReservations(int currentReservations) {
        this.currentReservations = currentReservations;
    }

    public int getPeopleMissed() {
        return peopleMissed;
    }

    public void setPeopleMissed(int peopleMissed) {
        this.peopleMissed = peopleMissed;
    }

    public LocalDate getBookingOpenDate() {
        return bookingOpenDate;
    }

    public void setBookingOpenDate(LocalDate bookingOpenDate) {
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

    public String getIsRecurrent() {
        return isRecurrent;
    }

    public void setIsRecurrent(String isRecurrent) {
        this.isRecurrent = isRecurrent;
    }
}
