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

import java.util.List;
import java.util.*;

import base.Preferences;

public class PerfilActivity extends AppCompatActivity {

    Preferences preferencias = new Preferences();

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
        for(int i=0; i < preferencias.getPreferences().size(); i++) {
            String ola = preferencias.getPreferences().get(i);
            Toast.makeText(PerfilActivity.this, ola, Toast.LENGTH_SHORT).show();
        }
    }
}