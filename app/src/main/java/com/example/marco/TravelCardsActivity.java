package com.example.marco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
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

import adapters.LocalAdapter;
import adapters.PerfilAdapter;
import base.Local;
import base.Perfil;
import database.DataBaseMarco;
import rotisserie.Decision;

public class TravelCardsActivity extends AppCompatActivity {
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

        dbMarco = new DataBaseMarco(); //inicializando banco de dados
        handleInstanceState(savedInstanceState);
        setUpFirebase();
        setUpAdapter();


        // arrayLocal = decisao.choice();
        setContentView(R.layout.activity_travel_cards);
        final RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);


        ArrayList<Local> arrayLocal = new ArrayList<Local>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Perfil perfil1 = perfilAdapter.getItems().get(0);
                if(perfil1.getPreferences()==null){
                    Toast.makeText(TravelCardsActivity.this, "Você não tem nenhum gosto escolhido :'(", Toast.LENGTH_SHORT).show();
                } else {
                    decisao = new Decision(mAdapterLocal, perfil1.getPreferences().getPreferences());
                    CreateViagemAdapter ca = new CreateViagemAdapter(decisao.choice());
                    recList.setAdapter(ca);
                }
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


                //Toast.makeText(DecisaoLocal.this, "Passei", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(DecisaoLocal.this, "Falha conexão!", Toast.LENGTH_SHORT).show();
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    /*
        private ArrayList<Local> createList() {
            Decision decisao = new Decision();
            return decisao.choice();
        }
            */
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