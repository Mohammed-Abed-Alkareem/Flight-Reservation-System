package com.example.flightreservationsystem.AdminHomeActivity.filter;

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

import com.example.flightreservationsystem.AdminHomeActivity.AdminHomeActivity;
import com.example.flightreservationsystem.AdminHomeActivity.Archived.ViewArchiveActivity;
import com.example.flightreservationsystem.AdminHomeActivity.EditFlightActivity;
import com.example.flightreservationsystem.AdminHomeActivity.Open.ViewOpenActivity;
import com.example.flightreservationsystem.AdminHomeActivity.ScheduleFlightActivity;
import com.example.flightreservationsystem.AdminHomeActivity.ViewReservationsActivity;
import com.example.flightreservationsystem.AdminHomeActivity.unava.ViewUnavailableActivity;
import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.utils.DatabaseHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FilterFlightsActivity extends AppCompatActivity {

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    DrawerLayout drawerLayout;
    ImageView menu;

    LinearLayout home, schedule, edit, open, unavailable, archive, reservation, filter, logout;

    DatabaseHelper databaseHelper;

    private EditText fromDepartureDate, toDepartureDate;
    private EditText fromArrivalDate, toArrivalDate;
    private EditText departureCity, arrivalCity;
    private Button filterButton;

    private RecyclerView recyclerView;
    private FilterAdapter filterAdapter;
    private List<Flights> flightList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_flights);

        databaseHelper = new DatabaseHelper(this, null, 1);

        drawerLayout = findViewById(R.id.admin_drawer_layout);
        menu = findViewById(R.id.menu_icon);

        home = findViewById(R.id.admin_home);
        schedule = findViewById(R.id.schedule_flight);
        edit = findViewById(R.id.edit_flight);
        open = findViewById(R.id.view_open_flights);
        unavailable = findViewById(R.id.view_unavailable_flights);
        archive = findViewById(R.id.view_flights_archive);
        reservation = findViewById(R.id.view_reservations);
        filter = findViewById(R.id.filter_flights);
        logout = findViewById(R.id.logout);

        menu.setOnClickListener(v -> openDrawer(drawerLayout));

        home.setOnClickListener(v -> redirectActivity(FilterFlightsActivity.this, AdminHomeActivity.class));

        schedule.setOnClickListener(v -> redirectActivity(FilterFlightsActivity.this, ScheduleFlightActivity.class));

        edit.setOnClickListener(v -> startActivity(new Intent(FilterFlightsActivity.this, EditFlightActivity.class)));

        open.setOnClickListener(v -> startActivity(new Intent(FilterFlightsActivity.this, ViewOpenActivity.class)));

        unavailable.setOnClickListener(v -> startActivity(new Intent(FilterFlightsActivity.this, ViewUnavailableActivity.class)));

        archive.setOnClickListener(v -> startActivity(new Intent(FilterFlightsActivity.this, ViewArchiveActivity.class)));

        reservation.setOnClickListener(v -> startActivity(new Intent(FilterFlightsActivity.this, ViewReservationsActivity.class)));

        filter.setOnClickListener(v -> recreate());

        logout.setOnClickListener(v -> {
            Toast.makeText(FilterFlightsActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(FilterFlightsActivity.this, LoginActivity.class));
        });

        // Initialize EditText fields
        fromDepartureDate = findViewById(R.id.fromDepartureDate);
        toDepartureDate = findViewById(R.id.toDepartureDate);

        fromArrivalDate = findViewById(R.id.fromArrivalDate);
        toArrivalDate = findViewById(R.id.toArrivalDate);

        departureCity = findViewById(R.id.departureCity);
        arrivalCity = findViewById(R.id.arrivalCity);

        filterButton = findViewById(R.id.filterButton);

        filterButton.setOnClickListener(v -> {

            // clear error messages
            fromDepartureDate.setError(null);
            toDepartureDate.setError(null);
            fromArrivalDate.setError(null);
            toArrivalDate.setError(null);
            departureCity.setError(null);
            arrivalCity.setError(null);

            String fromDepDate = fromDepartureDate.getText().toString();
            String toDepDate = toDepartureDate.getText().toString();
            String fromArrDate = fromArrivalDate.getText().toString();
            String toArrDate = toArrivalDate.getText().toString();
            String depCity = departureCity.getText().toString();
            String arrCity = arrivalCity.getText().toString();


            filterFlights(fromDepDate, toDepDate, fromArrDate, toArrDate, depCity, arrCity);

        });



        // Set up DatePickers for date fields
        setupDatePicker(fromDepartureDate);
        setupDatePicker(toDepartureDate);
        setupDatePicker(fromArrivalDate);
        setupDatePicker(toArrivalDate);




    }

    private void filterFlights(String fromDepDate, String toDepDate, String fromArrDate, String toArrDate, String depCity, String arrCity) {

        if (!validateInputs(fromDepDate, toDepDate, fromArrDate, toArrDate, depCity, arrCity)) {
            return;
        }

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Enables vertical scrolling

        flightList = new ArrayList<>();



        flightList = databaseHelper.AdminFilterFlights(fromDepDate, toDepDate, fromArrDate, toArrDate, depCity, arrCity);

        // Set the adapter with flightList
        filterAdapter = new FilterAdapter(this, flightList);
        recyclerView.setAdapter(filterAdapter);  // Set the adapter for RecyclerView


    }

    private void setupDatePicker(final EditText editText) {
        editText.setFocusable(false);
        editText.setClickable(true);
        editText.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(FilterFlightsActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {

                        editText.setText(String.format("%04d-%02d-%02d", year1, monthOfYear + 1, dayOfMonth));
                    }, year, month, day);
            datePickerDialog.show();
        });
    }


    private boolean validateInputs(String fromDepDate, String toDepDate, String fromArrDate, String toArrDate, String depCity, String arrCity) {

        boolean isValid = true;

        if(isValidDate(fromDepDate)) {
            fromDepartureDate.setError(null);
        } else {
            fromDepartureDate.setError("Invalid Date Format yyyy-MM-dd");
            isValid = false;
        }

        if(isValidDate(toDepDate)) {
            toDepartureDate.setError(null);
        } else {
            toDepartureDate.setError("Invalid Date Format yyyy-MM-dd");
            isValid = false;
        }

        if(isValidDate(fromArrDate)) {
            fromArrivalDate.setError(null);
        } else {
            fromArrivalDate.setError("Invalid Date Format yyyy-MM-dd");
            isValid = false;
        }

        if(isValidDate(toArrDate)) {
            toArrivalDate.setError(null);
        } else {
            toArrivalDate.setError("Invalid Date Format yyyy-MM-dd");
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
