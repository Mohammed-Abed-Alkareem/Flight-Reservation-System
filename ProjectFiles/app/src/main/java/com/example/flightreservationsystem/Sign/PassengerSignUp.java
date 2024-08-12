package com.example.flightreservationsystem.Sign;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.models.Passenger;
import com.example.flightreservationsystem.models.Validation;
import com.example.flightreservationsystem.utils.DatabaseHelper;
import com.example.flightreservationsystem.utils.Hash;
import com.example.flightreservationsystem.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class PassengerSignUp extends AppCompatActivity {

    DateTimeFormatter formatter_date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    ;
    private EditText emailEditText, phoneEditText, firstNameEditText, lastNameEditText,
            passportNumberEditText, passportIssuePlaceEditText, nationalityEditText,
            passwordEditText, confirmPasswordEditText, passportIssueDateEditText,
            dateOfBirthEditText, expiryDateEditText;
    private Spinner foodPreferencesSpinner;

    private DatabaseHelper databaseHelper;

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

        passportIssueDateEditText = findViewById(R.id.passportIssueDateEditText);
        dateOfBirthEditText = findViewById(R.id.dateOfBirthEditText);
        expiryDateEditText = findViewById(R.id.expiryDateEditText);

        foodPreferencesSpinner = findViewById(R.id.spinnerFoodPreference);
        Button registerButton = findViewById(R.id.register_button);
        Button loginButton = findViewById(R.id.signup_button);

        // Set up food preferences spinner
        String[] options = {"Normal", "Vegetarian", "Non-Vegetarian", "Vegan", "Halal", "Gluten-Free"};
        ArrayAdapter<String> foodPreferencesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        foodPreferencesSpinner.setAdapter(foodPreferencesAdapter);

        // Set up button listeners
        loginButton.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
        registerButton.setOnClickListener(v -> registerPassenger());

        passportIssueDateEditText.setOnClickListener(v -> showDatePickerDialog("issue", passportIssueDateEditText));
        dateOfBirthEditText.setOnClickListener(v -> showDatePickerDialog("dob", dateOfBirthEditText));
        expiryDateEditText.setOnClickListener(v -> showDatePickerDialog("expiry", expiryDateEditText));
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
        String nationality = nationalityEditText.getText().toString();

        LocalDate passportIssueDate = null;
        LocalDate dateOfBirth = null;
        LocalDate passportExpiryDate = null;
        try {
            // Only parse if the text is not empty
            if (!passportIssueDateEditText.getText().toString().isEmpty()) {
                passportIssueDate = LocalDate.parse(passportIssueDateEditText.getText().toString(), formatter_date);
            }
            if (!dateOfBirthEditText.getText().toString().isEmpty()) {
                dateOfBirth = LocalDate.parse(dateOfBirthEditText.getText().toString(), formatter_date);
            }
            if (!expiryDateEditText.getText().toString().isEmpty()) {
                passportExpiryDate = LocalDate.parse(expiryDateEditText.getText().toString(), formatter_date);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Invalid date format. Expected format: yyyy-mm-dd", Toast.LENGTH_SHORT).show();
        }

        // Clear previous errors
        clearErrors();

        boolean isValid = true;

        try {
            // Validation
            isValid = validateFields(email, phone, firstName, lastName, password, confirmPassword, passportNumber, passportIssuePlace, passportExpiryDate, dateOfBirth, passportIssueDate);

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
                Toast.makeText(this, "Please fill in all the fields correctly.", Toast.LENGTH_SHORT).show();
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

    private boolean validateFields(String email, String phone, String firstName, String lastName, String password, String confirmPassword, String passportNumber, String passportIssuePlace, LocalDate passportExpiryDate, LocalDate dateOfBirth, LocalDate passportIssueDate) {
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

        if (!Validation.isValidDate(dateOfBirth.format(formatter_date))) {
            isValid = false;
            dateOfBirthEditText.setError("Invalid date format. Expected format: yyyy-mm-dd");
        }
        if (!Validation.isValidDate(passportIssueDate.format(formatter_date))) {
            isValid = false;
            passportIssueDateEditText.setError("Invalid date format. Expected format: yyyy-mm-dd");
        }
        if (!Validation.isValidDate(passportExpiryDate.format(formatter_date))) {
            isValid = false;
            expiryDateEditText.setError("Invalid date format. Expected format: yyyy-mm-dd");
        }

        return isValid;
    }

    private void showDatePickerDialog(final String kind, final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Store the selected date
                    if (kind.equals("dob")) {
                        dobYear = selectedYear;
                        dobMonth = selectedMonth + 1; // Month is 0-indexed so add 1
                        dobDay = selectedDay;
                    } else if (kind.equals("issue")) {
                        issueYear = selectedYear;
                        issueMonth = selectedMonth + 1;
                        issueDay = selectedDay;
                    } else if (kind.equals("expiry")) {
                        expiryYear = selectedYear;
                        expiryMonth = selectedMonth + 1;
                        expiryDay = selectedDay;
                    }
                    // Set the selected date to the EditText field
                    editText.setText(String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay));
                }, year, month, day);

        datePickerDialog.show();
    }


}
