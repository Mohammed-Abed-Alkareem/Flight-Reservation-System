package com.example.flightreservationsystem.AdminHomeActivity.Archived;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightreservationsystem.Classes.Flights;
import com.example.flightreservationsystem.R;

import java.util.List;

public class ArchivedAdapter extends RecyclerView.Adapter<ArchivedAdapter.FlightViewHolder> {

    private Context context;
    private List<Flights> flightList;

    public ArchivedAdapter(Context context, List<Flights> flightList) {
        this.context = context;
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.archive_flight_card, parent, false);
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

        // Set the date and number of people missed
        holder.flightDate.setText(flight.getArrivalDate() != null ? flight.getArrivalDate().toString() : "N/A");
        holder.peopleMissed.setText(String.format("%d People Missed", flight.getPeopleMissed()));
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView flightNumber, departureCity, arrivalCity, departureTime, arrivalTime,
                duration, aircraftModel, economyPrice, businessPrice, flightDate, peopleMissed;

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
            flightDate = itemView.findViewById(R.id.flight_date); // New TextView for flight date
            peopleMissed = itemView.findViewById(R.id.people_missed); // New TextView for number of people missed
        }
    }
}
