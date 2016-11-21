package com.example.marco;

import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.Query;

import java.util.List;
import java.util.*;

import adapters.PerfilAdapter;
import base.Perfil;
import base.Preferences;
import database.dataBaseMarco;

public class PerfilActivity extends AppCompatActivity {

    Preferences preferencias = new Preferences();

    private dataBaseMarco dbMarco;

    private Query mQuery; //caminho de Local
    private PerfilAdapter perfilAdapter; //adapter de perfil , não usado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ToggleButton tbBar = (ToggleButton) findViewById(R.id.tbBar);
        ToggleButton tbMuseu = (ToggleButton) findViewById(R.id.tbMuseu);
        ToggleButton tbPraca = (ToggleButton) findViewById(R.id.tbPraca);
        ToggleButton tbPraia = (ToggleButton) findViewById(R.id.tbPraia);
        ToggleButton tbTeatro = (ToggleButton) findViewById(R.id.tbTeatro);
        ToggleButton tbComida = (ToggleButton) findViewById(R.id.tbComida);

        dbMarco = new dataBaseMarco(); //inicializando banco de dados
        setUpFirebase();
        setUpAdapter();

        tbBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Bar");
                } else {
                    List <String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Bar");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbComida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Comida");
                } else {
                    List <String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Comida");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbMuseu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Museu");
                } else {
                    List <String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Museu");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbPraca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Praca");
                } else {
                    List <String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Praca");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbPraia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Praia");
                } else {
                    List <String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Praia");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbTeatro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Teatro");
                } else {
                    List <String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Teatro");
                    preferencias.setPreferences(Gostos);
                }
            }
        });
    }

    public void ConfirmaGosto(View view) {
        Perfil perfil  = perfilAdapter.getItems().get(0);
        perfil.setPreferences(preferencias);
        String chave = perfilAdapter.getKeys().get(0);
        dbMarco.editPerfil(perfil,chave);
    }

    private void setUpFirebase() {
        mQuery = dbMarco.recoverPerfil(); //ACESANDO NÓS DE CONSULTA
    }

    public void setUpAdapter() {
        perfilAdapter = new PerfilAdapter(mQuery, Perfil.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        perfilAdapter.destroy();
    }
}