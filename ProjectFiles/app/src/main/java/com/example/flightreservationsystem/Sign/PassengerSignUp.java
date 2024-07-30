package com.example.flightreservationsystem.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;

public class PassengerSignUp extends AppCompatActivity {
    Button loginButton;
    Intent loginIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_passenger);

        loginButton = findViewById(R.id.signup_button);
        loginIntent = new Intent(this, LoginActivity.class);


        loginButton.setOnClickListener(v -> startActivity(loginIntent));
        
    }
}
