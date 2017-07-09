package com.example.marco;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import adapters.PerfilAdapter;
import base.Perfil;
import base.Preferences;
import database.DataBaseMarco;

import static com.example.marco.DBOpenHelper.GOSTOS;
import static com.example.marco.DBOpenHelper.TB_GOSTOS;

public class PerfilActivity extends AppCompatActivity {

    Preferences preferencias = new Preferences();
    private ContentValues values;
    private DataBaseMarco dbMarco;
    private Query mQuery; //caminho de Local
    private PerfilAdapter perfilAdapter; //adapter de perfil , não usado
    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private DBOpenHelper dbOpenHelper;

    ToggleButton tbBar;
    ToggleButton tbMuseu;
    ToggleButton tbPraca;
    ToggleButton tbPraia;
    ToggleButton tbTeatro;
    ToggleButton tbComida;
    ToggleButton tbMusica;
    ToggleButton tbArtesanato;
    ToggleButton tbCinema;
    ToggleButton tbIgreja;

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
        tbMusica = (ToggleButton) findViewById(R.id.tbMusica);
        tbArtesanato = (ToggleButton) findViewById(R.id.tbArtesanato);
        tbCinema = (ToggleButton) findViewById(R.id.tbCinema);
        tbIgreja = (ToggleButton) findViewById(R.id.tbIgreja);
        values = new ContentValues();
        dbMarco = new DataBaseMarco(); //inicializando banco de dados
        dbOpenHelper = new DBOpenHelper(this);

        setUpFirebase();
        setUpAdapter();

        tbBar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Bar");
                    writeDB_Single("Bar");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Bar");
                    delete_item("Bar");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbComida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Comida");
                    writeDB_Single("Comida");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Comida");
                    delete_item("Comida");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbMuseu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Museu");
                    writeDB_Single("Museu");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Museu");
                    delete_item("Museu");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbPraca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Praca");
                    writeDB_Single("Praca");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    delete_item("Praca");
                    Gostos.remove("Praca");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbPraia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Praia");
                    writeDB_Single("Praia");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    delete_item("Praia");
                    Gostos.remove("Praia");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbTeatro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Teatro");
                    writeDB_Single("Teatro");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Teatro");
                    delete_item("Teatro");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbCinema.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Cinema");
                    writeDB_Single("Cinema");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Cinema");
                    delete_item("Cinema");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbArtesanato.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Artesanato");
                    writeDB_Single("Artesanato");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Artesanato");
                    delete_item("Artesanato");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbMusica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Musica");
                    writeDB_Single("Musica");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Musica");
                    delete_item("Musica");
                    preferencias.setPreferences(Gostos);
                }
            }
        });

        tbIgreja.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencias.addPreferences("Igreja");
                    writeDB_Single("Igreja");
                } else {
                    List<String> Gostos = preferencias.getPreferences();
                    Gostos.remove("Igreja");
                    delete_item("Igreja");
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
                if (perfilAdapter != null) {
                    if (perfilAdapter.getItems() != null) {
                        if (perfilAdapter.getItems().size() > 0) {
                            Perfil perfil1 = perfilAdapter.getItems().get(0);
                            Preferences pref = perfil1.getPreferences();
                            if (pref != null) {
                                if (pref.getPreferences().contains("Teatro")) {
                                    tbTeatro.setChecked(true);

                                }

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
                            if (pref.getPreferences().contains("Musica")) {
                                tbMusica.setChecked(true);

                            }
                            if (pref.getPreferences().contains("Artesanato")) {
                                tbArtesanato.setChecked(true);

                            }
                            if (pref.getPreferences().contains("Igreja")) {
                                tbIgreja.setChecked(true);

                            }
                            if (pref.getPreferences().contains("Cinema")) {
                                tbCinema.setChecked(true);

                            }
                        }
                    } else {
                        Perfil perfil = new Perfil(dbMarco.getUser().getDisplayName(), dbMarco.getUser().getEmail());
                        dbMarco.createPerfil(perfil);
                    }
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public void ConfirmaGosto(View view) {
        Perfil perfil = perfilAdapter.getItems().get(0);
        perfil.setPreferences(preferencias);
        String chave = perfilAdapter.getKeys().get(0);
        dbMarco.editPerfil(perfil, chave);
        this.finish();
    }
    protected void writeDB_Single(String new_value){
        values.put(GOSTOS, new_value);
        dbOpenHelper.getWritableDatabase().insert(TB_GOSTOS, null, values);
        values.clear();
    }
    protected void delete_item(String itemRow){
        dbOpenHelper.getReadableDatabase().delete(TB_GOSTOS,GOSTOS+" = '"+itemRow+"' ",null);
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