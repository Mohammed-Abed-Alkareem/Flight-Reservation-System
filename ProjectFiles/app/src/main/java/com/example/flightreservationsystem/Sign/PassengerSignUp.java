package com.example.flightreservationsystem.Sign;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.Classes.Passenger;
import com.example.flightreservationsystem.Classes.Validation;
import com.example.flightreservationsystem.utils.DatabaseHelper;
import com.example.flightreservationsystem.utils.Hash;
import com.example.flightreservationsystem.R;

import java.util.Calendar;

public class PassengerSignUp extends AppCompatActivity {

    private EditText emailEditText, phoneEditText, firstNameEditText, lastNameEditText,
            passportNumberEditText, passportIssuePlaceEditText, nationalityEditText,
            passwordEditText, confirmPasswordEditText;
    private Button passportIssueDateButton, dateOfBirthButton, expiryDateButton;
    private Spinner foodPreferencesSpinner;

    private EditText currentDateField; // to track the current date field

    private DatePickerDialog datePickerDialog;

    private DatabaseHelper databaseHelper;
    private Button registerButton;
    private Button loginButton;

    // Variables to store the selected dates
    private int dobYear, dobMonth, dobDay;
    private int issueYear, issueMonth, issueDay;
    private int expiryYear, expiryMonth, expiryDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_passenger);

        databaseHelper = new DatabaseHelper(this, null, 1);

        // Initialize UI elements
        emailEditText = findViewById(R.id.email_edit_text);
        phoneEditText = findViewById(R.id.phone_edit_text);
        firstNameEditText = findViewById(R.id.first_name_edit_text);
        lastNameEditText = findViewById(R.id.last_name_edit_text);
        passportNumberEditText = findViewById(R.id.editTextPassportNumber);
        passportIssuePlaceEditText = findViewById(R.id.editTextPassportIssuePlace);
        nationalityEditText = findViewById(R.id.editTextNationality);
        passwordEditText = findViewById(R.id.password_edit_text);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text);

        foodPreferencesSpinner = findViewById(R.id.spinnerFoodPreference);
        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.signup_button);

        passportIssueDateButton = findViewById(R.id.passportIssueDateButton);
        dateOfBirthButton = findViewById(R.id.dateOfBirthButton);
        expiryDateButton = findViewById(R.id.expiryDateButton);

        // Set up food preferences spinner
        String[] options = {"Normal", "Vegetarian", "Non-Vegetarian", "Vegan", "Halal", "Gluten-Free"};
        ArrayAdapter<String> foodPreferencesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        foodPreferencesSpinner.setAdapter(foodPreferencesAdapter);

        // Set up button listeners
        loginButton.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        registerButton.setOnClickListener(v -> registerPassenger());

        passportIssueDateButton.setOnClickListener(v -> {
            showDatePickerDialog("issue");
        });

        dateOfBirthButton.setOnClickListener(v -> {
            showDatePickerDialog("dob");
        });

        expiryDateButton.setOnClickListener(v -> {
            showDatePickerDialog("expiry");
        });



    }

    private void registerPassenger() {
        // Get the values from the EditText fields
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        String passportNumber = passportNumberEditText.getText().toString();
        String passportIssuePlace = passportIssuePlaceEditText.getText().toString();
        //String passportIssueDate = passportIssueDateButton.getText().toString();
        //String dateOfBirth = dateOfBirthButton.getText().toString();
        String nationality = nationalityEditText.getText().toString();

        String passportIssueDate = getDate(issueYear, issueMonth, issueDay);
        String dateOfBirth = getDate(dobYear, dobMonth, dobDay);
        String passportExpiryDate = getDate(expiryYear, expiryMonth, expiryDay);


        // Clear previous errors
        clearErrors();

        boolean isValid = true;

        try {
            // Validation
            isValid = validateFields(email, phone, firstName, lastName, password, confirmPassword, passportNumber, passportIssuePlace);

            if (isValid) {
                // Create a new Passenger object
                Passenger passenger = new Passenger();
                passenger.setEmail(email);
                passenger.setPhone(phone);
                passenger.setFirst_name(firstName);
                passenger.setLast_name(lastName);
                passenger.setPassword_hash(Hash.hashPassword(password));
                passenger.setRole("passenger");
                passenger.setPassport_number(passportNumber);
                passenger.setPassport_issue_date(passportIssueDate);
                passenger.setPassport_issue_place(passportIssuePlace);
                passenger.setPassport_expiration_date(passportExpiryDate);
                passenger.setFood_preference(foodPreferencesSpinner.getSelectedItem().toString());
                passenger.setDate_of_birth(dateOfBirth);
                passenger.setNationality(nationality);



                // Add the Passenger object to the database
                databaseHelper.insertPassengerDetails(passenger);
                // Redirect to the login page
                startActivity(new Intent(this, LoginActivity.class));
            } else {
                throw new Exception("Failed to register passenger.");
            }

        } catch (Exception e) {
            // Display error message
            Toast.makeText(this, "Registration Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void clearErrors() {
        emailEditText.setError(null);
        phoneEditText.setError(null);
        firstNameEditText.setError(null);
        lastNameEditText.setError(null);
        passwordEditText.setError(null);
        confirmPasswordEditText.setError(null);
        passportNumberEditText.setError(null);
        passportIssuePlaceEditText.setError(null);

    }

    private boolean validateFields(String email, String phone, String firstName, String lastName, String password, String confirmPassword, String passportNumber, String passportIssuePlace) {
        boolean isValid = true;

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
        if (!Validation.isValidPassportNumber(passportNumber)) {
            isValid = false;
            passportNumberEditText.setError("Invalid passport number format. Expected format: two uppercase letters followed by seven digits.");
        }
        if (!Validation.isValidName(passportIssuePlace)) {
            isValid = false;
            passportIssuePlaceEditText.setError("Invalid name format. Expected format: alphabetic characters and certain special characters like ',.-");
        }

        return isValid;
    }

    private void showDatePickerDialog(final String kind) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                PassengerSignUp.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Store the selected date
                        if (kind.equals("dob")) {
                            dobYear = year;
                            dobMonth = month + 1; // Month is 0-indexed so add 1
                            dobDay = dayOfMonth;
                        } else if (kind.equals("issue")) {
                            issueYear = year;
                            issueMonth = month + 1; // Month is 0-indexed so add 1
                            issueDay = dayOfMonth;
                        } else if (kind.equals("expiry")) {
                            expiryYear = year;
                            expiryMonth = month + 1; // Month is 0-indexed so add 1
                            expiryDay = dayOfMonth;
                        }


                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    // Method to get the selected Date
    public String getDate(int year, int month, int day) {
        // yyyy/mm/dd
        // make sure the month and day are in two digits
        String monthString = month < 10 ? "0" + month : String.valueOf(month);
        String dayString = day < 10 ? "0" + day : String.valueOf(day);

        return year + "-" + monthString + "-" + dayString;

    }

}

