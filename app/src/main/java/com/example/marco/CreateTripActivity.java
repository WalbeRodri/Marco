package com.example.marco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Date;

import base.Trip;
import database.DataBaseMarco;

public class CreateTripActivity extends AppCompatActivity {
    protected EditText nome;
    protected DatePicker partida, chegada;
    protected TimePicker inicio, fim;
    protected EditText orcamento;
    protected EditText endereco;
    protected int currentapiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inicio = (TimePicker) findViewById(R.id.timeBeginPicker);
        fim = (TimePicker) findViewById(R.id.timeEndPicker);
        fim.setIs24HourView(true);
        inicio.setIs24HourView(true);

        currentapiVersion = android.os.Build.VERSION.SDK_INT;
        // O método setCurrentHour está obsoleto, mas o setHour é novo demais pro nosso minSDK
        // Precisa testar em outros aparelhos!!!
        if (currentapiVersion >= 23){
            inicio.setHour(8);
            fim.setHour(17);
        } else{
            inicio.setCurrentHour(8);
            inicio.setCurrentHour(17);
        }
        nome = (EditText) findViewById(R.id.tripNameField);
        partida = (DatePicker) findViewById(R.id.tripStartDateField);
        chegada = (DatePicker) findViewById(R.id.tripFinishDateField);
        orcamento = (EditText) findViewById(R.id.dailyBudgetField);
        endereco = (EditText) findViewById(R.id.hostAdressField);

    }
    protected void confirmTrip(View view) {
        Date data = new Date(partida.getYear(), partida.getMonth(), partida.getDayOfMonth());
        Date cheg = new Date(chegada.getYear(), chegada.getMonth(), chegada.getDayOfMonth());
        DataBaseMarco banco = new DataBaseMarco();


        String timeStart;
        String timeEnd;
        if(currentapiVersion >= 23){
             timeStart = inicio.getHour()+":"+inicio.getMinute();
             timeEnd = fim.getHour()+":"+fim.getMinute();
        } else {
            timeStart = inicio.getCurrentHour()+":"+inicio.getCurrentMinute();
            timeEnd = fim.getCurrentHour()+":"+ fim.getCurrentMinute();
        }
        Double orcam = Double.parseDouble(orcamento.getText().toString());
        //criacao do objeto que cria a viagem.
        Trip viagem = new Trip( nome.getText().toString(), orcam, "Recife", data , cheg, endereco.getText().toString(),null,timeStart, timeEnd  );
        banco.createTrip(viagem);

        Intent intent = new Intent(this, TravelCardsActivity.class);
        startActivity(intent);
    }
}
