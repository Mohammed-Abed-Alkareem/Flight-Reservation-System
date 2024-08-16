package com.example.flightreservationsystem.AdminHomeActivity.reser;

import android.app.Activity;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity;
import com.example.flightreservationsystem.AdminHomeActivity.Archived.ViewArchiveActivity;
import com.example.flightreservationsystem.AdminHomeActivity.EditFlightActivity;
import com.example.flightreservationsystem.AdminHomeActivity.Open.OpenAdapter;
import com.example.flightreservationsystem.AdminHomeActivity.Open.ViewOpenActivity;
import com.example.flightreservationsystem.AdminHomeActivity.ScheduleFlightActivity;
import com.example.flightreservationsystem.AdminHomeActivity.filter.FilterFlightsActivity;
import com.example.flightreservationsystem.AdminHomeActivity.unava.ViewUnavailableActivity;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.models.Reservations;
import com.example.flightreservationsystem.models.Validation;
import com.example.flightreservationsystem.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ViewReservations extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout home, schedule , edit , open , unavailable, archive ,reservation ,filter , logout;

//    Button scheduleFlightButton;

    /////////cards/////
    private RecyclerView recyclerView;
    private ReservationAdapter reservationAdapter;
    private List<Reservations> reservationList;

    Button searchReservations;
    EditText flightNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations);

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

        searchReservations = findViewById(R.id.search_button);
        flightNumber = findViewById(R.id.flight_number);

        menu.setOnClickListener(v -> openDrawer(drawerLayout));

        home.setOnClickListener(v -> redirectActivity(ViewReservations.this, AdminHomeActivity.class));

        schedule.setOnClickListener(v -> startActivity(new Intent(ViewReservations.this, ScheduleFlightActivity.class)));

        edit.setOnClickListener(v -> startActivity(new Intent(ViewReservations.this, EditFlightActivity.class)));

        open.setOnClickListener(v -> startActivity(new Intent(ViewReservations.this, ViewOpenActivity.class)));

        unavailable.setOnClickListener(v -> startActivity(new Intent(ViewReservations.this, ViewUnavailableActivity.class)));

        archive.setOnClickListener(v -> startActivity(new Intent(ViewReservations.this, ViewArchiveActivity.class)));

        reservation.setOnClickListener(v -> recreate());

        filter.setOnClickListener(v -> startActivity(new Intent(ViewReservations.this, FilterFlightsActivity.class)));

        logout.setOnClickListener(v -> {
            Toast.makeText(ViewReservations.this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ViewReservations.this, LoginActivity.class));
        });


        ////////////////////////////////////

        searchReservations.setOnClickListener(v -> {

            // Get flight number from the user
            String flight_number = flightNumber.getText().toString();

            if(!Validation.isValidFlightNumber(flight_number)) {
                flightNumber.setError("Invalid Flight Number");
                Toast.makeText(ViewReservations.this, "Invalid Flight Number", Toast.LENGTH_SHORT).show();
                return;
            }

            // Initialize RecyclerView and adapter
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Enables vertical scrolling

            reservationList = new ArrayList<>();

            DatabaseHelper databaseHelper = new DatabaseHelper(this, null, 1);
            reservationList = databaseHelper.getReservationsByFlightNumber(flight_number);

            if (reservationList.isEmpty()) {
                Toast.makeText(this, "No Reservations For this Flight", Toast.LENGTH_SHORT).show();
            }

            // Set the adapter with flightList
            reservationAdapter = new ReservationAdapter(this, reservationList);
            recyclerView.setAdapter(reservationAdapter);  // Set the adapter for RecyclerView
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