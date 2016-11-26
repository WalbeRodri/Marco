package com.example.marco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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
    private FirebaseAuth mAuth;
    private DataBaseMarco banco;

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
        if (currentapiVersion >= 23) {
            inicio.setHour(8);
            fim.setHour(17);
        } else {
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
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            Date data = new Date(partida.getYear(), partida.getMonth(), partida.getDayOfMonth());
            Date cheg = new Date(chegada.getYear(), chegada.getMonth(), chegada.getDayOfMonth());
            String enderecoAux;
            banco = new DataBaseMarco();

            String timeStart;
            String timeEnd;
            if (currentapiVersion >= 23) {
                timeStart = inicio.getHour() + ":" + inicio.getMinute();
                timeEnd = fim.getHour() + ":" + fim.getMinute();
            } else {
                timeStart = inicio.getCurrentHour() + ":" + inicio.getCurrentMinute();
                timeEnd = fim.getCurrentHour() + ":" + fim.getCurrentMinute();
            }
            //criacao do objeto que cria a viagem.
            if (nome.getText().length() != 0 && orcamento.getText().length() != 0) {

                if (endereco.getText().length() != 0) {
                    enderecoAux = "";
                } else
                    enderecoAux = endereco.getText().toString();

                Double orcam = Double.parseDouble(orcamento.getText().toString());
                Trip viagem = new Trip(nome.getText().toString(), orcam, "Recife", data, cheg, enderecoAux, null, timeStart, timeEnd);
                banco.createTrip(viagem);
                Intent intent = new Intent(this, TravelCardsActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(CreateTripActivity.this, "Preencha todos os campos obrigatórios para prosseguir", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CreateTripActivity.this, "Realize Login para poder criar seu Roteiro personalizado", Toast.LENGTH_SHORT).show();
        }

    }
}
