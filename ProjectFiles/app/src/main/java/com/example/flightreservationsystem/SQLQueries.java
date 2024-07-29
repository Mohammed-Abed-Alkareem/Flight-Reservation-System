package com.example.flightreservationsystem;

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
                    "role TEXT NOT NULL, " +
                    ");";


    public static final String CREATE_PassengerDetails_TABLE =
            "CREATE TABLE PassengerDetails (" +
                    " user_id INT PRIMARY KEY, " +
                    "passport_number VARCHAR(50), " +
                    "passport_expiration_date DATE, " +
                    "food_preference VARCHAR(50), " +
                    "date_of_birth DATE, " +
                    "nationality VARCHAR(50), " +
                    "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE" +
                    ");";


    // Flight-related queries
    public static final String CREATE_FLIGHT_TABLE =
            "CREATE TABLE Flights (" +
                    "flight_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "flight_number TEXT UNIQUE NOT NULL, " +
                    "departure_city TEXT NOT NULL, " +
                    "arrival_city TEXT NOT NULL, " +
                    "departure_datetime DATETIME NOT NULL, " +
                    "arrival_datetime DATETIME NOT NULL, " +
                    "duration TEXT NOT NULL, " +
                    "aircraft_model TEXT, " +
                    "max_seats INTEGER NOT NULL, " +
                    "current_reservations INTEGER DEFAULT 0, " +
                    "booking_open_date DATE NOT NULL, " +
                    "economy_price REAL, " +
                    "business_price REAL, " +
                    "extra_baggage_price REAL, " +
                    "is_recurrent TEXT NOT NULL" +
                    ");";


    public static final String CREATE_Reservations_TABLE =
            "CREATE TABLE Reservations (" +
                    "reservation_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "flight_id INT, " +
                    "user_id INT, " +
                    "flight_class ENUM('Economy', 'Business') NOT NULL, " +
                    "extra_bags INT DEFAULT 0, " +
                    "reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (flight_id) REFERENCES Flights(flight_id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE " +
                    ");";

    public static final String INSERT_FLIGHT =
            "INSERT INTO Flights (flight_number, departure_city, arrival_city, departure_datetime, " +
                    "arrival_datetime, duration, aircraft_model, max_seats, booking_open_date, economy_price, " +
                    "business_price, extra_baggage_price, is_recurrent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String SELECT_FLIGHTS_BY_CITY_AND_DATE =
            "SELECT * FROM Flights WHERE departure_city = ? AND arrival_city = ? AND departure_datetime >= ?;";
}

