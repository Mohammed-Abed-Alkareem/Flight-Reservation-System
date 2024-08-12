package com.example.flightreservationsystem.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.models.Admin;
import com.example.flightreservationsystem.models.Validation;
import com.example.flightreservationsystem.utils.DatabaseHelper;
import com.example.flightreservationsystem.utils.Hash;
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
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            // Clear previous errors
            emailEditText.setError(null);
            phoneEditText.setError(null);
            firstNameEditText.setError(null);
            lastNameEditText.setError(null);
            passwordEditText.setError(null);
            confirmPasswordEditText.setError(null);

            try {
                // Validation
                if (!Validation.isValidEmail(email)) {
                    isValid = false;
                    emailEditText.setError("Invalid email format. Expected format: example@domain.com");
                    emailEditText.requestFocus();
                }
                if (!Validation.isValidPhone(phone)) {
                    isValid = false;
                    phoneEditText.setError("Invalid phone format. Expected format: 10 digits.");
                    phoneEditText.requestFocus();
                }
                if (!Validation.isValidName(firstName)) {
                    isValid = false;
                    firstNameEditText.setError("Invalid name format. Expected format: alphabetic characters and certain special characters like ',.-");
                    firstNameEditText.requestFocus();
                }
                if (!Validation.isValidName(lastName)) {
                    isValid = false;
                    lastNameEditText.setError("Invalid name format. Expected format: alphabetic characters and certain special characters like ',.-");
                    lastNameEditText.requestFocus();
                }
                if (!Validation.isValidPassword(password)) {
                    isValid = false;
                    passwordEditText.setError("Invalid password format. Expected format: at least 8 characters, with at least one digit, one lower case, one upper case letter, and one special character.");
                    passwordEditText.requestFocus();
                }
                if (!Validation.validateConfirmPassword(confirmPassword, password)) {
                    isValid = false;
                    confirmPasswordEditText.setError("Passwords do not match.");
                    confirmPasswordEditText.requestFocus();
                }

                // If all validations pass, proceed with registration
                if (isValid) {
                    // Hash passwords
                    String hashedPassword = Hash.hashPassword(password);
                    String hashedConfirmPassword = Hash.hashPassword(confirmPassword);

                    // Register the user
                    Admin admin = new Admin(
                            0, // dummy id
                            email,
                            phone,
                            firstName,
                            lastName,
                            hashedPassword,
                            "admin"
                    );
                    boolean success = databaseHelper.insertAdmin(admin);

                    if (success) {
                        Toast.makeText(this, "Admin registered successfully", Toast.LENGTH_SHORT).show();

                        startActivity(loginIntent);
                    } else {
                        throw new Exception("Failed to register admin.");
                    }
                }
            } catch (Exception e) {
                // Display error message
                Toast.makeText(this, "Registration Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }


}
