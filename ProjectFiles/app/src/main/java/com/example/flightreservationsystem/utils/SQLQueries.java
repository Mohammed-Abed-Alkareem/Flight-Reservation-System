package com.example.flightreservationsystem.utils;

public class SQLQueries {

    // User-related queries
    public static final String CREATE_USER_TABLE =
            "CREATE TABLE Users (" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "email TEXT UNIQUE NOT NULL, " +
                    "phone TEXT, " +
                    "first_name TEXT NOT NULL, " +
                    "last_name TEXT NOT NULL, " +
                    "password_hash TEXT NOT NULL, " +
                    "role TEXT NOT NULL" +
                    ");";



    public static final String CREATE_PASSENGER_DETAILS_TABLE =
            "CREATE TABLE PassengerDetails (" +
                    "user_id INTEGER PRIMARY KEY, " +
                    "passport_number TEXT, " +
                    "passport_issue_date DATE, " +
                    "passport_issue_place TEXT, " +
                    "passport_expiration_date DATE, " +
                    "food_preference TEXT, " +
                    "date_of_birth DATE, " +
                    "nationality TEXT, " +
                    "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE" +
                    ");";


    public static final String CREATE_FLIGHT_TABLE = "CREATE TABLE Flights (" +
            "flight_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "flight_number TEXT NOT NULL UNIQUE," +
            "departure_city TEXT NOT NULL," +
            "arrival_city TEXT NOT NULL," +
            "departure_date Date NOT NULL," +
            "arrival_date Date NOT NULL," +
            "departure_time TEXT NOT NULL," +
            "arrival_time TEXT NOT NULL," +
            "duration TEXT NOT NULL," +
            "aircraft_model TEXT NOT NULL," +
            "max_seats INTEGER NOT NULL," +
            "current_reservations INTEGER NOT NULL," +
            "people_missed INTEGER NOT NULL," +
            "booking_open_date TEXT NOT NULL," +
            "economy_price REAL NOT NULL," +
            "business_price REAL NOT NULL," +
            "extra_baggage_price REAL NOT NULL," +
            "is_recurrent TEXT NOT NULL" +
            ");";




    public static final String CREATE_RESERVATION_TABLE =
            "CREATE TABLE Reservations (" +
                    "reservation_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "flight_id INTEGER, " +
                    "user_id INTEGER, " +
                    "flight_class TEXT CHECK(flight_class IN ('Economy', 'Business')) NOT NULL, " +
                    "extra_bags INTEGER DEFAULT 0, " +
                    "total_price REAL NOT NULL, " +
                    "food_preferences TEXT NOT NULL"+
                    "reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (flight_id) REFERENCES Flights(flight_id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE" +
                    ");";

}
