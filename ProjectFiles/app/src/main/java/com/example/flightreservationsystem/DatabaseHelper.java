package com.example.flightreservationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.flightreservationsystem.Classes.Admin;
import com.example.flightreservationsystem.Classes.Flights;
import com.example.flightreservationsystem.Classes.Passenger;
import com.example.flightreservationsystem.Classes.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FlightReservationSystem.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    public DatabaseHelper(Context context, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, version);
        this.context = context; // Initialize the context
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQLQueries.CREATE_USER_TABLE);
            db.execSQL(SQLQueries.CREATE_FLIGHT_TABLE);
            db.execSQL(SQLQueries.CREATE_RESERVATION_TABLE);
            db.execSQL(SQLQueries.CREATE_PASSENGER_DETAILS_TABLE);
        } catch (Exception e) {
            Toast.makeText(context, "Error creating tables: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Flights");
        db.execSQL("DROP TABLE IF EXISTS Reservations");
        db.execSQL("DROP TABLE IF EXISTS PassengerDetails");
        onCreate(db);
    }

    public void insertPassengerDetails(Passenger passenger) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction(); // Start transaction

        try {
            // Insert the user
            ContentValues userValues = new ContentValues();
            userValues.put("email", passenger.getEmail());
            userValues.put("phone", passenger.getPhone());
            userValues.put("first_name", passenger.getFirst_name());
            userValues.put("last_name", passenger.getLast_name());
            userValues.put("password_hash", passenger.getPassword_hash());
            userValues.put("role", passenger.getRole());

            long userId = db.insert("Users", null, userValues);

            if (userId == -1) {
                throw new Exception("Failed to insert user.");
            }

            // Insert passenger details
            ContentValues passengerValues = new ContentValues();
            passengerValues.put("user_id", userId);
            passengerValues.put("passport_number", passenger.getPassport_number());
            passengerValues.put("passport_issue_date", passenger.getPassport_issue_date());
            passengerValues.put("passport_issue_place", passenger.getPassport_issue_place());
            passengerValues.put("food_preference", passenger.getFood_preference());
            passengerValues.put("date_of_birth", passenger.getDate_of_birth());
            passengerValues.put("nationality", passenger.getNationality());

            long passengerId = db.insert("PassengerDetails", null, passengerValues);

            if (passengerId == -1) {
                throw new Exception("Failed to insert passenger details.");
            }

            System.out.println("Passenger details inserted successfully"
                    + "\nEmail: " + passenger.getEmail()
                    + "\nPhone: " + passenger.getPhone()
                    + "\nFirst Name: " + passenger.getFirst_name()
                    + "\nLast Name: " + passenger.getLast_name()
                    + "\nPassword: " + passenger.getPassword_hash()
                    + "\nPassport Number: " + passenger.getPassport_number()
                    + "\nPassport Issue Date: " + passenger.getPassport_issue_date()
                    + "\nPassport Issue Place: " + passenger.getPassport_issue_place()
                    + "\nFood Preference: " + passenger.getFood_preference()
                    + "\nDate of Birth: " + passenger.getDate_of_birth()
                    + "\nNationality: " + passenger.getNationality());

            // If both inserts are successful, set transaction as successful
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Handle errors and rollback transaction
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.endTransaction(); // End transaction
            db.close(); // Close database
        }
    }
    public boolean insertAdmin(Admin admin) {
        SQLiteDatabase db = null;
        boolean success = false;

        try {
            db = this.getWritableDatabase();
            db.beginTransaction(); // Start transaction

            // Insert the user
            ContentValues userValues = new ContentValues();
            userValues.put("email", admin.getEmail());
            userValues.put("phone", admin.getPhone());
            userValues.put("first_name", admin.getFirst_name());
            userValues.put("last_name", admin.getLast_name());
            userValues.put("password_hash", admin.getPassword_hash());
            userValues.put("role", admin.getRole());

            long userId = db.insert("Users", null, userValues);

            if (userId == -1) {
                throw new Exception("Failed to insert user.");
            }

            // If insert is successful, set transaction as successful
            db.setTransactionSuccessful();
            success = true;
        } catch (Exception e) {
            // Handle errors and rollback transaction
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null) {
                db.endTransaction(); // End transaction
                db.close(); // Close database
            }
        }
        return success;
    }

    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email = ?", new String[]{email});
        Log.d("DatabaseHelper", "Querying Users with email: " + email);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int roleIdx = cursor.getColumnIndex("role");

                    if (roleIdx != -1) {
                        String role = cursor.getString(roleIdx);
                        Log.d("DatabaseHelper", "User role: " + role);

                        if ("admin".equals(role)) {
                            return new Admin(
                                    Integer.parseInt(getColumnValue(cursor, "user_id")),
                                    getColumnValue(cursor, "email"),
                                    getColumnValue(cursor, "phone"),
                                    getColumnValue(cursor, "first_name"),
                                    getColumnValue(cursor, "last_name"),
                                    getColumnValue(cursor, "password_hash"),
                                    getColumnValue(cursor, "role")
                            );
                        } else {
                            // Fetch PassengerDetails for this user
                            String userId = getColumnValue(cursor, "user_id");
                            Cursor cursor2 = db.rawQuery("SELECT * FROM PassengerDetails WHERE user_id = ?", new String[]{userId});
                            Log.d("DatabaseHelper", "Querying PassengerDetails with user_id: " + userId);

                            if (cursor2 != null) {
                                try {
                                    if (cursor2.moveToFirst()) {
                                        return new Passenger(
                                                Integer.parseInt(getColumnValue(cursor, "user_id")),
                                                getColumnValue(cursor, "email"),
                                                getColumnValue(cursor, "phone"),
                                                getColumnValue(cursor, "first_name"),
                                                getColumnValue(cursor, "last_name"),
                                                getColumnValue(cursor, "password_hash"),
                                                getColumnValue(cursor, "role"),
                                                getColumnValue(cursor2, "passport_number"),
                                                getColumnValue(cursor2, "passport_issue_date"),
                                                getColumnValue(cursor2, "passport_issue_place"),
                                                getColumnValue(cursor2, "food_preference"),
                                                getColumnValue(cursor2, "date_of_birth"),
                                                getColumnValue(cursor2, "nationality")
                                        );
                                    } else {
                                        Log.e("DatabaseHelper", "No rows returned for PassengerDetails with user_id: " + userId);
                                    }
                                } finally {
                                    cursor2.close();
                                }
                            } else {
                                Log.e("DatabaseHelper", "Failed to query PassengerDetails with user_id: " + userId);
                            }
                        }
                    } else {
                        Log.e("DatabaseHelper", "'role' column not found in Users table.");
                    }
                } else {
                    Log.e("DatabaseHelper", "No rows returned for Users with email: " + email);
                }
            } finally {
                cursor.close();
            }
        } else {
            Log.e("DatabaseHelper", "Failed to query Users with email: " + email);
        }

        db.close();
        return null;
    }

    private String getColumnValue(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1) {
            return cursor.getString(columnIndex);
        } else {
            Log.e("DatabaseHelper", "Column " + columnName + " not found.");
            return null;
        }
    }

    public void insertFlight(Flights flight) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction(); // Start transaction

        try {
            ContentValues flightValues = new ContentValues();
            flightValues.put("flight_number", flight.getFlightNumber());
            flightValues.put("departure_city", flight.getDepartureCity());
            flightValues.put("arrival_city", flight.getArrivalCity());
            flightValues.put("departure_date", flight.getDepartureDate().toString());
            flightValues.put("arrival_date", flight.getArrivalDate().toString());
            flightValues.put("departure_time", flight.getDepartureTime().toString());

            flightValues.put("arrival_time", flight.getArrivalTime().toString());
            flightValues.put("duration", flight.getDuration());
            flightValues.put("aircraft_model", flight.getAircraftModel());
            flightValues.put("max_seats", flight.getMaxSeats());
            flightValues.put("current_reservations", flight.getCurrentReservations());
            flightValues.put("people_missed", flight.getPeopleMissed());
            flightValues.put("booking_open_date", flight.getBookingOpenDate().toString());
            flightValues.put("economy_price", flight.getEconomyPrice());
            flightValues.put("business_price", flight.getBusinessPrice());
            flightValues.put("extra_baggage_price", flight.getExtraBaggagePrice());
            flightValues.put("is_recurrent", flight.getIsRecurrent());

            long flightId = db.insert("Flights", null, flightValues);

            if (flightId == -1) {
                throw new Exception("Failed to insert flight.");
            }

            System.out.println("Flight inserted successfully"
                    + "\nFlight Number: " + flight.getFlightNumber()
                    + "\nDeparture City: " + flight.getDepartureCity()
                    + "\nArrival City: " + flight.getArrivalCity()
                    + "\nDeparture Date: " + flight.getDepartureDate()
                    + "\nDeparture Time: " + flight.getDepartureTime()
                    + "\nArrival Date: " + flight.getArrivalDate()
                    + "\nArrival Time: " + flight.getArrivalTime()
                    + "\nDuration: " + flight.getDuration()
                    + "\nAircraft Model: " + flight.getAircraftModel()
                    + "\nMax Seats: " + flight.getMaxSeats()
                    + "\nCurrent Reservations: " + flight.getCurrentReservations()
                    + "\nPeople Missed: " + flight.getPeopleMissed()
                    + "\nBooking Open Date: " + flight.getBookingOpenDate()
                    + "\nEconomy Price: " + flight.getEconomyPrice()
                    + "\nBusiness Price: " + flight.getBusinessPrice()
                    + "\nExtra Baggage Price: " + flight.getExtraBaggagePrice()
                    + "\nIs Recurrent: " + flight.getIsRecurrent());

    } catch (Exception e) {
            // Handle errors and rollback transaction
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.endTransaction(); // End transaction
            db.close(); // Close database
        }

    }
    }
