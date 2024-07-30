package com.example.flightreservationsystem.Classes;

public class Admin extends User {
    public Admin(String email, String phone, String first_name, String last_name, String password_hash, String role) {
        super(email, phone, first_name, last_name, password_hash, role);
    }
}
