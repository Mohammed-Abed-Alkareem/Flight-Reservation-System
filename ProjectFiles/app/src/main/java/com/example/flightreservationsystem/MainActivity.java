package com.example.flightreservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button loginButton = findViewById(R.id.login_button);
    Button signupButton = findViewById(R.id.signup_button);
    Intent loginIntent = new Intent(this, LoginActivity.class);
    Intent signupIntent = new Intent(this, SignupActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton.setOnClickListener(v -> {
            startActivity(loginIntent);
        });


        signupButton.setOnClickListener(v -> {
            startActivity(signupIntent);
        });

    }


}