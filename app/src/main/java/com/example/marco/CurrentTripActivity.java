package com.example.marco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.marco.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import android.os.AsyncTask;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import adapters.TripAdapter;
import base.Trip;
import database.DataBaseMarco;

public class CurrentTripActivity extends AppCompatActivity {
    private final static String SAVED_ADAPTER_ITEMS_TRIP = "SAVED_ADAPTER_ITEMS_TRIP";
    private final static String SAVED_ADAPTER_KEYS_TRIP = "SAVED_ADAPTER_KEYS_TRIP";
    private FirebaseAuth f_auth;
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
    private DataBaseMarco dbmarco;
    private boolean viagemAtual;
    Query myquery;
    TripAdapter tripsAdapter;
    ArrayList<Trip> listaViagem;
    ArrayList<String> listaChave;
    Date dia_atual;
   // private TripAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        f_auth = FirebaseAuth.getInstance();
        if (f_auth.getCurrentUser() == null) {
            Toast.makeText(CurrentTripActivity.this,"Faça login para acessar sua viagem atual",Toast.LENGTH_LONG).show();
        }else{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_current_trip);
            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);
            dbmarco = new DataBaseMarco();
            handleInstanceState(savedInstanceState);
            viagemAtual = false;
        }
    }

    private void setUpFirebase() {
        myquery = dbmarco.recoverTrips(); //ACESANDO NÓS DE CONSULTA
    }

    public void setUpAdapter() {
        tripsAdapter = new TripAdapter(myquery, Trip.class, listaViagem, listaChave);
    }
    private void handleInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null &&
                savedInstanceState.containsKey(SAVED_ADAPTER_ITEMS_TRIP) &&
                savedInstanceState.containsKey(SAVED_ADAPTER_KEYS_TRIP)) {
            listaViagem = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_ADAPTER_ITEMS_TRIP));
            listaChave = savedInstanceState.getStringArrayList(SAVED_ADAPTER_KEYS_TRIP);
        } else {
            listaViagem = new ArrayList<Trip>(); //INICIALIZANDO VARIAVEIS
            listaChave = new ArrayList<String>();
        }
    }
    protected void onStart(){
        /*try{
            dia_atual = new Date(); //pega o dia atual sem precisar passar parâmetros
            Query take_last_trip_day = dbref.child("trip").child(f_auth.getCurrentUser().getUid()).orderByKey();
            take_last_trip_day.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot!=null){
                        Trip viagem_atual = dataSnapshot.getValue(Trip.class);
                        if(dia_atual.getDay()==viagem_atual.getStart_date().getDay()
                                && dia_atual.getMonth() == viagem_atual.getStart_date().getMonth()
                                && dia_atual.getYear()==viagem_atual.getStart_date().getYear()){

                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch(NullPointerException e){
            e.getMessage();
        }*/
        Calendar hoje = Calendar.getInstance();

        int ano = hoje.get(Calendar.YEAR);
        //Toast.makeText(getApplicationContext(),"Ano: "+ano,Toast.LENGTH_SHORT).show();
        int mes = hoje.get(Calendar.MONTH);
        //Toast.makeText(getApplicationContext(),"Mês: "+mes,Toast.LENGTH_SHORT).show();
        int dia = hoje.get(Calendar.DAY_OF_MONTH);
        //Toast.makeText(getApplicationContext(),"Dia: "+dia,Toast.LENGTH_SHORT).show();
        mes = mes+1;
        Log.d("DATA NO CALENDAR",dia+"/"+mes+"/"+ano);
        for(int i=0;i<listaViagem.size();i++){
            Date dt = listaViagem.get(i).getStart_date();
            Log.d("DATAS NO ADAPTER",dt.getDate()+"/"+dt.getMonth()+"/"+dt.getYear());
            if(dt.getDate()==dia&&
                    dt.getYear()==ano&&
                    dt.getMonth()==mes){
                viagemAtual = true;
            }
        }

        if(viagemAtual) {
            Intent j = new Intent(getApplicationContext(), TravelCardsActivity.class);
            startActivity(j);
        }

        super.onStart();
    }

    private class ProcuraViagem extends AsyncTask<Date,Void,Void>{
        @Override
        protected void onPreExecute(){
            setUpFirebase();
            setUpAdapter();

            Calendar hoje = Calendar.getInstance();

            int ano = hoje.get(Calendar.YEAR);
            //Toast.makeText(getApplicationContext(),"Ano: "+ano,Toast.LENGTH_SHORT).show();
            int mes = hoje.get(Calendar.MONTH);
            //Toast.makeText(getApplicationContext(),"Mês: "+mes,Toast.LENGTH_SHORT).show();
            int dia = hoje.get(Calendar.DAY_OF_MONTH);
            //Toast.makeText(getApplicationContext(),"Dia: "+dia,Toast.LENGTH_SHORT).show();
            mes = mes+1;
            dia_atual = new Date(ano,mes,dia);
        }
        @Override
        protected Void doInBackground(Date... params) {
            for(int i=0;i<listaViagem.size();i++){
                Date dt = listaViagem.get(i).getStart_date();
                Log.d("DATAS NO ADAPTER",dt.getDate()+"/"+dt.getMonth()+"/"+dt.getYear());
                if(dt.getDate()==dia_atual.getDate()&&
                        dt.getYear()==dia_atual.getYear()&&
                        dt.getMonth()==dia_atual.getMonth()){
                    viagemAtual = true;
                }
            }
            return null;
        }
    }
}
