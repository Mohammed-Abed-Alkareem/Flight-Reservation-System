package com.example.flightreservationsystem.Sign;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.Classes.Passenger;
import com.example.flightreservationsystem.Classes.Validation;
import com.example.flightreservationsystem.DatabaseHelper;
import com.example.flightreservationsystem.Hash;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;

public class PassengerSignUp extends AppCompatActivity {

    EditText emailEditText;
    EditText phoneEditText;
    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText passwordEditText;
    EditText confirmPasswordEditText;
    EditText passportNumberEditText;
    EditText passportIssueDateEditText;
    EditText passportIssuePlaceEditText;
    EditText passportExpiryDateEditText;
    EditText nationalityEditText;
    EditText dateOfBirthEditText;
    Spinner foodPreferencesSpinner;


    DatabaseHelper databaseHelper;

    Button registerButton;

    Button loginButton;
    Intent loginIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_passenger);

        databaseHelper = new DatabaseHelper(this, null, 1);

        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        firstNameEditText = findViewById(R.id.first_name_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);
        passportNumberEditText = findViewById(R.id.editTextPassportNumber);
        passportIssueDateEditText = findViewById(R.id.editTextPassportIssueDate);
        passportIssuePlaceEditText = findViewById(R.id.editTextPassportIssuePlace);
        passportExpiryDateEditText = findViewById(R.id.editTextPassportExpirationDate);

        dateOfBirthEditText = findViewById(R.id.editTextDateOfBirth);
        nationalityEditText = findViewById(R.id.editTextNationality);
        foodPreferencesSpinner = findViewById(R.id.spinnerFoodPreference);
        registerButton = findViewById(R.id.register_button);

        //////////////////////////
        String[] options={"Normal", "Vegetarian","Non-Vegetarian","Vegan", "Halal", "Gluten-Free"};
        ArrayAdapter<String> objGenderArr = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, options);
        foodPreferencesSpinner.setAdapter(objGenderArr);
        ///////////////////////


        loginButton = findViewById(R.id.signup_button);
        loginIntent = new Intent(this, LoginActivity.class);


        loginButton.setOnClickListener(v -> startActivity(loginIntent));

        registerButton.setOnClickListener(v -> {
            // Get the values from the EditText fields
            String email = emailEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String firstName = firstNameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            String passportNumber = passportNumberEditText.getText().toString();
            String passportIssueDate = passportIssueDateEditText.getText().toString();
            String passportIssuePlace = passportIssuePlaceEditText.getText().toString();
            String passportExpiryDate = passportExpiryDateEditText.getText().toString();
            String dateOfBirth = dateOfBirthEditText.getText().toString();

            // Clear previous errors
            emailEditText.setError(null);
            phoneEditText.setError(null);
            firstNameEditText.setError(null);
            lastNameEditText.setError(null);
            passwordEditText.setError(null);
            confirmPasswordEditText.setError(null);
            passportNumberEditText.setError(null);
            passportIssueDateEditText.setError(null);
            passportIssuePlaceEditText.setError(null);
            passportExpiryDateEditText.setError(null);
            dateOfBirthEditText.setError(null);

            boolean isValid = true;

            try {
                // Validation
                if (!Validation.isValidEmail(email)) {
                    isValid = false;
                    emailEditText.setError("Invalid email format. Expected format: example@domain.com");
                }
                if (!Validation.isValidPhone(phone)) {
                    isValid = false;
                    phoneEditText.setError("Invalid phone format. Expected format: 10 digits.");
                }
                if (!Validation.isValidName(firstName)) {
                    isValid = false;
                    firstNameEditText.setError("Invalid name format. Expected format: alphabetic characters and certain special characters like ',.-");
                }
                if (!Validation.isValidName(lastName)) {
                    isValid = false;
                    lastNameEditText.setError("Invalid name format. Expected format: alphabetic characters and certain special characters like ',.-");
                }
                if (!Validation.isValidPassword(password)) {
                    isValid = false;
                    passwordEditText.setError("Invalid password format. Expected format: at least 8 characters, with at least one digit, one lower case, one upper case letter, and one special character.");
                }
                if (!Validation.validateConfirmPassword(confirmPassword, password)) {
                    isValid = false;
                    confirmPasswordEditText.setError("Passwords do not match.");
                }

                if(!Validation.isValidPassportNumber(passportNumber)){
                    isValid = false;
                    passportNumberEditText.setError("Invalid passport number format. Expected format: two uppercase letters followed by seven digits.");
                }
                if(!Validation.isValidDate(passportIssueDate)){
                    isValid = false;
                    passportIssueDateEditText.setError("Invalid passport issue date format. Expected format: YYYY-MM-DD.");

                }
                if(!Validation.isValidName(passportIssuePlace)){
                    isValid = false;
                    passportIssuePlaceEditText.setError("Invalid name format. Expected format: alphabetic characters and certain special characters like ',.-");
                }
                if(!Validation.isValidDate(passportExpiryDate)){
                    isValid = false;
                    passportExpiryDateEditText.setError("Invalid passport expiry date format. Expected format: YYYY-MM-DD.");
                }
                if(!Validation.isValidDate(dateOfBirth)){
                    isValid = false;
                    dateOfBirthEditText.setError("Invalid date of birth format. Expected format: YYYY-MM-DD.");
                }

                if (isValid) {
                    // Create a new Passenger object
                    Passenger passenger = new Passenger(
                            0, // dummy id
                            email,
                            phone,
                            firstName,
                            lastName,
                            Hash.hashPassword(password),
                            "passenger",
                            passportNumber,
                            passportIssueDate,
                            passportIssuePlace,
                            foodPreferencesSpinner.getSelectedItem().toString(),
                            dateOfBirth,
                            nationalityEditText.getText().toString()
                    );

                    // Add the Passenger object to the database
                    databaseHelper.insertPassengerDetails(passenger);
                    // Redirect to the login page
                    startActivity(loginIntent);
                }else {
                    throw new Exception("Failed to register passenger.");
                }

        } catch (Exception e) {
            // Display error message
            Toast.makeText(this, "Registration Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        });

    }

}


