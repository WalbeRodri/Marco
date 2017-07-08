package com.example.marco;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;

import android.support.v4.os.AsyncTaskCompat;
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

import com.example.marco.map.ViagemAtualActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;

import base.Trip;

import static com.example.marco.DBOpenHelper.GOSTOS;

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
                ,new String[]{"DataInicio"}
                ,null
                ,new String[]{}
                ,null
                ,null
                ,null);
        String dtInText="";
        int i=-1;
        try{
            i = q.getColumnIndex("DataInicio");
            if(q.moveToFirst()){
                dtInText=q.getString(i);
            }else{
                Log.d("Cursor","carai nenhum");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("Cursor",i+" "+dtInText);
        q.close();
        Intent intent = new Intent(this, ViagemAtualActivity.class);
        startActivity(intent);
    }

    public void createTrip(View view) {
        Intent intent = new Intent(this, CreateTripActivity.class);
        startActivity(intent);
    }
    public void povoaDesgraça(int i){
        for(int z = 0; z<i; z++){
            ContentValues values = new ContentValues();
            values.put("Nome","viage"+z);
            values.put("DataInicio","dd/MM/yyyy"+z);
            values.put("Destino","Recife"+z);
            values.put("HoraInicio",8);
            values.put("HoraFinal",18);
             dbHelper.getWritableDatabase().insert(DBOpenHelper.TRIP,null,values);

        }
        Cursor cursor;
        cursor = dbHelper.getWritableDatabase().query(DBOpenHelper.TRIP
                ,new String[]{"DataInicio"}
                ,null
                ,new String[]{}
                ,null
                ,null
                ,null);
        Log.d("COLUNAS: ", ""+cursor.getCount());
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
