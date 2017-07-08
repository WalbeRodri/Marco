package com.example.marco;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marco.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import android.location.Location;
import android.location.LocationManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import adapters.LocalAdapter;
import adapters.TripAdapter;
import base.Local;
import base.Trip;
import database.DataBaseMarco;

public class CurrentTripActivity extends AppCompatActivity{
    private final static String SAVED_ADAPTER_ITEMS_TRIP = "SAVED_ADAPTER_ITEMS_TRIP";
    private final static String SAVED_ADAPTER_KEYS_TRIP = "SAVED_ADAPTER_KEYS_TRIP";
    private FirebaseAuth f_auth;
    private DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
    private DataBaseMarco dbmarco;
    Query myquery;
    LocalAdapter localAdapter;
    ArrayList<Local> listaLocais;
    private boolean viagemAtual;
    ArrayList<Trip> listaViagem;
    ArrayList<String> listaChave;
    Date dia_atual;
    ArrayList<Date> dataViagens;
    TextView tv;
   // private TripAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        f_auth = FirebaseAuth.getInstance();
        if (f_auth.getCurrentUser() == null) {
            Toast.makeText(CurrentTripActivity.this,"Faça login para acessar sua viagem atual",Toast.LENGTH_LONG).show();
        }else{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_current_trip);
            Log.d("onCreate",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar);
            dbmarco = new DataBaseMarco();
           // handleInstanceState(savedInstanceState);
            //setUpFirebase();
            listaLocais = new ArrayList<>();
            setUpAdapter();
            dataViagens = new ArrayList<Date>();
            //tv = (TextView) findViewById(R.id.infor);
           /* Calendar hoje = Calendar.getInstance(); pegar dia de hoje, NÃO EXCLUIR

            int ano = hoje.get(Calendar.YEAR);
            //Toast.makeText(getApplicationContext(),"Ano: "+ano,Toast.LENGTH_SHORT).show();
            int mes = hoje.get(Calendar.MONTH);
            //Toast.makeText(getApplicationContext(),"Mês: "+mes,Toast.LENGTH_SHORT).show();
            int dia = hoje.get(Calendar.DAY_OF_MONTH);
            //Toast.makeText(getApplicationContext(),"Dia: "+dia,Toast.LENGTH_SHORT).show();
            mes = mes+1;
            dia_atual = new Date(ano,mes,dia);*/
        }
    }
    /*private String name;
    private String description;
    private double price;
    private String schedule;
    private double timeSpend;
    private double latitude;
    private double longitude;
    private String type;
    private String image;
    private String horario;
    private String general_category;*/

    private void createLocais(){
        for(int i=0;i<10;i++){
            Local l = new Local("goiaba"+i,"qualquer coisa"+i,10.0,"hfg"+i,2.0,(-8.06405238),(-34.87158716),"comida"+i,"hau"+i,"num sei"+i);
            listaLocais.add(l);
        }
    }

    private void setUpFirebase() {
        myquery = dbmarco.recoverLocal(); //ACESANDO NÓS DE CONSULTA
        myquery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("Database",dataSnapshot.toString());
                Map<String, Local> td = (HashMap<String,Local>) dataSnapshot.getValue();
                //ArrayList<Local> a = new ArrayList<Local>(td.values());
                Local l = (Local) td.values();
                //Log.d("Teste",a.toString());
                listaLocais.add(l);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setUpAdapter() {
        createLocais();
        localAdapter = new LocalAdapter(myquery, Local.class, listaLocais, listaChave);
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
        /*try{ antigo método mas preservar
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
       /* myquery.addChildEventListener(new ChildEventListener() { query de pegar as datas, NÃO EXCLUIR
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int mes = 0;
                int dia = 0;
                int ano = 0;
                String viagem = dataSnapshot.getValue().toString();
                StringTokenizer tokeni = new StringTokenizer(viagem,",");
                Log.d("Opa",viagem);
                ArrayList<String> parteTrip = new ArrayList<String>();
                while(tokeni.hasMoreTokens()){
                    parteTrip.add(tokeni.nextToken());
                }
                Log.d("parteTrip",parteTrip.size()+"");
                for(int i=0;i<parteTrip.size();i++){
                    String elemento = parteTrip.get(i);
                    if(elemento.contains("month")){
                        String a = elemento.substring(7,elemento.length());
                        Log.d("mes",elemento);
                        mes = Integer.parseInt(a);
                    }else if(elemento.contains("date=")&& !elemento.contains("start")){
                        String a = elemento.substring(6,elemento.length()-1);
                        dia = Integer.parseInt(a);
                    }else if(elemento.contains("year")){
                        String a = elemento.substring(6,elemento.length());
                        ano = Integer.parseInt(a);
                    }
                }
                //String aux1 = viagem.substring(8,10); //budget
                //  int budget = Integer.parseInt(aux1);
                // String aux2 = viagem.substring(20,25); //destiny
                //  Trip v_aux = dataSnapshot.getValue(Trip.class);


                Date d = new Date(ano,(mes+1),dia);
                if(d.getMonth()==dia_atual.getMonth()
                        && d.getDate()==dia_atual.getDate()
                        && d.getYear()==dia_atual.getYear()){
                    viagemAtual=true;
                    Log.d("ViagemAtual","achei!");
                }
                Log.d("Data no db",d.toString());
                dataViagens.add(d);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                //Toast.makeText(getApplicationContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_LONG).show();
                //listaViagem.add(v_aux);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
       // new ProcuraViagem().execute(dia_atual);


        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    private class ProcuraViagem extends AsyncTask<Date,Void,Void>{


        @Override
        protected void onPreExecute(){
            Log.d("teste","no preExecute");
            //setUpAdapter();

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

            Log.d("ASYNC",dataViagens.size()+"");
            for(int i=0;i<dataViagens.size();i++){
                Date dt = dataViagens.get(i);
                Log.d("DATAS NO ADAPTER",dt.getDate()+"/"+dt.getMonth()+"/"+dt.getYear());
                if(dt.getDate()==dia_atual.getDate()&&
                        dt.getYear()==dia_atual.getYear()&&
                        dt.getMonth()==dia_atual.getMonth()){
                    viagemAtual = true;
                }


                    //tv.setText();
                    /*Intent j = new Intent(getApplicationContext(), TravelCardsActivity.class);
                    startActivity(j);*/
            }
            return null;
        }
    }
}
