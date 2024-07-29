package com.example.flightreservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText = findViewById(R.id.email_edit_text);
    EditText passwordEditText = findViewById(R.id.password_edit_text);
    CheckBox rememberMeCheckBox = findViewById(R.id.remember_me_checkbox);
    Button loginButton = findViewById(R.id.login_button);

    Button signupButton = findViewById(R.id.signUp_button);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }
}
