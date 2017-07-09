package com.example.marco;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DBOpenHelper dbHelper;
    final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        Log.d("Cria DB",">>>>>>>>>>>>>>>>>>>>>>>");
        dbHelper = new DBOpenHelper(this);
        Log.d("Cria DB",">>>>>>>>>>>>>>>>>>>>>>>");
//        povoaDesgraça(10);
        if (mAuth.getCurrentUser() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main2);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_reset) {
            //handle Reset
        } else if (id == R.id.nav_feedback) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.example.marco")); //redireciona para a página do app na play store
            startActivity(intent);

        } else if (id == R.id.nav_sobre) {

        } else if (id == R.id.nav_sign_in_out) {
            if (mAuth.getCurrentUser() == null) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Logout Succeed", Toast.LENGTH_SHORT).show();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void listTrips(View view) {

        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, ListTripsActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Realize Login para ver suas Viagens antigas", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
//        Intent intent = new Intent(this, DecisaoLocal.class);
//        startActivity(intent);

    }

    public void currentTrip(View view) throws NullPointerException{
        Cursor q = dbHelper.getReadableDatabase().query(
                DBOpenHelper.TRIP
                ,new String[]{"DataInicio","Orcamento","HoraInicio","Horafinal"}
                ,null
                ,new String[]{}
                ,null
                ,null
                ,null);
        String dtInText="";
        Calendar hoje = Calendar.getInstance();
        int ano = hoje.get(Calendar.YEAR);
        //Toast.makeText(getApplicationContext(),"Ano: "+ano,Toast.LENGTH_SHORT).show();
        int mes = hoje.get(Calendar.MONTH);
        //Toast.makeText(getApplicationContext(),"Mês: "+mes,Toast.LENGTH_SHORT).show();
        int dia = hoje.get(Calendar.DAY_OF_MONTH);
        //Toast.makeText(getApplicationContext(),"Dia: "+dia,Toast.LENGTH_SHORT).show();
        mes = mes+1;
        Date dia_atual = new Date(ano,mes,dia);
        Log.d("Data atual",dia_atual.getDate()+"/"+dia_atual.getMonth()+"/"+dia_atual.getYear());
        //int i=-1;
        boolean viagemAtual = false;
        String horaini = "";
        String horafim = "";
        Double orcamento = 0.0;
        try{
            //i = q.getColumnIndex("DataInicio");
            int j=q.getCount();
            Log.d("Cursor",j+"");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            while(q.moveToNext()){
                dtInText = q.getString(q.getColumnIndex("DataInicio"));
                Log.d("Data do cursor",dtInText);
                Date d = sdf.parse(dtInText);
                horaini = q.getString(q.getColumnIndex("HoraInicio"));
                horafim = q.getString(q.getColumnIndex("HoraFinal"));
                orcamento = q.getDouble(q.getColumnIndex("Orcamento"));
                Log.d("Data do db",d.getDate()+"/"+d.getMonth()+"/"+d.getYear());
                if(d.getYear()==dia_atual.getYear()
                        && (d.getMonth()+1)==dia_atual.getMonth()
                        && d.getDate()==dia_atual.getDate()){

                    iniciaTravelCards(horaini,horafim,orcamento);
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(viagemAtual)
            iniciaTravelCards(horaini,horafim,orcamento);
        else
            Toast.makeText(getApplicationContext(),"Não há nenhuma viagem em andamento",Toast.LENGTH_SHORT).show();
           //Log.d("Cursor",i+" "+dtInText);
    }

    public void createTrip(View view) {
        Intent intent = new Intent(this, CreateTripActivity.class);
        startActivity(intent);
    }

    public void iniciaTravelCards(String horaini, String horafim, Double orcamento){
        Intent intent = new Intent(getApplicationContext(),TravelCardsActivity.class);
        intent.putExtra("TRIP_TIME_START",horaini);
        intent.putExtra("TRIP_TIME_END", horafim);
        intent.putExtra("TRIP_ORCAMENTO", orcamento);
        startActivity(intent);
    }
    public void gerenciaGostos(View view) {
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(this, PerfilActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Realize Login para escolher suas preferências", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
