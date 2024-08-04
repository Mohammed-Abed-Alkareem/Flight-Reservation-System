package com.example.flightreservationsystem.AdminHomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;

public class ScheduleFlightActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout home, schedule , edit , open , unavailable, archive ,reservation ,filter , logout;

    Button scheduleFlightButton;

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

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(ScheduleFlightActivity.this, AdminHomeActivity.class);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               recreate();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleFlightActivity.this, EditFlightActivity.class));
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleFlightActivity.this, ViewOpenFlightsActivity.class));
            }
        });

        unavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleFlightActivity.this, ViewUnavailableActivity.class));
            }
        });

        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleFlightActivity.this, ViewFlightsArchiveActivity.class));
            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleFlightActivity.this, ViewReservationsActivity.class));
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScheduleFlightActivity.this, FilterFlightsActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScheduleFlightActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ScheduleFlightActivity.this, LoginActivity.class));
            }
        });


        ////////////////////////////////////

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

        scheduleFlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               boolean isValid = true;

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


                // set error to null
                flightNumberEditText.setError(null);
                departureCityEditText.setError(null);
                arrivalCityEditText.setError(null);
                departureDateEditText.setError(null);
                departureTimeEditText.setError(null);
                arrivalDateEditText.setError(null);
                arrivalTimeEditText.setError(null);
                durationEditText.setError(null);
                aircraftModelEditText.setError(null);
                maxSeatsEditText.setError(null);
                bookingOpenDateEditText.setError(null);
                economyPriceEditText.setError(null);
                businessPriceEditText.setError(null);
                extraBaggagePriceEditText.setError(null);
                isRecurrentEditText.setError(null);


//                if (flightNumber.isEmpty()) {
//                    flightNumberEditText.setError("Flight Number is required");
//                    flightNumberEditText.requestFocus();
//                    return;
//                }
//
//                if (departureCity.isEmpty()) {
//                    departureCityEditText.setError("Departure City is required");
//                    departureCityEditText.requestFocus();
//                    return;
//                }
//
//                if (arrivalCity.isEmpty()) {
//                    arrivalCityEditText.setError("Arrival City is required");
//                    arrivalCityEditText.requestFocus();
//                    return;
//                }
//


            }
        });





//        values.put("flight_number", flightNumber);
//        values.put("departure_city", departureCity);
//        values.put("arrival_city", arrivalCity);
//        values.put("departure_date", departureDate);
//        values.put("departure_time", departureTime);
//        values.put("arrival_date", arrivalDate);
//        values.put("arrival_time", arrivalTime);
//        values.put("duration", duration);
//        values.put("aircraft_model", aircraftModel);
//        values.put("max_seats", maxSeats);
//        values.put("booking_open_date", bookingOpenDate);
//        values.put("economy_price", economyPrice);
//        values.put("business_price", businessPrice);
//        values.put("extra_baggage_price", extraBaggagePrice);
//        values.put("is_recurrent", isRecurrent);

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