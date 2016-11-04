package com.example.marco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListTripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips);
    }

    public void createTrip(View view) {
        Intent intent = new Intent(this, CreateTripActivity.class);
        startActivity(intent);
    }
}
