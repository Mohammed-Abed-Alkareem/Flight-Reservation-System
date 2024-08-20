package com.example.flightreservationsystem.PassengerActivity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.flightreservationsystem.MainActivity;
import com.example.flightreservationsystem.PassengerActivity.search.SearchFlightsActivity;
import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.models.Reservations;
import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.utils.DatabaseHelper;

public class ReserveFlight extends AppCompatActivity {

    ///Notification Variables
    private static final String MY_CHANNEL_ID = "my_chanel_1";
    private static final String MY_CHANNEL_NAME = "My channel";
    private static final int NOTIFICATION_ID = 123;
    private static final String NOTIFICATION_TITLE = "Reservation Successful";
    private String NOTIFICATION_BODY = "Your reservation has been successfully made on the flight: ";

    // Drawer Variables
    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, search, reserve, current, previous, logout; // add others


    //layout variables
    TextView passengerName;
    EditText flightNumber, extraBag, classType, foodPreference;
    Button reserveButton;

    //shared preferences
    SharedPreferences preferences;
    //Database helper
    DatabaseHelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_flight);

        //drawer layout
        drawerLayout = findViewById(R.id.passenger_drawer_layout);
        menu = findViewById(R.id.menu_icon);

        home = findViewById(R.id.passenger_home);
        reserve = findViewById(R.id.make_reservation);
        search = findViewById(R.id.search_flight);
        current = findViewById(R.id.view_current_reservations);
        previous = findViewById(R.id.view_previous_reservations);
        logout = findViewById(R.id.logout);

        // layout variables
        flightNumber = findViewById(R.id.flight_number);
        extraBag = findViewById(R.id.extra_bags);
        classType = findViewById(R.id.class_type);
        foodPreference = findViewById(R.id.food_preferences);
        reserveButton = findViewById(R.id.reserve);

        //drawer functionality
        menu.setOnClickListener(v -> openDrawer(drawerLayout));
        home.setOnClickListener(v -> redirectActivity(ReserveFlight.this, PassengerHomeActivity.class));
        reserve.setOnClickListener(v -> recreate());
        search.setOnClickListener(v -> redirectActivity(ReserveFlight.this, SearchFlightsActivity.class));
        current.setOnClickListener(v -> redirectActivity(ReserveFlight.this, CurrentReservations.class));
        previous.setOnClickListener(v -> redirectActivity(ReserveFlight.this, PreviousReservations.class));

        logout.setOnClickListener(v -> {
            Toast.makeText(ReserveFlight.this, "Logged Out", Toast.LENGTH_SHORT).show();
            redirectActivity(ReserveFlight.this, LoginActivity.class);
        });

        ///////////////////drawer name and email///////////////////////////////
        TextView email = findViewById(R.id.passenger_email);
        //fetch email from shared preferences
        preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        email.setText(preferences.getString("userEmail", ""));

        passengerName = findViewById(R.id.passenger_name);
        //fetch name from shared preferences
        preferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        passengerName.setText(preferences.getString("userFirstName", ""));

        // reserve flight button functionality
        reserveButton.setOnClickListener(v -> {

            try { // try-catch block to handle invalid input

                // get input from user
                String flight_number = flightNumber.getText().toString();
                int extra_bags = Integer.parseInt(extraBag.getText().toString());
                String class_type = classType.getText().toString();
                String food_preference = foodPreference.getText().toString();

                // validate input
                if (flight_number.isEmpty()) {
                    flightNumber.setError("Please enter a flight number");
                    return;
                }

                // get flight from database
                databasehelper = new DatabaseHelper(this, null, 1);
                Flights flight = databasehelper.getFlightByNumber(flight_number);

                // if flight is not found, display error message
                if (flight == null) {
                    flightNumber.setError("Flight not found");
                    return;
                }

                // if extra bags is negative, display error message
                if (extra_bags < 0) {
                    extraBag.setError("Please enter a valid number of extra bags");
                    return;
                }

                // if class type is not "Economy" or "Business", display error message
                if (!class_type.equals("Economy") && !class_type.equals("Business")) {
                    classType.setError("Please enter a valid class type");
                    return;
                }

                // make reservation
                Reservations reservation = new Reservations();
                reservation.setFlightID(flight.getFlight_id());
                reservation.setUserID(preferences.getInt("userId", 0));
                reservation.setExtraBags(extra_bags);
                reservation.setClassType(class_type);
                reservation.setFoodPreference(food_preference);

                // calculate total price
                double total_price = 0;
                if (class_type.equals("Economy")) {
                    total_price = flight.getEconomyPrice();
                } else {
                    total_price = flight.getBusinessPrice();
                }

                total_price += extra_bags * flight.getExtraBaggagePrice();

                reservation.setTotalPrice(total_price);

                // add reservation to database
                databasehelper.addReservation(reservation);

                // update flight reservations count
                flight.setCurrentReservations(flight.getCurrentReservations() + 1);
                databasehelper.updateFlight(flight);

                NOTIFICATION_BODY += flight_number;

                //clear input fields
                flightNumber.setText("");
                extraBag.setText("");
                classType.setText("");
                foodPreference.setText("");

                flightNumber.setError(null);
                extraBag.setError(null);
                classType.setError(null);
                foodPreference.setError(null);

                // display success message
                Toast.makeText(this, "Reservation Successful", Toast.LENGTH_SHORT).show();

                // create notification
                createNotification(NOTIFICATION_TITLE, NOTIFICATION_BODY);
            } catch (Exception e) { // catch block to handle invalid input
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // open drawer
    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    // close drawer
    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    // redirect to another activity
    public static void redirectActivity(Activity activity, Class secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    // close drawer on pause
    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    // notifications Methods //

    // create notification channel
    private void createNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID, MY_CHANNEL_NAME, importance);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }

    // create notification
    public void createNotification(String title, String body) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MY_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
        } else {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

    // request permission for notifications
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            createNotification(NOTIFICATION_TITLE, NOTIFICATION_BODY);
        } else {
            Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
        }
    }

}



