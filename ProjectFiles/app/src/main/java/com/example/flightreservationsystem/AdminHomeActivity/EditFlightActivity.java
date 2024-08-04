package com.example.flightreservationsystem.AdminHomeActivity;

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

import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;

public class EditFlightActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout home, schedule , edit , open , unavailable, archive ,reservation ,filter , logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flight);

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
                redirectActivity(EditFlightActivity.this, AdminHomeActivity.class);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               redirectActivity(EditFlightActivity.this, ScheduleFlightActivity.class);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditFlightActivity.this, ViewOpenFlightsActivity.class));
            }
        });

        unavailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditFlightActivity.this, ViewUnavailableActivity.class));
            }
        });

        archive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditFlightActivity.this, ViewFlightsArchiveActivity.class));
            }
        });

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditFlightActivity.this, ViewReservationsActivity.class));
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditFlightActivity.this, FilterFlightsActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditFlightActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditFlightActivity.this, LoginActivity.class));
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