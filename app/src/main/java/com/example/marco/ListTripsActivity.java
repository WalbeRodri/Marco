package com.example.marco;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.example.marco.map.MapsActivity;

import android.location.LocationManager;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Date;

import adapters.TripAdapter;
import base.Trip;
import database.DataBaseMarco;

public class ListTripsActivity extends AppCompatActivity {
    private final static String SAVED_ADAPTER_ITEMS_TRIP = "SAVED_ADAPTER_ITEMS_TRIP";
    private final static String SAVED_ADAPTER_KEYS_TRIP = "SAVED_ADAPTER_KEYS_TRIP";

    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private Query mQuery;
    private DataBaseMarco dbMarco;
    private TripAdapter tripsAdapter;
    private ArrayList<Trip> mAdapterTrip; //armazena lista de locais
    private ArrayList<String> mAdapterTripKeys; //chaves de locais
    protected Date dia_atual;
    //private LocationManager mgr;
    private DBOpenHelper dbHelper;
    private ArrayList<Trip> viagensBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips);
//        handleInstanceState(savedInstanceState);
//        setUpFirebase();
//        setUpAdapter();
        dbHelper = new DBOpenHelper(this);
      //  mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        final RecyclerView recList = (RecyclerView) findViewById(R.id.tripsList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        //tripsAdapter = new ListTripsAdapter(createList(3));

        viagensBD = new ArrayList<>();
        Cursor q = dbHelper.getReadableDatabase().query(
                DBOpenHelper.TRIP
                ,new String[]{"Nome","DataInicio","Orcamento","HoraInicio","Horafinal"}
                ,null
                ,new String[]{}
                ,null
                ,null
                ,"DataInicio");
        try{
            //contagem de itens do cursor, a cargo de debug
            int j=q.getCount();
            Log.d("Cursor",j+"");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while(q.moveToNext()) {
                Trip tripAux = new Trip(
                        q.getString(q.getColumnIndex("Nome")),
                        q.getDouble(q.getColumnIndex("Orcamento")),
                        "Recife",
                        sdf.parse(q.getString(q.getColumnIndex("DataInicio"))),
                        null,
                        q.getString(q.getColumnIndex("HoraFinal")),
                        q.getString(q.getColumnIndex("HoraInicio")));
                viagensBD.add(tripAux);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        q.close();
        ListTripsAdapter ca = new ListTripsAdapter(viagensBD);
        recList.setAdapter(ca);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //tratar a adição de viagens que ja estejam no firebase e pass-las ao bd
//                decisao = new Decision(mAdapterLocal, perfil1.getPreferences().getPreferences());
//                ListTripsAdapter ca = new ListTripsAdapter(mAdapterTrip);
//                ListTripsAdapter ca = new ListTripsAdapter(viagensBD);
//                recList.setAdapter(ca);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(DecisaoLocal.this, "Falha conexão!", Toast.LENGTH_SHORT).show();
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    public void createTrip(View view) {
        Intent intent = new Intent(this, CreateTripActivity.class);
        startActivity(intent);
    }

    public void openTrip(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
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


    /*
        private ArrayList<Local> createList() {
            Decision decisao = new Decision();
            return decisao.choice();
        }
            */
    private void setUpFirebase() {
        mQuery = dbMarco.recoverTrips(); //ACESANDO NÓS DE CONSULTA
    }

    public void setUpAdapter() {
        tripsAdapter = new TripAdapter(mQuery, Trip.class, mAdapterTrip, mAdapterTripKeys);
    }

    // Restoring the item list and the keys of the items: they will be passed to the adapter
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS_TRIP) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS_TRIP)) {
            mAdapterTrip = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS_TRIP));
            mAdapterTripKeys = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS_TRIP);
        } else {
            mAdapterTrip = new ArrayList<Trip>(); //INICIALIZANDO VARIAVEIS
            mAdapterTripKeys = new ArrayList<String>();
        }
    }

    // Saving the list of items and keys of the items on rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_ADAPTER_ITEMS_TRIP, Parcels.wrap(tripsAdapter.getItems())); //SETANDO LOCAL QUE IRÁ ARMAZENAR
        outState.putStringArrayList(SAVED_ADAPTER_KEYS_TRIP, tripsAdapter.getKeys());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        tripsAdapter.destroy(); //destruindo os adapter's criados
    }
}
