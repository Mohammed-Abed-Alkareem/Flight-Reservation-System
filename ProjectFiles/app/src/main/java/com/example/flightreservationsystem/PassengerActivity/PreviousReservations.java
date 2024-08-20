package com.example.flightreservationsystem.PassengerActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightreservationsystem.PassengerActivity.search.SearchFlightsActivity;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.models.Reservations;
import com.example.flightreservationsystem.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class PreviousReservations extends AppCompatActivity {
    // Drawer Variables
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, search, reserve, current, previous, logout; // add others

    //layout variables
    TextView passengerName;

    //shared preferences
    SharedPreferences preferences;

    //Database Helper
    DatabaseHelper databasehelper;

    // Recycler View Variables
    private RecyclerView recyclerView; // RecyclerView object
    private ReservationAdapter reservationAdapter; // Adapter object
    private List<Reservations> reservationList; // List of reservations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_rservations);

        // Initialize Drawer Variables
        drawerLayout = findViewById(R.id.passenger_drawer_layout);
        menu = findViewById(R.id.menu_icon);
        home = findViewById(R.id.passenger_home);
        reserve = findViewById(R.id.make_reservation);
        search = findViewById(R.id.search_flight);
        current = findViewById(R.id.view_current_reservations);
        previous = findViewById(R.id.view_previous_reservations);
        logout = findViewById(R.id.logout);

        // drawer layout functionality
        menu.setOnClickListener(v -> openDrawer(drawerLayout));
        home.setOnClickListener(v -> redirectActivity(PreviousReservations.this, PassengerHomeActivity.class));
        reserve.setOnClickListener(v -> redirectActivity(PreviousReservations.this, ReserveFlight.class));
        search.setOnClickListener(v -> redirectActivity(PreviousReservations.this, SearchFlightsActivity.class));
        current.setOnClickListener(v -> redirectActivity(PreviousReservations.this, CurrentReservations.class));
        previous.setOnClickListener(v -> recreate());

        logout.setOnClickListener(v -> {
            Toast.makeText(PreviousReservations.this, "Logged Out", Toast.LENGTH_SHORT).show();
            redirectActivity(PreviousReservations.this, LoginActivity.class);
        });

        ///////////////////drawer name and email///////////////////////////////
        TextView email = findViewById(R.id.passenger_email);
        //fetch email from shared preferences
        preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        email.setText(preferences.getString("userEmail", ""));

        passengerName = findViewById(R.id.passenger_name);
        //fetch naeme from shared preferences
        preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        passengerName.setText(preferences.getString("userFirstName", ""));


        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Enables vertical scrolling

        reservationList = new ArrayList<>();

        //get userid from shared preferences
        preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        int userId = preferences.getInt("userId", 0);

        // Get the reservations from the database
        databasehelper = new DatabaseHelper(this, null, 1);
        reservationList = databasehelper.getPreviousReservationsByUserId(userId);

        if (reservationList.isEmpty()) { // If there are no reservations
            Toast.makeText(this, "No Current Reservations For You *_*", Toast.LENGTH_SHORT).show();
        }

        // Set the adapter with reservationList
        reservationAdapter = new ReservationAdapter(this, reservationList);
        recyclerView.setAdapter(reservationAdapter);  // Set the adapter for RecyclerView

    }

    // Drawer Functions
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // Redirect Activity Function
    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    // Close Drawer on Pause
    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}