package com.example.flightreservationsystem.PassengerActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightreservationsystem.R;
import com.example.flightreservationsystem.models.Reservations;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder> {

    // formatter for date and time
    private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern("HH:mm");

    // list of reservations
    private List<Reservations> reservations;
    // context for the adapter
    private Context context;

    // constructor for the adapter taking in the context and list of reservations
    public ReservationAdapter(Context context, List<Reservations> reservations) {
        this.context = context;
        this.reservations = reservations;
    }

    // method to create the view holder for the adapter that fill  the layout for the reservation card
    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_card, parent, false);
        return new ReservationViewHolder(view);
    }

    // method to bind the data to the view holder for the reservation card
    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservations reservation = reservations.get(position);
        holder.flightIdTextView.setText("Flight ID: " + reservation.getFlightID());
        holder.userIdTextView.setText("User ID: " + reservation.getUserID());
        holder.flightClassTextView.setText(String.format("Flight Class: %s", reservation.getFlightClass()));
        holder.extraBagsTextView.setText("Extra Bags: " + reservation.getExtraBags());
        holder.totalPriceTextView.setText("Total Price: $" + reservation.getTotalPrice());
        holder.foodPreferencesTextView.setText("Food Preferences: " + reservation.getFoodPreference());
        String reservationDate = reservation.getReservationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        holder.reservationDateTextView.setText("Reservation Date: " + reservationDate);
    }

    // method to get the number of reservations for the adapter
    @Override
    public int getItemCount() {
        return reservations.size();
    }

    // view holder for the reservation card that holds the text views for the reservation details
    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        // Reservation details text views
        TextView flightIdTextView;
        TextView userIdTextView;
        TextView flightClassTextView;
        TextView extraBagsTextView;
        TextView totalPriceTextView;
        TextView foodPreferencesTextView;
        TextView reservationDateTextView;

        // constructor for the view holder that initializes the text views for each reservation detail
        public ReservationViewHolder(@NonNull View itemView) {
            super(itemView);
            flightIdTextView = itemView.findViewById(R.id.flight_id);
            userIdTextView = itemView.findViewById(R.id.user_id);
            flightClassTextView = itemView.findViewById(R.id.flight_class);
            extraBagsTextView = itemView.findViewById(R.id.extra_bags);
            totalPriceTextView = itemView.findViewById(R.id.total_price);
            foodPreferencesTextView = itemView.findViewById(R.id.food_preferences);
            reservationDateTextView = itemView.findViewById(R.id.reservation_date);
        }
    }
}
