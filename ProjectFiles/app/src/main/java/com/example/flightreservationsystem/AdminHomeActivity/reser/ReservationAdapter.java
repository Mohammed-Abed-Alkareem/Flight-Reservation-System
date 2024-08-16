package com.example.flightreservationsystem.AdminHomeActivity.reser;

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

    private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern("HH:mm");

    private List<Reservations> reservations;
    private Context context;

    public ReservationAdapter(Context context, List<Reservations> reservations) {
        this.context = context;
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public ReservationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_card, parent, false);
        return new ReservationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationViewHolder holder, int position) {
        Reservations reservation = reservations.get(position);
//        holder.flightIdTextView.setText("Flight ID: " + reservation.getFlightId());
//        holder.userIdTextView.setText("User ID: " + reservation.getUserId());
//        holder.flightClassTextView.setText("Flight Class: " + reservation.getFlightClass());
//        holder.extraBagsTextView.setText("Extra Bags: " + reservation.getExtraBags());
//        holder.totalPriceTextView.setText("Total Price: $" + reservation.getTotalPrice());
//        holder.foodPreferencesTextView.setText("Food Preferences: " + reservation.getFoodPreferences());
//        holder.reservationDateTextView.setText("Reservation Date: " + reservation.getReservationDate());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ReservationViewHolder extends RecyclerView.ViewHolder {
        TextView flightIdTextView;
        TextView userIdTextView;
        TextView flightClassTextView;
        TextView extraBagsTextView;
        TextView totalPriceTextView;
        TextView foodPreferencesTextView;
        TextView reservationDateTextView;

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
