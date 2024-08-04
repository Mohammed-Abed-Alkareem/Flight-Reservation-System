package com.example.flightreservationsystem.Sign;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity;
import com.example.flightreservationsystem.Classes.Admin;
import com.example.flightreservationsystem.Classes.Passenger;
import com.example.flightreservationsystem.Classes.User;
import com.example.flightreservationsystem.DatabaseHelper;
import com.example.flightreservationsystem.Hash;
//import com.example.flightreservationsystem.AdminActivites.AdminHomeActivity;
import com.example.flightreservationsystem.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private CheckBox rememberMeCheckBox;
    private Button loginButton;
    private Button signupButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this, null, 1);

        // Initialize views
        emailEditText = findViewById(R.id.email_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        rememberMeCheckBox = findViewById(R.id.remember_me_checkbox);
        loginButton = findViewById(R.id.register_button);
        signupButton = findViewById(R.id.signup_button);

        // Set up button click listeners
        loginButton.setOnClickListener(v -> {
            emailEditText.setError(null);
            passwordEditText.setError(null);
            handleLogin();
        });

        signupButton.setOnClickListener(v -> {
            // Handle sign up button click
            Intent intent = new Intent(LoginActivity.this, RoleSelection.class);
            startActivity(intent);
        });

        // Load saved email and password if "Remember Me" was checked
        SharedPreferences preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        if (preferences.getBoolean("rememberMe", false)) {
            emailEditText.setText(preferences.getString("email", ""));
            passwordEditText.setText(preferences.getString("password", ""));
            rememberMeCheckBox.setChecked(true);
        }
    }

    private void handleLogin() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (rememberMeCheckBox.isChecked()) {
            // Save the email and password to SharedPreferences
            SharedPreferences preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email", email);
            editor.putString("password", password);
            editor.putBoolean("rememberMe", true);
            editor.apply();
        } else {
            // Clear saved email and password if "Remember Me" is not checked
            SharedPreferences preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }

        User user = checkLogin(email, password);
        if (user != null) {
            // Save the user details to SharedPreferences
            SharedPreferences preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("userId", user.getId());
            //add name
            editor.putString("userFirstName", user.getFirst_name());
            editor.putString("userRole", user instanceof Passenger ? "Passenger" : "Admin");
            editor.apply();

            Intent intent = null;
//            if (user instanceof Admin) {
//                intent = new Intent(LoginActivity.this, AdminHomeActivity.class);

//            }
//            else if (user instanceof Passenger) {
//                intent = new Intent(LoginActivity.this, PassengerHomeActivity.class);
//            }
            intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
            startActivity(intent);
        } else {
            // Display an error message
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            emailEditText.setError("Invalid email or password");
            emailEditText.requestFocus();
            passwordEditText.setError("Invalid email or password");
            passwordEditText.requestFocus();
        }
    }

    private User checkLogin(String email, String password) {
        User user = databaseHelper.getUserByEmail(email);

        if (user == null) {
            return null;
        }

        String hashedPassword = Hash.hashPassword(password);
        if (user instanceof Passenger) {
            Passenger passenger = (Passenger) user;
            if (passenger.getPassword_hash().equals(hashedPassword)) {
                return passenger;
            }
        } else if (user instanceof Admin) {
            Admin admin = (Admin) user;
            if (admin.getPassword_hash().equals(hashedPassword)) {
                return admin;
            }
        }

        return null;
    }
}
