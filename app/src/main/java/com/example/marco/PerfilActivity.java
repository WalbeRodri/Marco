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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    ToggleButton tbBar;
    ToggleButton tbMuseu;
    ToggleButton tbPraca;
    ToggleButton tbPraia;
    ToggleButton tbTeatro;
    ToggleButton tbComida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        tbBar = (ToggleButton) findViewById(R.id.tbBar);
        tbMuseu = (ToggleButton) findViewById(R.id.tbMuseu);
        tbPraca = (ToggleButton) findViewById(R.id.tbPraca);
        tbPraia = (ToggleButton) findViewById(R.id.tbPraia);
        tbTeatro = (ToggleButton) findViewById(R.id.tbTeatro);
        tbComida = (ToggleButton) findViewById(R.id.tbComida);

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

        //final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference ref = database.getReference("https://marco-52cd1.firebaseio.com/");

        // Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Perfil perfil1 = perfilAdapter.getItems().get(0);
                Preferences pref = perfil1.getPreferences();
                if(pref != null) {
                    Toast.makeText(PerfilActivity.this, "Foi", Toast.LENGTH_SHORT).show();
                    if (pref.getPreferences().contains("Teatro")) {
                        tbTeatro.setChecked(true);
                    }
                    if (pref.getPreferences().contains("Praia")) {
                        tbPraia.setChecked(true);
                    }
                    if (pref.getPreferences().contains("Praca")) {
                        tbPraca.setChecked(true);
                    }
                    if (pref.getPreferences().contains("Bar")) {
                        tbBar.setChecked(true);
                    }
                    if (pref.getPreferences().contains("Comida")) {
                        tbComida.setChecked(true);
                    }
                    if (pref.getPreferences().contains("Museu")) {
                        tbMuseu.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PerfilActivity.this, "NAUM Foi", Toast.LENGTH_SHORT).show();
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        //;
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