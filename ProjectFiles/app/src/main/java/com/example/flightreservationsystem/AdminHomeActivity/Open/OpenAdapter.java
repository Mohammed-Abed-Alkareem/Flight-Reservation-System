package com.example.flightreservationsystem.AdminHomeActivity.Open;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightreservationsystem.models.Flights;
import com.example.flightreservationsystem.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OpenAdapter extends RecyclerView.Adapter<OpenAdapter.FlightViewHolder> {
    private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMATTER_TIME = DateTimeFormatter.ofPattern("HH:mm");

    private Context context;
    private List<Flights> flightList;

    public OpenAdapter(Context context, List<Flights> flightList) {
        this.context = context;
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.open_flight_card, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flights flight = flightList.get(position);

        holder.flightNumber.setText("Flight #: " + flight.getFlightNumber());
        holder.departureCity.setText("Dep: " + flight.getDepartureCity());
        holder.arrivalCity.setText("Arr: " + flight.getArrivalCity());

        // Handle departure and arrival times
        holder.departureTime.setText(flight.getDepartureTime() != null
                ? flight.getDepartureTime().format(FORMATTER_TIME)
                : "N/A");

        holder.arrivalTime.setText(flight.getArrivalTime() != null
                ? flight.getArrivalTime().format(FORMATTER_TIME)
                : "N/A");

        holder.duration.setText(flight.getDuration());
        holder.aircraftModel.setText(flight.getAircraftModel());
        holder.maxSeats.setText(String.valueOf(flight.getMaxSeats()));
        holder.currentReservations.setText(String.valueOf(flight.getCurrentReservations()));

        // Handle booking open date
        holder.bookingOpenDate.setText(flight.getDepartureDate() != null
                ? flight.getDepartureDate().format(FORMATTER_DATE)
                : "N/A");

        holder.extraBaggagePrice.setText(String.format("$%.2f", flight.getExtraBaggagePrice()));
        holder.economyPrice.setText(String.format("$%.2f", flight.getEconomyPrice()));
        holder.businessPrice.setText(String.format("$%.2f", flight.getBusinessPrice()));
        holder.isRecurrent.setText(flight.getIsRecurrent());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView flightNumber, departureCity, arrivalCity, departureTime, arrivalTime,
                duration, aircraftModel, economyPrice, businessPrice, maxSeats, currentReservations,
                bookingOpenDate, extraBaggagePrice, isRecurrent;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            flightNumber = itemView.findViewById(R.id.flight_number);
            departureCity = itemView.findViewById(R.id.departure_city);
            arrivalCity = itemView.findViewById(R.id.arrival_city);
            departureTime = itemView.findViewById(R.id.departure_time);
            arrivalTime = itemView.findViewById(R.id.arrival_time);
            duration = itemView.findViewById(R.id.duration);
            aircraftModel = itemView.findViewById(R.id.aircraft_model);
            maxSeats = itemView.findViewById(R.id.max_seats);
            currentReservations = itemView.findViewById(R.id.current_reservations);
            bookingOpenDate = itemView.findViewById(R.id.booking_open_date);
            extraBaggagePrice = itemView.findViewById(R.id.extra_baggage_price);
            economyPrice = itemView.findViewById(R.id.economy_price);
            businessPrice = itemView.findViewById(R.id.business_price);
            isRecurrent = itemView.findViewById(R.id.is_recurrent);
        }
    }
}
