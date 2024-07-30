package com.example.flightreservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.Sign.RoleSelection;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signupButton;
    private Intent loginIntent;
    private Intent signupIntent;

    private DatabaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Button and Intent variables
        loginButton = findViewById(R.id.register_button);
        signupButton = findViewById(R.id.admin_button);
        loginIntent = new Intent(this, LoginActivity.class);
        signupIntent = new Intent(this, RoleSelection.class);

        // Set OnClickListener for login button
        loginButton.setOnClickListener(v -> startActivity(loginIntent));

        // Set OnClickListener for signup button (if needed)
        signupButton.setOnClickListener(v -> startActivity(signupIntent));
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        // Initialize DatabaseHelper
//        dataBaseHelper = new DatabaseHelper(MainActivity.this);
//    }
//
}
