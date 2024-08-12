package com.example.flightreservationsystem.AdminHomeActivity.Archived;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity;
import com.example.flightreservationsystem.AdminHomeActivity.EditFlightActivity;
import com.example.flightreservationsystem.AdminHomeActivity.filter.FilterFlightsActivity;
import com.example.flightreservationsystem.AdminHomeActivity.Open.ViewOpenActivity;
import com.example.flightreservationsystem.AdminHomeActivity.ViewReservationsActivity;
import com.example.flightreservationsystem.AdminHomeActivity.unava.ViewUnavailableActivity;
import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.utils.DatabaseHelper;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewArchiveActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout home, schedule , edit , open , unavailable, archive ,reservation ,filter , logout;

//    Button scheduleFlightButton;

    /////////cards/////
    private RecyclerView recyclerView;
    private ArchivedAdapter archivedAdapter;
    private List<Flights> flightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flights_archive);

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
                redirectActivity(ViewArchiveActivity.this, AdminHomeActivity.class);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewArchiveActivity.this, ViewArchiveActivity.class));
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewArchiveActivity.this, EditFlightActivity.class));
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewArchiveActivity.this, ViewOpenActivity.class));
            }
        });

        unavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewArchiveActivity.this, ViewUnavailableActivity.class));
            }
        });

        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewArchiveActivity.this, ViewReservationsActivity.class));
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewArchiveActivity.this, FilterFlightsActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewArchiveActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ViewArchiveActivity.this, LoginActivity.class));
            }
        });


        ////////////////////////////////////

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Enables vertical scrolling

        flightList = new ArrayList<>();

        DatabaseHelper databaseHelper = new DatabaseHelper(this, null, 1);
        flightList = databaseHelper.getArchiveFlights();

        if (flightList.isEmpty()) {
            Toast.makeText(this, "No flights in archive", Toast.LENGTH_SHORT).show();
        }

        // Set the adapter with flightList
        archivedAdapter = new ArchivedAdapter(this, flightList);
        recyclerView.setAdapter(archivedAdapter);  // Set the adapter for RecyclerView

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