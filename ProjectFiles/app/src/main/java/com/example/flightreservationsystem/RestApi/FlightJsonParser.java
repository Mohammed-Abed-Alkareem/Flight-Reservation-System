package com.example.flightreservationsystem.RestApi;

import com.example.flightreservationsystem.models.Flights;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightJsonParser {

    // The expected date format in JSON
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static List<Flights> getObjectFromJson(String json) {
        List<Flights> flights = new ArrayList<>();
        if (json == null || json.isEmpty()) {
            return flights; // Return empty list if JSON is null or empty
        }

        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject flightObject = jsonArray.getJSONObject(i);
                Flights flight = new Flights();

                flight.setFlightNumber(flightObject.optString("flight_number", "Unknown"));
                flight.setDepartureCity(flightObject.optString("departure_city", "Unknown"));
                flight.setArrivalCity(flightObject.optString("arrival_city", "Unknown"));

                // Parse dates using the expected format
                flight.setDepartureDate(parseDate(flightObject.optString("departure_date", "1970/01/01")));
                flight.setArrivalDate(parseDate(flightObject.optString("arrival_date", "1970/01/01")));
                flight.setBookingOpenDate(parseDate(flightObject.optString("booking_open_date", "1970/01/01")));

                System.out.println("***********************************");
                System.out.println(formatDate(flight.getDepartureDate()));
                System.out.println(formatDate(flight.getArrivalDate()));
                System.out.println(formatDate(flight.getBookingOpenDate()));
                System.out.println("***********************************");

                // Parse times using HH:mm
                flight.setDepartureTime(parseTime(flightObject.optString("departure_time", "00:00")));
                flight.setArrivalTime(parseTime(flightObject.optString("arrival_time", "00:00")));

                flight.setDuration(flightObject.optString("duration", "0h"));
                flight.setAircraftModel(flightObject.optString("aircraft_model", "Unknown"));
                flight.setMaxSeats(flightObject.optInt("max_seats", 0));
                flight.setCurrentReservations(flightObject.optInt("current_reservations", 0));
                flight.setPeopleMissed(flightObject.optInt("people_missed", 0));
                flight.setEconomyPrice(flightObject.optDouble("economy_price", 0.0));
                flight.setBusinessPrice(flightObject.optDouble("business_price", 0.0));
                flight.setExtraBaggagePrice(flightObject.optDouble("extra_baggage_price", 0.0));
                flight.setIsRecurrent(flightObject.optString("is_recurrent", "no"));

                flights.add(flight);
            }
        } catch (JSONException e) {
            e.printStackTrace(); // Log error message
        }
        return flights;
    }

    private static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (Exception e) {
            e.printStackTrace();
            return LocalDate.of(1970, 1, 1); // Return default date on error
        }
    }

    private static LocalTime parseTime(String timeString) {
        try {
            return LocalTime.parse(timeString, TIME_FORMATTER);
        } catch (Exception e) {
            e.printStackTrace();
            return LocalTime.MIDNIGHT; // Return default time on error
        }
    }

    // Method to format the date when printing
    private static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }
}
