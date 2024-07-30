package com.example.flightreservationsystem.Classes;

public class User {
    private String email;
    private String phone;
    private String first_name;
    private String last_name;
    private String password_hash;
    private String role;

    public User(String email, String phone, String first_name, String last_name, String password_hash, String role) {

        this.email = email;
        this.phone = phone;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password_hash = password_hash;
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
