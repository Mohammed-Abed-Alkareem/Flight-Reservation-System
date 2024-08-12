package com.example.flightreservationsystem.PassengerActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.models.Reservations;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.utils.DatabaseHelper;

public class ReserveFlight extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout home, search, reserve, current, previous; // add others

    Button menuButton;

    TextView passengerName;
    SharedPreferences preferences;

    DatabaseHelper databasehelper;

    EditText flightNumber, extraBag, classType, foodPreference;
    Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);

        drawerLayout = findViewById(R.id.passenger_drawer_layout);
        menu = findViewById(R.id.menu_icon);

        menuButton = findViewById(R.id.menu);

        home = findViewById(R.id.passenger_home);
        reserve = findViewById(R.id.make_reservation);
        search = findViewById(R.id.search_flight);
        current = findViewById(R.id.view_current_reservations);
        previous = findViewById(R.id.view_previous_reservations);

        flightNumber = findViewById(R.id.flight_number);
        extraBag = findViewById(R.id.extra_bags);
        classType = findViewById(R.id.class_type);
        foodPreference = findViewById(R.id.food_preferences);
        reserveButton = findViewById(R.id.reserve);


        menu.setOnClickListener(v -> openDrawer(drawerLayout));

        menuButton.setOnClickListener(v -> openDrawer(drawerLayout));


        home.setOnClickListener(v -> redirectActivity(ReserveFlight.this, PassengerHomeActivity.class));

        reserve.setOnClickListener(v -> recreate());


        ///////////////////drawer name and email///////////////////////////////
        TextView email = findViewById(R.id.passenger_email);
        //fetch email from shared preferences
        preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        email.setText(preferences.getString("userEmail", ""));
        //////////////////////////////////////

        /////////////////////////////////

        passengerName = findViewById(R.id.passenger_name);
        //fetch naeme from shared preferences
        preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        passengerName.setText(preferences.getString("userFirstName", ""));

        reserveButton.setOnClickListener(v -> {
            String flight_number = flightNumber.getText().toString();
            Integer extra_bags = Integer.parseInt(extraBag.getText().toString());
            String class_type = classType.getText().toString();
            String food_preference = foodPreference.getText().toString();
            if (flight_number.isEmpty()) {
                flightNumber.setError("Please enter a flight number");
                return;
            }

            databasehelper = new DatabaseHelper(this, null, 1);
            Flights flight = databasehelper.getFlightByNumber(flight_number);

            if (flight == null) {
                flightNumber.setError("Flight not found");
                return;
            }

            if (extra_bags < 0) {
                extraBag.setError("Please enter a valid number of extra bags");
                return;
            }

            if (!class_type.equals("economy") && !class_type.equals("business")) {
                classType.setError("Please enter a valid class type");
                return;
            }

            Reservations reservation = new Reservations();
            reservation.setFlightID(flight.getFlight_id());
            reservation.setUserID(preferences.getInt("userId", 0));
            reservation.setExtraBaggage(extra_bags);
            reservation.setClassType(class_type);
            reservation.setFoodPreference(food_preference);

            double total_price = 0;
            if (class_type.equals("economy")) {
                total_price = flight.getEconomyPrice();
            } else {
                total_price = flight.getBusinessPrice();
            }

            total_price += extra_bags * flight.getExtraBaggagePrice();

            reservation.setTotalPrice(total_price);


            databasehelper.addReservation(reservation);

            flight.setCurrentReservations(flight.getCurrentReservations() + 1);

            databasehelper.updateFlight(flight);

            flightNumber.setText("");
            extraBag.setText("");
            classType.setText("");
            foodPreference.setText("");

            flightNumber.setError(null);
            extraBag.setError(null);
            classType.setError(null);
            foodPreference.setError(null);





        });


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



