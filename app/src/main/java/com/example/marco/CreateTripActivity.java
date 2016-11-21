package com.example.marco;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TimePicker;

public class CreateTripActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // O método setCurrentHour está obsoleto, mas o setHour é novo demais pro nosso minSDK
        // Precisa testar em outros aparelhos!!!
        if (currentapiVersion >= 23){
            timePicker.setHour(8);
        } else{
            timePicker.setCurrentHour(8);
        }

    }
    public void confirmTrip(View view) {
        Intent intent = new Intent(this, TravelCardsActivity.class);
        startActivity(intent);
    }
}
