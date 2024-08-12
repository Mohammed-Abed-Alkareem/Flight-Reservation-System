package com.example.flightreservationsystem.RestApi;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.flightreservationsystem.models.Flights;

import com.example.flightreservationsystem.MainActivity;

import java.util.List;

public class ConnectionAsyncTask extends AsyncTask<String, String, String> {
    Activity activity;
    public ConnectionAsyncTask(Activity activity) {
        this.activity = activity;
    }
    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {
        return HttpManager.getData(params[0]);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        // Parse JSON data
        List<Flights> flights = FlightJsonParser.getObjectFromJson(s);

        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.insertFlightsDB(flights);


    }
}