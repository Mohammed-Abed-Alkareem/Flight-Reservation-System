package com.example.flightreservationsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.flightreservationsystem.Classes.Admin;
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
            passengerValues.put("passport_expiration_date", passenger.getPassport_expiration_date());
            passengerValues.put("food_preference", passenger.getFood_preference());
            passengerValues.put("date_of_birth", passenger.getDate_of_birth());
            passengerValues.put("nationality", passenger.getNationality());

            long passengerId = db.insert("PassengerDetails", null, passengerValues);

            if (passengerId == -1) {
                throw new Exception("Failed to insert passenger details.");
            }

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
    public void insertAdmin(Admin admin) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction(); // Start transaction

        try {
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
    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE email = ?", new String[]{email});

        //check role
        if (cursor == null) {
            return null;
        }

        cursor.moveToFirst();
        int idx = cursor.getColumnIndex("role");

        if (idx == -1) {
            return null;
        }

        String role = cursor.getString(idx);

        if (role.equals("admin")) {
            return  new Admin(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        } else {
            return  new Passenger(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));

        }


    }
}

