package com.example.marco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void listTrips(View view) {
        Intent intent = new Intent(this, ListTripsActivity.class);
        startActivity(intent);
    }

    public void gostos(View view) {
        Intent intent = new Intent(this, GostosActivity.class);
    }

    public void createTrip(View view) {
        Intent intent = new Intent(this, CreateTripActivity.class);

        startActivity(intent);
    }

}
