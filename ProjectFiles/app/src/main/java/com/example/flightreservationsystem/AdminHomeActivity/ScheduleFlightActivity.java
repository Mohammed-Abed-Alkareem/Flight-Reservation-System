package com.example.flightreservationsystem.AdminHomeActivity;

import static com.example.flightreservationsystem.models.Validation.isValidAirCraftModel;
import static com.example.flightreservationsystem.models.Validation.isValidDate;
import static com.example.flightreservationsystem.models.Validation.isValidDuration;
import static com.example.flightreservationsystem.models.Validation.isValidFlightNumber;
import static com.example.flightreservationsystem.models.Validation.isValidName;
import static com.example.flightreservationsystem.models.Validation.isValidRecurrence;
import static com.example.flightreservationsystem.models.Validation.isValidTime;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.flightreservationsystem.AdminHomeActivity.Archived.ViewArchiveActivity;
import com.example.flightreservationsystem.AdminHomeActivity.Open.ViewOpenActivity;
import com.example.flightreservationsystem.AdminHomeActivity.filter.FilterFlightsActivity;
import com.example.flightreservationsystem.AdminHomeActivity.reser.ViewReservations;
import com.example.flightreservationsystem.AdminHomeActivity.unava.ViewUnavailableActivity;
import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.utils.DatabaseHelper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class ScheduleFlightActivity extends AppCompatActivity {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout home, schedule, edit, open, unavailable, archive, reservation, filter, logout;

    Button scheduleFlightButton;

    DatabaseHelper databaseHelper;

    EditText flightNumberEditText, departureCityEditText,
            arrivalCityEditText, departureDateEditText,
            departureTimeEditText, arrivalDateEditText,
            arrivalTimeEditText, durationEditText,
            aircraftModelEditText, maxSeatsEditText,
            bookingOpenDateEditText, economyPriceEditText,
            businessPriceEditText, extraBaggagePriceEditText,
            isRecurrentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_flight);

        databaseHelper = new DatabaseHelper(this, null, 1);

        drawerLayout = findViewById(R.id.admin_drawer_layout);
        menu = findViewById(R.id.menu_icon);

        home = findViewById(R.id.admin_home);
        schedule = findViewById(R.id.schedule_flight);
        edit = findViewById(R.id.edit_flight);
        open = findViewById(R.id.view_open_flights);
        unavailable = findViewById(R.id.view_unavailable_flights);
        archive = findViewById(R.id.view_flights_archive);
        reservation = findViewById(R.id.view_reservations);
        filter = findViewById(R.id.filter_flights);
        logout = findViewById(R.id.logout);

        menu.setOnClickListener(v -> openDrawer(drawerLayout));

        home.setOnClickListener(v -> redirectActivity(ScheduleFlightActivity.this, AdminHomeActivity.class));

        schedule.setOnClickListener(v -> recreate());

        edit.setOnClickListener(v -> startActivity(new Intent(ScheduleFlightActivity.this, EditFlightActivity.class)));

        open.setOnClickListener(v -> startActivity(new Intent(ScheduleFlightActivity.this, ViewOpenActivity.class)));

        unavailable.setOnClickListener(v -> startActivity(new Intent(ScheduleFlightActivity.this, ViewUnavailableActivity.class)));

        archive.setOnClickListener(v -> startActivity(new Intent(ScheduleFlightActivity.this, ViewArchiveActivity.class)));

        reservation.setOnClickListener(v -> startActivity(new Intent(ScheduleFlightActivity.this, ViewReservations.class)));

        filter.setOnClickListener(v -> startActivity(new Intent(ScheduleFlightActivity.this, FilterFlightsActivity.class)));

        logout.setOnClickListener(v -> {
            Toast.makeText(ScheduleFlightActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ScheduleFlightActivity.this, LoginActivity.class));
        });

        // Initialize EditText fields
        flightNumberEditText = findViewById(R.id.flight_number_edit_text);
        departureCityEditText = findViewById(R.id.departure_city_edit_text);
        arrivalCityEditText = findViewById(R.id.arrival_city_edit_text);
        departureDateEditText = findViewById(R.id.departure_date_edit_text);
        departureTimeEditText = findViewById(R.id.departure_time_edit_text);
        arrivalDateEditText = findViewById(R.id.arrival_date_edit_text);
        arrivalTimeEditText = findViewById(R.id.arrival_time_edit_text);
        durationEditText = findViewById(R.id.duration_edit_text);
        aircraftModelEditText = findViewById(R.id.aircraft_model_edit_text);
        maxSeatsEditText = findViewById(R.id.maximum_seats_edit_text);
        bookingOpenDateEditText = findViewById(R.id.booking_open_date_edit_text);
        economyPriceEditText = findViewById(R.id.price_economy_class_edit_text);
        businessPriceEditText = findViewById(R.id.price_business_class_edit_text);
        extraBaggagePriceEditText = findViewById(R.id.price_extra_baggage_edit_text);
        isRecurrentEditText = findViewById(R.id.is_recurrent_edit_text);

        scheduleFlightButton = findViewById(R.id.schedule_button);

        // Date and Time Pickers
        departureDateEditText.setOnClickListener(v -> showDatePicker(departureDateEditText));
        arrivalDateEditText.setOnClickListener(v -> showDatePicker(arrivalDateEditText));
        bookingOpenDateEditText.setOnClickListener(v -> showDatePicker(bookingOpenDateEditText));

        departureTimeEditText.setOnClickListener(v -> showTimePicker(departureTimeEditText));
        arrivalTimeEditText.setOnClickListener(v -> showTimePicker(arrivalTimeEditText));

        scheduleFlightButton.setOnClickListener(v -> {
            String flightNumber = flightNumberEditText.getText().toString();
            String departureCity = departureCityEditText.getText().toString();
            String arrivalCity = arrivalCityEditText.getText().toString();
            String departureDate = departureDateEditText.getText().toString();
            String departureTime = departureTimeEditText.getText().toString();
            String arrivalDate = arrivalDateEditText.getText().toString();
            String arrivalTime = arrivalTimeEditText.getText().toString();
            String duration = durationEditText.getText().toString();
            String aircraftModel = aircraftModelEditText.getText().toString();
            String maxSeats = maxSeatsEditText.getText().toString();
            String bookingOpenDate = bookingOpenDateEditText.getText().toString();
            String economyPrice = economyPriceEditText.getText().toString();
            String businessPrice = businessPriceEditText.getText().toString();
            String extraBaggagePrice = extraBaggagePriceEditText.getText().toString();
            String isRecurrent = isRecurrentEditText.getText().toString();




            // If all inputs are valid, schedule the flight
            if (validateInputs(flightNumber, departureCity, arrivalCity, departureDate, departureTime,
                    arrivalDate, arrivalTime, duration, aircraftModel, maxSeats, bookingOpenDate,
                    economyPrice, businessPrice, extraBaggagePrice, isRecurrent)) {
                // Schedule the flight
                Toast.makeText(ScheduleFlightActivity.this, "Flight Scheduled", Toast.LENGTH_SHORT).show();
                Flights flight = new Flights();
                flight.setFlightNumber(flightNumber);
                flight.setDepartureCity(departureCity);
                flight.setArrivalCity(arrivalCity);


                flight.setDepartureTime(LocalTime.parse(departureTime));


                flight.setArrivalTime(LocalTime.parse(arrivalTime));

                flight.setDuration(duration);
                flight.setAircraftModel(aircraftModel);
                flight.setMaxSeats(Integer.parseInt(maxSeats));
                flight.setCurrentReservations(0);
                flight.setPeopleMissed(0);
                flight.setDepartureDate(LocalDate.parse(departureDate, dateFormatter));
                flight.setArrivalDate(LocalDate.parse(arrivalDate, dateFormatter));
                flight.setBookingOpenDate(LocalDate.parse(bookingOpenDate, dateFormatter));

                flight.setEconomyPrice(Double.parseDouble(economyPrice));
                flight.setBusinessPrice(Double.parseDouble(businessPrice));
                flight.setExtraBaggagePrice(Double.parseDouble(extraBaggagePrice));
                flight.setIsRecurrent(isRecurrent);

                databaseHelper.insertFlight(flight);

                // Clear all EditText fields
                flightNumberEditText.setText("");
                departureCityEditText.setText("");
                arrivalCityEditText.setText("");
                departureDateEditText.setText("");
                departureTimeEditText.setText("");
                arrivalDateEditText.setText("");
                arrivalTimeEditText.setText("");
                durationEditText.setText("");
                aircraftModelEditText.setText("");
                maxSeatsEditText.setText("");
                bookingOpenDateEditText.setText("");
                economyPriceEditText.setText("");
                businessPriceEditText.setText("");
                extraBaggagePriceEditText.setText("");
                isRecurrentEditText.setText("");





            }



        });
    }

    private void showDatePicker(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth,selectedDay ) ->
                        editText.setText(String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)),
                year, month, day);

        datePickerDialog.show();
    }

    private void showTimePicker(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, selectedHour, selectedMinute) ->
                        editText.setText(String.format("%02d:%02d", selectedHour, selectedMinute)),
                hour, minute, true);

        timePickerDialog.show();
    }

    private boolean validateInputs(String flightNumber, String departureCity, String arrivalCity,
                                   String departureDate, String departureTime, String arrivalDate,
                                   String arrivalTime, String duration, String aircraftModel,
                                   String maxSeats, String bookingOpenDate, String economyPrice,
                                   String businessPrice, String extraBaggagePrice, String isRecurrent) {



        boolean isValid = true;

        if(isValidFlightNumber(flightNumber)) {
            flightNumberEditText.setError(null);
        } else {
            flightNumberEditText.setError("Invalid flight number");
            isValid = false;
        }

        if(isValidName(departureCity)) {
            departureCityEditText.setError(null);
        } else {
            departureCityEditText.setError("Invalid departure city");
            isValid = false;
        }

        if(isValidName(arrivalCity)) {
            arrivalCityEditText.setError(null);
        } else {
            arrivalCityEditText.setError("Invalid arrival city");
            isValid = false;
        }

        if(isValidDate(departureDate)) {
            departureDateEditText.setError(null);
        } else {
            departureDateEditText.setError("Invalid departure date");
            isValid = false;
        }

        if(isValidDate(arrivalDate)) {
            arrivalDateEditText.setError(null);
        } else {
            arrivalDateEditText.setError("Invalid arrival date");
            isValid = false;
        }

        if(isValidTime(departureTime)) {
            departureTimeEditText.setError(null);
        } else {
            departureTimeEditText.setError("Invalid departure time");
            isValid = false;
        }

        if(isValidTime(arrivalTime)) {
            arrivalTimeEditText.setError(null);
        } else {
            arrivalTimeEditText.setError("Invalid arrival time");
            isValid = false;
        }

        if(isValidDuration(duration)) {
            durationEditText.setError(null);
        } else {
            durationEditText.setError("Invalid duration");
            isValid = false;
        }

        if (isValidAirCraftModel(aircraftModel)) {
            aircraftModelEditText.setError(null);
        } else {
            aircraftModelEditText.setError("Invalid aircraft model");
            isValid = false;
        }

        if (maxSeats.matches("^\\d{1,3}$")) {
            maxSeatsEditText.setError(null);
        } else {
            maxSeatsEditText.setError("Invalid maximum seats");
            isValid = false;
        }

        if (isValidDate(bookingOpenDate)) {
            bookingOpenDateEditText.setError(null);
        } else {
            bookingOpenDateEditText.setError("Invalid booking open date");
            isValid = false;
        }

        if (economyPrice.matches("^\\d+(\\.\\d{1,2})?$")) {
            economyPriceEditText.setError(null);
        } else {
            economyPriceEditText.setError("Invalid economy price");
            isValid = false;
        }

        if (businessPrice.matches("^\\d+(\\.\\d{1,2})?$")) {
            businessPriceEditText.setError(null);
        } else {
            businessPriceEditText.setError("Invalid business price");
            isValid = false;
        }

        if (extraBaggagePrice.matches("^\\d+(\\.\\d{1,2})?$")) {
            extraBaggagePriceEditText.setError(null);
        } else {
            extraBaggagePriceEditText.setError("Invalid extra baggage price");
            isValid = false;
        }

        if(isValidRecurrence(isRecurrent)) {
            isRecurrentEditText.setError(null);
        } else {
            isRecurrentEditText.setError("Invalid recurrence");
            isValid = false;
        }

        return isValid;

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
