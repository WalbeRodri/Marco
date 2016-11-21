package com.example.marco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import base.Perfil;
import database.dataBaseMarco;

public class CadastroActivity extends AppCompatActivity {
    protected Perfil perfil;
    private dataBaseMarco dbMarco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        dbMarco = new dataBaseMarco();
        EditText nome = (EditText) findViewById(R.id.nomeCadastro);
        EditText genero = (EditText) findViewById(R.id.genero);
        EditText email = (EditText) findViewById(R.id.emailCadastro);
        DatePicker data = (DatePicker) findViewById(R.id.dataNascimento);
        EditText senha1 = (EditText) findViewById(R.id.senha1);
        EditText senha2 = (EditText) findViewById(R.id.senha2);
        if (nome.getText().length() == 0 || genero.getText().length() == 0 || email.getText().length() == 0) {
            Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        } else if (!senha1.getText().toString().equals(senha2.getText().toString())) {
            Toast.makeText(CadastroActivity.this, "As senhas precisam ser iguais", Toast.LENGTH_SHORT).show();
        } else {

            Date date = new Date(data.getYear(), data.getMonth(), data.getDayOfMonth());
            perfil = new Perfil(nome.getText().toString(), email.getText().toString(), genero.getText().toString(), date, null);

        }
    }


    public void confirmaCadastro() {
        dbMarco.createPerfil(perfil);
    }
}
