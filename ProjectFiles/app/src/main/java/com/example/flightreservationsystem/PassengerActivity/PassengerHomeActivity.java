package com.example.flightreservationsystem.PassengerActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.flightreservationsystem.R;

public class PassengerHomeActivity extends AppCompatActivity {

        DrawerLayout drawerLayout;
        ImageView menu;

        LinearLayout home; // add others

        Button menuButton;

        TextView passengerName;
        SharedPreferences preferences;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_passenger_home);

            drawerLayout = findViewById(R.id.passenger_drawer_layout);
            menu = findViewById(R.id.menu_icon);

            menuButton = findViewById(R.id.menu);

            home = findViewById(R.id.passenger_home);
//            schedule = findViewById(R.id.schedule_flight);
//            edit = findViewById(R.id.edit_flight);
//            open = findViewById(R.id.view_open_flights);
//            unavailable = findViewById(R.id.view_unavailable_flights);
//            archive = findViewById(R.id.view_flights_archive);
//            reservation = findViewById(R.id.view_reservations);
//            filter = findViewById(R.id.filter_flights);
//            logout = findViewById(R.id.logout);


            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDrawer(drawerLayout);
                }
            });

            menuButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDrawer(drawerLayout);
                }
            });


            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                }
            });

//            schedule.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    redirectActivity(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, ScheduleFlightActivity.class);
//                }
//            });
//
//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    redirectActivity(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, EditFlightActivity.class);
//                }
//            });
//
//            open.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    redirectActivity(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, ViewOpenActivity.class);
//                }
//            });
//
//            unavailable.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    redirectActivity(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, ViewUnavailableActivity.class);
//                }
//            });
//
//            archive.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    redirectActivity(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, ViewArchiveActivity.class);
//                }
//            });
//
//            reservation.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    redirectActivity(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, ViewReservationsActivity.class);
//                }
//            });
//
//            filter.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    redirectActivity(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, FilterFlightsActivity.class);
//                }
//            });
//
//            logout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
//                    redirectActivity(com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity.this, LoginActivity.class);
//                }
//            });

            //////////////////////////////////////////////////

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