package com.example.flightreservationsystem.PassengerActivity.search;

import static com.example.flightreservationsystem.models.Validation.isValidDate;
import static com.example.flightreservationsystem.models.Validation.isValidName;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.flightreservationsystem.PassengerActivity.CurrentReservations;
import com.example.flightreservationsystem.PassengerActivity.PassengerHomeActivity;
import com.example.flightreservationsystem.PassengerActivity.PreviousReservations;
import com.example.flightreservationsystem.PassengerActivity.ReserveFlight;

import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.utils.DatabaseHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchFlightsActivity extends AppCompatActivity {

    // Date Formatter
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Drawer Variables
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, search, reserve, current, previous, logout;

    // Database Helper
    DatabaseHelper databaseHelper;

    // Layout Variables
    private EditText from_date, to_date;
    private EditText departureCity, arrivalCity;
    private Button searchButton;

    // Recycler View Variables
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<Flights> flightList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_flight_activity);

        // Initialize Database Helper
        databaseHelper = new DatabaseHelper(this, null, 1);

        // Initialize Drawer Variables
        drawerLayout = findViewById(R.id.passenger_drawer_layout);
        menu = findViewById(R.id.menu_icon);
        home = findViewById(R.id.passenger_home);
        reserve = findViewById(R.id.make_reservation);
        search = findViewById(R.id.search_flight);
        current = findViewById(R.id.view_current_reservations);
        previous = findViewById(R.id.view_previous_reservations);
        logout = findViewById(R.id.logout);

        // Drawer Functionality
        menu.setOnClickListener(v -> openDrawer(drawerLayout));
        home.setOnClickListener(v -> redirectActivity(SearchFlightsActivity.this, PassengerHomeActivity.class));
        reserve.setOnClickListener(v -> redirectActivity(SearchFlightsActivity.this, ReserveFlight.class));
        search.setOnClickListener(v -> recreate());
        current.setOnClickListener(v -> redirectActivity(SearchFlightsActivity.this, CurrentReservations.class));
        previous.setOnClickListener(v -> redirectActivity(SearchFlightsActivity.this, PreviousReservations.class));

        logout.setOnClickListener(v -> {
            Toast.makeText(SearchFlightsActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            redirectActivity(SearchFlightsActivity.this, LoginActivity.class);
        });


        // Initialize EditText fields
        from_date = findViewById(R.id.fromDate);
        to_date = findViewById(R.id.toDate);


        // Set up DatePickers for date fields
        setupDatePicker(from_date);
        setupDatePicker(to_date);


        departureCity = findViewById(R.id.departureCity);
        arrivalCity = findViewById(R.id.arrivalCity);

        // Initialize Button
        searchButton = findViewById(R.id.searchButton);

        // Set OnClickListener for search button
        searchButton.setOnClickListener(v -> {

            // clear error messages
            from_date.setError(null);
            to_date.setError(null);

            departureCity.setError(null);
            arrivalCity.setError(null);

            // Get input values
            String from_Date = from_date.getText().toString();
            String to_Date = to_date.getText().toString();
            String depCity = departureCity.getText().toString();
            String arrCity = arrivalCity.getText().toString();

            // Filter flights
            filterFlights(from_Date, to_Date, depCity, arrCity);

        });
    }

    private void filterFlights(String fromDate, String toDate, String depCity, String arrCity) {

        //check if date is empty
        if (fromDate.isEmpty()) { // If from date is empty, set it to today
            fromDate = LocalDate.now().format(dateFormatter);
        }

        if (toDate.isEmpty()) { // If to date is empty, set it to 2099-12-31
            toDate = "2099-12-31";
        }

        //if to date is before from date, to date is set to error
        if (LocalDate.parse(fromDate, dateFormatter).isAfter(LocalDate.parse(toDate, dateFormatter))) {
            to_date.setError("To date must be after from date");
            return;
        }

        if (!validateInputs(fromDate, toDate, depCity, arrCity)) { // If inputs are invalid
            Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Enables vertical scrolling

        // Get the flights from the database
        flightList = new ArrayList<>();
        flightList = databaseHelper.PassengerSearchFlights(fromDate, toDate, depCity, arrCity);

        // Set the adapter with flightList
        searchAdapter = new SearchAdapter(this, flightList);
        recyclerView.setAdapter(searchAdapter);  // Set the adapter for RecyclerView

    }

    private void setupDatePicker(final EditText editText) {
        editText.setFocusable(false); // Disable manual input
        editText.setClickable(true); // Enable clicking

        editText.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(SearchFlightsActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> { // Set the date on editText when selected
                        editText.setText(String.format("%04d-%02d-%02d", year1, monthOfYear + 1, dayOfMonth));
                    }, year, month, day);
            datePickerDialog.show();
        });
    }

    // Validate inputs
    private boolean validateInputs(String fromDate, String toDate, String depCity, String arrCity) {

        boolean isValid = true;

        if(isValidDate(fromDate)) {
            from_date.setError(null);
        } else {
            from_date.setError("Invalid Date Format yyyy-MM-dd");
            isValid = false;
        }

        if(isValidDate(toDate)) {
            to_date.setError(null);
        } else {
            to_date.setError("Invalid Date Format yyyy-MM-dd");
            isValid = false;
        }


        if(isValidName(depCity)) {
            departureCity.setError(null);
        } else {
            departureCity.setError("Invalid departure city");
            isValid = false;
        }

        if(isValidName(arrCity)) {
            arrivalCity.setError(null);
        } else {
            arrivalCity.setError("Invalid arrival city");
            isValid = false;
        }

        return isValid;

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}
