package com.example.flightreservationsystem.PassengerActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.flightreservationsystem.PassengerActivity.search.SearchFlightsActivity;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;

public class PassengerHomeActivity extends AppCompatActivity {

    // Drawer Variables
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, search, reserve, current, previous, logout; // add others

    // Menu Button
    Button menuButton;

    // Layout Variables
    TextView passengerName;

    // Shared Preferences
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home);

        // Initialize Drawer Variables
        drawerLayout = findViewById(R.id.passenger_drawer_layout);

        menu = findViewById(R.id.menu_icon);
        home = findViewById(R.id.passenger_home);
        reserve = findViewById(R.id.make_reservation);
        search = findViewById(R.id.search_flight);
        current = findViewById(R.id.view_current_reservations);
        previous = findViewById(R.id.view_previous_reservations);
        logout = findViewById(R.id.logout);

        // Drawer Functionality
        menu.setOnClickListener(v -> openDrawer(drawerLayout));
        home.setOnClickListener(v -> recreate());
        search.setOnClickListener(v -> redirectActivity(PassengerHomeActivity.this, SearchFlightsActivity.class));
        reserve.setOnClickListener(v -> redirectActivity(PassengerHomeActivity.this, ReserveFlight.class));
        current.setOnClickListener(v -> redirectActivity(PassengerHomeActivity.this, CurrentReservations.class));
        previous.setOnClickListener(v -> redirectActivity(PassengerHomeActivity.this, PreviousReservations.class));

        logout.setOnClickListener(v -> {
            Toast.makeText(PassengerHomeActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            redirectActivity(PassengerHomeActivity.this, LoginActivity.class);
        });

        // Menu Button Functionality
        menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(v -> openDrawer(drawerLayout));

        passengerName = findViewById(R.id.passenger_name);
        //fetch naeme from shared preferences
        preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        passengerName.setText(preferences.getString("userFirstName", ""));


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