package com.example.flightreservationsystem.RestApi;

import com.example.flightreservationsystem.Classes.Flights;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FlightJsonParser {

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
                flight.setDepartureDate(LocalDate.parse(flightObject.optString("departure_date", "1970-01-01")));
                flight.setArrivalDate(LocalDate.parse(flightObject.optString("arrival_date", "1970-01-01")));
                flight.setDepartureTime(LocalTime.parse(flightObject.optString("departure_time", "00:00:00")));
                flight.setArrivalTime(LocalTime.parse(flightObject.optString("arrival_time", "00:00:00")));
                flight.setDuration(flightObject.optString("duration", "0h"));
                flight.setAircraftModel(flightObject.optString("aircraft_model", "Unknown"));
                flight.setMaxSeats(flightObject.optInt("max_seats", 0));
                flight.setCurrentReservations(flightObject.optInt("current_reservations", 0));
                flight.setPeopleMissed(flightObject.optInt("people_missed", 0));
                flight.setBookingOpenDate(LocalDate.parse(flightObject.optString("booking_open_date", "1970-01-01")));
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
}
