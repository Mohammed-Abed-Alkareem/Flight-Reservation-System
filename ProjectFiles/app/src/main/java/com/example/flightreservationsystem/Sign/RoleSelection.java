package com.example.flightreservationsystem.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.R;

public class RoleSelection extends AppCompatActivity {
    Button adminButton;
    Button userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        // Initialize views here
        adminButton = findViewById(R.id.admin_button);
        userButton = findViewById(R.id.register_button);

        // Set up button click listeners, etc.
        adminButton.setOnClickListener(v -> {
            // Handle admin button click
            Intent intent = new Intent(RoleSelection.this, AdminSignUp.class);
            startActivity(intent);
        });

        userButton.setOnClickListener(v -> {
            // Handle user button click
            Intent intent = new Intent(RoleSelection.this, PassengerSignUp.class);
            startActivity(intent);
        });
    }
}
