package com.example.flightreservationsystem.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.Classes.Admin;
import com.example.flightreservationsystem.DatabaseHelper;
import com.example.flightreservationsystem.Hash;
import com.example.flightreservationsystem.R;

public class AdminSignUp extends AppCompatActivity {

    EditText emailEditText;
    EditText phoneEditText;
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;

    Button registerButton;

    Button signupButton;
    Intent loginIntent;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_admin);

        databaseHelper = new DatabaseHelper(this, null, 1);

        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        firstNameEditText = findViewById(R.id.first_name_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        registerButton = findViewById(R.id.register_button);

        signupButton = findViewById(R.id.signup_button);
        loginIntent = new Intent(this, LoginActivity.class);

        signupButton.setOnClickListener(v -> startActivity(loginIntent));

        registerButton.setOnClickListener(v -> {

            boolean isValid = true;

            String email = emailEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String password = Hash.hashPassword(passwordEditText.getText().toString());
            String confirmPassword = Hash.hashPassword(confirmPasswordEditText.getText().toString());

            if (!validateEmail(email)) {
                isValid = false;
            }
            if (!validatePhone(phone)) {
                isValid = false;
            }
            if (!validateFirstName(firstName)) {
                isValid = false;
            }
            if (!validateLastName(lastName)) {
                isValid = false;
            }
            if (!validatePassword(password)) {
                isValid = false;
            }
            if (!validateConfirmPassword(confirmPassword, password)) {
                isValid = false;
            }

            if (isValid) {
                // Register the user
                Admin admin = new Admin(email, phone, firstName, lastName, password, "admin");
                // Save the user to the database
                databaseHelper.insertAdmin(admin);

                Toast.makeText(this, "Admin registered successfully", Toast.LENGTH_SHORT).show();

            }


        });


    }

    private boolean validateEmail(String email) {
        // Validate email
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    private boolean validatePhone(String phone) {
        // Validate phone number
        if (phone.isEmpty()) {
            phoneEditText.setError("Phone number is required");
            return false;

        } else if (!android.util.Patterns.PHONE.matcher(phone).matches()) {
            phoneEditText.setError("Please enter a valid phone number");
            return false;
        }
        return true;
    }

    private boolean validateFirstName(String firstName) {
        // Validate first name
        if (firstName.isEmpty()) {
            firstNameEditText.setError("First name is required");
            return false;
        }
        return true;
    }


    private boolean validateLastName(String lastName) {
        // Validate last name
        if (lastName.isEmpty()) {
            lastNameEditText.setError("Last name is required");
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password) {
        // Validate password
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            return false;
        } else if (password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters long");
            return false;
        }
        return true;
    }

    private boolean validateConfirmPassword(String confirmPassword, String password) {
        // Validate confirm password
        if (confirmPassword.isEmpty()) {
            confirmPasswordEditText.setError("Confirm password is required");
            return false;
        } else if (!confirmPassword.equals(password)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }



}
