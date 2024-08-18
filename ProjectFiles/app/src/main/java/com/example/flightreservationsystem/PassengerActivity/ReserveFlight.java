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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.flightreservationsystem.PassengerActivity.search.SearchFlightsActivity;
import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.models.Reservations;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.utils.DatabaseHelper;

public class ReserveFlight extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout home, search, reserve, current, previous, logout; // add others



    TextView passengerName;
    SharedPreferences preferences;

    DatabaseHelper databasehelper;

    EditText flightNumber, extraBag, classType, foodPreference;
    Button reserveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_flight);

        drawerLayout = findViewById(R.id.passenger_drawer_layout);
        menu = findViewById(R.id.menu_icon);

        final ImageView airplane = findViewById(R.id.airplane);

        home = findViewById(R.id.passenger_home);
        reserve = findViewById(R.id.make_reservation);
        search = findViewById(R.id.search_flight);
        current = findViewById(R.id.view_current_reservations);
        previous = findViewById(R.id.view_previous_reservations);
        logout = findViewById(R.id.logout);

        flightNumber = findViewById(R.id.flight_number);
        extraBag = findViewById(R.id.extra_bags);
        classType = findViewById(R.id.class_type);
        foodPreference = findViewById(R.id.food_preferences);
        reserveButton = findViewById(R.id.reserve);


        menu.setOnClickListener(v -> openDrawer(drawerLayout));




        home.setOnClickListener(v -> redirectActivity(ReserveFlight.this, PassengerHomeActivity.class));

        reserve.setOnClickListener(v -> recreate());
        search.setOnClickListener(v -> redirectActivity(ReserveFlight.this, SearchFlightsActivity.class));
        current.setOnClickListener(v -> redirectActivity(ReserveFlight.this, CurrentReservations.class));
        previous.setOnClickListener(v -> redirectActivity(ReserveFlight.this, PreviousReservations.class));

        logout.setOnClickListener(v -> {
            Toast.makeText(ReserveFlight.this, "Logged Out", Toast.LENGTH_SHORT).show();
            redirectActivity(ReserveFlight.this, LoginActivity.class);
        });

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

            try {
                String flight_number = flightNumber.getText().toString();
                int extra_bags = Integer.parseInt(extraBag.getText().toString());
                String class_type = classType.getText().toString();
                String food_preference = foodPreference.getText().toString();
                if (flight_number.isEmpty()) {
                    flightNumber.setError("Please enter a flight number");
                    return;
                }

                databasehelper = new DatabaseHelper(this, null, 1);
                Flights flight = databasehelper.getFlightByNumber(flight_number);

                System.out.println("====================================");
                System.out.println(flight);
                System.out.println(flight.getFlight_id());
                System.out.println("====================================");

                if (flight == null) {
                    flightNumber.setError("Flight not found");
                    return;
                }

                if (extra_bags < 0) {
                    extraBag.setError("Please enter a valid number of extra bags");
                    return;
                }

                if (!class_type.equals("Economy") && !class_type.equals("Business")) {
                    classType.setError("Please enter a valid class type");
                    return;
                }

                Reservations reservation = new Reservations();
                reservation.setFlightID(flight.getFlight_id());
                reservation.setUserID(preferences.getInt("userId", 0));
                reservation.setExtraBags(extra_bags);
                reservation.setClassType(class_type);
                reservation.setFoodPreference(food_preference);

                double total_price = 0;
                if (class_type.equals("Economy")) {
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

                Toast.makeText(this, "Reservation Successful", Toast.LENGTH_SHORT).show();

            }
            catch (Exception e) {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }


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



