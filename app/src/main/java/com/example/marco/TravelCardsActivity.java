package com.example.marco;

import android.*;
import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

import com.example.marco.map.MapsActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.io.Console;
import java.util.ArrayList;

import adapters.LocalAdapter;
import adapters.PerfilAdapter;
import base.Local;
import base.Perfil;
import database.DataBaseMarco;
import rotisserie.Decision;

import android.location.Location;
import android.location.LocationManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;


public class TravelCardsActivity extends AppCompatActivity{
    private final static String SAVED_ADAPTER_ITEMS_LOCAL = "SAVED_ADAPTER_ITEMS_LOCAIS";
    private final static String SAVED_ADAPTER_KEYS_LOCAL = "SAVED_ADAPTER_KEYS_LOCAIS";

    private String startTime;
    private String endTime;
    private Double orcamento;

    private DataBaseMarco dbMarco;
    private Query mQuery; //caminho de Local
    private LocalAdapter localAdapter; //adapter

    private Query mQuery2; //caminho de Perfil
    private PerfilAdapter perfilAdapter; //adapter de perfil , não usado

    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    private ArrayList<Local> mAdapterLocal; //armazena lista de locais
    private ArrayList<String> mAdapterLocalKeys; //chaves de locais

    private CreateViagemAdapter ca;
    private Decision decisao = null;
    private ArrayList<Local> locals;
    static TravelCardsActivity travelCardsActivity;
    private GoogleApiClient playService;
    TravelCardsReceiver tvc;
    private boolean achouLocal;
    private boolean concedePerm = false;
    private static final int ID_PERMISSION_REQUEST = 123;
    private static final String TEM_PERMISSAO = "ComPerm";
    private Bundle estado;
    private Geofence geof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.estado = savedInstanceState;
        if(estado!=null){
            concedePerm = estado.getBoolean(TEM_PERMISSAO,false);

        }
        if(todasPermitidas(getRequisitos())){
            executando();
        }else if(!concedePerm){
            ActivityCompat.requestPermissions(this,grupoRequisitos(getRequisitos()),ID_PERMISSION_REQUEST);
        }


    }
    protected void executando(){
        dbMarco = new DataBaseMarco(); //inicializando banco de dados
        handleInstanceState(estado);
        setUpFirebase();
        setUpAdapter();


        // arrayLocal = decisao.choice();
        setContentView(R.layout.activity_travel_cards);
        final RecyclerView recList = (RecyclerView) findViewById(R.id.cardList);
        Intent intent = getIntent();
        final String startTime = intent.getStringExtra("TRIP_TIME_START");
        final String endTime = intent.getStringExtra("TRIP_TIME_END");
        final double orcamento = intent.getDoubleExtra("TRIP_ORCAMENTO", 100);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        tvc = new TravelCardsReceiver();
        ArrayList<Local> arrayLocal = new ArrayList<Local>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Perfil perfil1 = perfilAdapter.getItems().get(0);
                if (perfil1.getPreferences() == null) {
                    Toast.makeText(TravelCardsActivity.this, "Você não tem nenhum gosto escolhido :'(", Toast.LENGTH_SHORT).show();
                } else {
                    decisao = new Decision(mAdapterLocal, perfil1.getPreferences().getPreferences(), startTime, endTime, orcamento);
                    locals = decisao.choice();
                    ca = new CreateViagemAdapter(locals, getApplicationContext());
                    recList.setAdapter(ca);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent("RODAR_FUSED_LOCATION")); //manda broadcast quando finaliza query
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
//                Toast.makeText(DecisaoLocal.this, "Falha conexão!", Toast.LENGTH_SHORT).show();
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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getBaseContext());
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
    @Override
    protected void onResume(){
        LocalBroadcastManager.getInstance(this).registerReceiver(tvc,
                new IntentFilter("RODAR_FUSED_LOCATION"));
        super.onResume();
    }

    protected void onStart(){
        if(playService!=null){
            playService.reconnect(); //como o comando connect é chamado no onReceive, faço a reconexão para que sempre possa verificar
        }
        super.onStart();
    }


    public class TravelCardsReceiver extends BroadcastReceiver implements GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener{ //broadcast receiver que vai tratar o fused location ao fim da query
        private LocationRequest locreq;

        @Override
        public void onReceive(Context context, Intent intent) {
            playService = new GoogleApiClient.Builder(context)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
            playService.connect();
        }

        @Override
        @SuppressWarnings("MissingPermission")
        public void onConnected(@Nullable Bundle bundle) {
            achouLocal = false;
            //loc=LocationServices.FusedLocationApi.getLastLocation(playService);
            locreq = new LocationRequest()
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                    .setInterval(1800000)
                    .setFastestInterval(900000); //procurar no máximo a cada 30min, ou no mínimo a cada 15min
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    playService, locreq, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            //loc = location;
                            for(int i=0;i<locals.size();i++){
                                float[] results = new float[3];
                                for(Local l: locals){
                                    Location.distanceBetween(location.getLatitude(),location.getLongitude(),l.getLatitude(),l.getLongitude(),results);
                                    //a cada mudança de localização, calcula-se a distância em metros entre o local atual e os locais pertencentes ao roteiro
                                    Log.d("Geofence Check",""+results[0]);
                                    if(results[0]<100){ //a distância menor do que 100 metros é suficiente, no nosso caso, para dizer que a pessoa está de fato no local
                                        //a distância é armazenada no índice 0 segundo a documentação
                                        //tentar mexer no check do xml da view
                                        Toast.makeText(getApplicationContext(),""+results[0],Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                          /*  if(achouLocal){
                                Toast.makeText(getApplicationContext(),"Passando por um dos locais!",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getApplicationContext(),"Não achei :c "+location.getLongitude()+" "+location.getLatitude(),Toast.LENGTH_LONG).show();
                            }*/

                        }
                    }
            );
        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }
    }


    protected boolean temPermissao(String perm) {
        return(ContextCompat.checkSelfPermission(this, perm)== PackageManager.PERMISSION_GRANTED);
    }

    private String[] grupoRequisitos(String[] reqs){
        ArrayList<String> resultado = new ArrayList<>();
        for(String permissao : reqs){
            if(!temPermissao(permissao)){
                resultado.add(permissao);
            }
        }
        return (resultado.toArray(new String[resultado.size()]));
    }

    private boolean todasPermitidas(String[] perms){
        for(String permitida: perms){
            if(!temPermissao(permitida)){
                return false;
            }
        }
        return true;
    }

    private String[] getRequisitos(){
        return(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION});
    }

    @Override
    public void onRequestPermissionsResult(int codigoReq,
                                           @NonNull String[] permissoes,
                                           @NonNull int[] resultados) {
        concedePerm=false;

        if (codigoReq==ID_PERMISSION_REQUEST) {
            if (todasPermitidas(getRequisitos())) {
                executando();//prossegue com o onCreate original
            }
            else {
                Toast.makeText(getApplicationContext(),"Sem as permissões de local!",Toast.LENGTH_LONG).show();
                finish();
            }
        }
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
    protected void onPause() {
        // Activity não visível, faz unregister do broadcast receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(tvc);
        super.onPause();
    }

    protected void onStop(){
        if(playService!=null){
            playService.disconnect();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localAdapter.destroy(); //destruindo os adapter criados
        perfilAdapter.destroy();
    }

    public void openMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        Bundle b = new Bundle();
        b.putParcelableArrayList("FILES_TO_SEND", locals);
        intent.putExtras(b);
        startActivity(intent);
    }

}