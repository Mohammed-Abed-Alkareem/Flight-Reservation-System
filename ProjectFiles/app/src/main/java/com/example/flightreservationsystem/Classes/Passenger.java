package com.example.flightreservationsystem.Classes;

import com.example.flightreservationsystem.Classes.User;

public class Passenger extends User {
   /*
   * Passport number
 Passport issue date
 Passport issue place
 Passport expiration date
 Food preference (seafood, vegetarian, etc.)
 Date of Birth
 Nationality
   * */
    private String passport_number;
    private String passport_issue_date;
    private String passport_issue_place;
    private String food_preference;
    private String date_of_birth;
    private String nationality;

    public Passenger(String email, String phone, String first_name, String last_name, String password_hash, String role, String passport_number, String passport_issue_date, String passport_issue_place , String food_preference, String date_of_birth, String nationality) {
        super(email, phone, first_name, last_name, password_hash, role);
        this.passport_number = passport_number;
        this.passport_issue_date = passport_issue_date;
        this.passport_issue_place = passport_issue_place;
        this.food_preference = food_preference;
        this.date_of_birth = date_of_birth;
        this.nationality = nationality;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getPassport_issue_date() {
        return passport_issue_date;
    }

    public void setPassport_issue_date(String passport_issue_date) {
        this.passport_issue_date = passport_issue_date;
    }

    public String getPassport_issue_place() {
        return passport_issue_place;
    }

    public void setPassport_issue_place(String passport_issue_place) {
        this.passport_issue_place = passport_issue_place;
    }

    public String getFood_preference() {
        return food_preference;
    }

    public void setFood_preference(String food_preference) {
        this.food_preference = food_preference;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
