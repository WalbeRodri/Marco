package com.example.marco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import adapters.LocalAdapter;
import adapters.PerfilAdapter;
import base.Local;
import base.Perfil;
import database.DataBaseMarco;
import rotisserie.Decision;

public class DecisaoLocal extends AppCompatActivity {

    private final static String SAVED_ADAPTER_ITEMS_LOCAL = "SAVED_ADAPTER_ITEMS_LOCAIS";
    private final static String SAVED_ADAPTER_KEYS_LOCAL = "SAVED_ADAPTER_KEYS_LOCAIS";

    private DataBaseMarco dbMarco;
    private Query mQuery; //caminho de Local
    private LocalAdapter localAdapter; //adapter

    private Query mQuery2; //caminho de Perfil
    private PerfilAdapter perfilAdapter; //adapter de perfil , não usado

    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    private ArrayList<Local> mAdapterLocal; //armazena lista de locais
    private ArrayList<String> mAdapterLocalKeys; //chaves de locais

    private Decision decisao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decisao_local);
        Toast.makeText(DecisaoLocal.this, "Se voce estava tentando ir pra Historico de viagem, descomente as linhas 117 e 118 da MainActivity \n\nLove, Jil", Toast.LENGTH_SHORT).show();
        dbMarco = new DataBaseMarco(); //inicializando banco de dados
        handleInstanceState(savedInstanceState);
        setUpFirebase();
        setUpAdapter();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Perfil perfil1 = perfilAdapter.getItems().get(0);

                //PRINTS DE TESTE, FAVOR NAO TIRAR
                /*for(int i = 0; i < perfil1.getPreferences().getPreferences().size(); i++){
                    Toast.makeText(DecisaoLocal.this, perfil1.getPreferences().getPreferences().get(i), Toast.LENGTH_SHORT).show();
                }*/
                /*for (int i = 0; i < mAdapterLocal.size(); i++) {
                    if (mAdapterLocal.get(i).getType() != null)
                        Toast.makeText(DecisaoLocal.this, mAdapterLocal.get(i).getType(), Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DecisaoLocal.this, "TEVE NULL", Toast.LENGTH_SHORT).show();
                }*/

                decisao = new Decision(mAdapterLocal, perfil1.getPreferences().getPreferences());

                List<Local> ola = decisao.choice();
                //Toast.makeText(DecisaoLocal.this, "Passei", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < ola.size(); i++) {
                    Local loc = ola.get(i);
                    Toast.makeText(DecisaoLocal.this, loc.getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DecisaoLocal.this, "NAUM Foi", Toast.LENGTH_SHORT).show();
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    private void setUpFirebase() {
        mQuery = dbMarco.recoverLocal(); //ACESANDO NÓS DE CONSULTA
        mQuery2 = dbMarco.recoverPerfil();
    }

    public void setUpAdapter() {
        localAdapter = new LocalAdapter(mQuery, Local.class, mAdapterLocal, mAdapterLocalKeys);
        perfilAdapter = new PerfilAdapter(mQuery2, Perfil.class);
    }

    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS_LOCAL) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS_LOCAL)) {
            mAdapterLocal = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS_LOCAL));
            mAdapterLocalKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS_LOCAL);
        } else {
            mAdapterLocal = new ArrayList<Local>(); //INICIALIZANDO VARIAVEIS
            mAdapterLocalKeys = new ArrayList<String>();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return item.getItemId() == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    // Saving the list of items and keys of the items on rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS_LOCAL, Parcels.wrap(localAdapter.getItems())); //SETANDO LOCAL QUE IRÁ ARMAZENAR
        outState.putStringArrayList(SAVED_ADAPTER_KEYS_LOCAL, localAdapter.getKeys());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localAdapter.destroy(); //destroindo os adapter criados
        perfilAdapter.destroy();
    }
}