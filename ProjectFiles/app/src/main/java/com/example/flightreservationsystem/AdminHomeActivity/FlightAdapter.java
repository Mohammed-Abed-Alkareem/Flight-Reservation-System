package com.example.flightreservationsystem.AdminHomeActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightreservationsystem.Classes.Flights;
import com.example.flightreservationsystem.R;

import java.time.LocalTime;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private Context context;
    private List<Flights> flightList;

    public FlightAdapter(Context context, List<Flights> flightList) {
        this.context = context;
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flight_card, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flights flight = flightList.get(position);
        holder.flightNumber.setText(flight.getFlightNumber());
        holder.departureCity.setText(flight.getDepartureCity());
        holder.arrivalCity.setText(flight.getArrivalCity());

        // Handle LocalTime to String conversion
        holder.departureTime.setText(flight.getDepartureTime() != null ? flight.getDepartureTime().toString() : "N/A");
        holder.arrivalTime.setText(flight.getArrivalTime() != null ? flight.getArrivalTime().toString() : "N/A");

        holder.duration.setText(flight.getDuration());
        holder.aircraftModel.setText(flight.getAircraftModel());

        holder.economyPrice.setText(String.format("$%.2f", flight.getEconomyPrice()));
        holder.businessPrice.setText(String.format("$%.2f", flight.getBusinessPrice()));
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView flightNumber, departureCity, arrivalCity, departureTime, arrivalTime,
                duration, aircraftModel, economyPrice, businessPrice;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            flightNumber = itemView.findViewById(R.id.flight_number);
            departureCity = itemView.findViewById(R.id.departure_city);
            arrivalCity = itemView.findViewById(R.id.arrival_city);
            departureTime = itemView.findViewById(R.id.departure_time);
            arrivalTime = itemView.findViewById(R.id.arrival_time);
            duration = itemView.findViewById(R.id.duration);
            aircraftModel = itemView.findViewById(R.id.aircraft_model);
            economyPrice = itemView.findViewById(R.id.economy_price);
            businessPrice = itemView.findViewById(R.id.business_price);
        }
    }
}
