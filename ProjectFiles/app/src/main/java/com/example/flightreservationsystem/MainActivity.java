package com.example.flightreservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.RestApi.ConnectionAsyncTask;
import com.example.flightreservationsystem.Sign.LoginActivity;
import com.example.flightreservationsystem.Sign.RoleSelection;
import com.example.flightreservationsystem.utils.DatabaseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button signupButton;
    private Intent loginIntent;
    private Intent signupIntent;

    private DatabaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ///////////////////Read from API/////////////////////

        ConnectionAsyncTask connectionAsyncTask = new ConnectionAsyncTask(MainActivity.this);
        connectionAsyncTask.execute("https://mocki.io/v1/c26a75f7-c431-4042-8cb9-d4b495c0376c");

        ///////////////////////////////////////////

        dataBaseHelper = new DatabaseHelper(MainActivity.this, null, 1);

        // Initialize Button and Intent variables
        loginButton = findViewById(R.id.register_button);
        signupButton = findViewById(R.id.admin_button);
        loginIntent = new Intent(this, LoginActivity.class);
        signupIntent = new Intent(this, RoleSelection.class);

        // Set OnClickListener for login button
        loginButton.setOnClickListener(v -> startActivity(loginIntent));

        // Set OnClickListener for signup button (if needed)
        signupButton.setOnClickListener(v -> startActivity(signupIntent));

        final ImageView airplane = findViewById(R.id.airplane);
        Animation airplaneAnimation = AnimationUtils.loadAnimation(this, R.anim.plane);
        airplane.startAnimation(airplaneAnimation);
    }

    public void insertFlightsDB(List<Flights> flights) {

        for (Flights flight : flights) {
            dataBaseHelper.insertFlight(flight);
        }

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        // Initialize DatabaseHelper
//        dataBaseHelper = new DatabaseHelper(MainActivity.this);
//    }
//
}
