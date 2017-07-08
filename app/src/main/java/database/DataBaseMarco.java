package database;

import android.content.Context;

import com.example.marco.DBOpenHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import base.*;

public class DataBaseMarco {
    private DBOpenHelper dbOpenHelper;
    private FirebaseDatabase database;
    private DatabaseReference tripRef;  //nó de viagem
    private DatabaseReference perfilRef; // nó de perfil
    private DatabaseReference localRef;
    private FirebaseUser user;

    //  private DatabaseReference agendaRef;
    public DataBaseMarco() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uidUser = auth.getCurrentUser().getUid();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        localRef = database.getReference("local");
        tripRef = database.getReference("trip").child(uidUser);
        perfilRef = database.getReference("perfil").child(uidUser);

    }

    public DataBaseMarco(Context ctx) {
        dbOpenHelper = new DBOpenHelper(ctx);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uidUser = auth.getCurrentUser().getUid();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        localRef = database.getReference("local");
        tripRef = database.getReference("trip").child(uidUser);
        perfilRef = database.getReference("perfil").child(uidUser);



    }


    public void createPerfil(Perfil perfil) {
        perfilRef.push().setValue(perfil);
    }

    public void createTrip(Trip trip) {
        tripRef.push().setValue(trip);

    }

    public FirebaseUser getUser(){
        return user;
    }

    public void editTrip(Trip trip,String key){
        tripRef.child(key).setValue(trip);
    }


    public void editPerfil(Perfil perfil,String key){
        perfilRef.child(key).setValue(perfil);
    }

    public void deleteTrip(String key) {
        tripRef.child(key).removeValue();
    }

    public void deletePerfil(String key) {
        perfilRef.child(key).removeValue();
    }


    public Query recoverPerfil() {;
        return perfilRef;
    }

    public Query recoverTrips() {
        return tripRef;
    }

    public Query recoverLocal() {
        return  localRef;
    }
}
