package com.example.marco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.StringBuilderPrinter;

import android.widget.Toast;

/**
 * Created by Walber Rodrigues on 22/06/2017.
 */

public class DBOpenHelper extends SQLiteOpenHelper {
    final  static String TABLE_NAME = "links";
    final  static String _ID = "_id";
    final static String LINK = "link";
    final static String PUB_DATE = "pubDate";
    final static String DESCRIPTION = "Description" ;
    final static String TRIP = "Viagem";
    final static String AGENDA = "Agenda";
    final static String PERFIL = "Perfil";
    final static String PREFERENCES = "Preferencias";
    final static String LOCAL = "Local_Visitado";
    final static String DAY = "Dia";
    final static String GOSTOS = "PREF";
    final static String TB_GOSTOS = "TB_PREF";

    //    final static String[] columns = { _ID, TITLE, PUB_DATE, LINK, DESCRIPTION };
    final private static String NAME = "trip_db";
    final private static Integer VERSION = 1;
    final private Context mContext;
    final private static String CREATE_TRIP = "CREATE TABLE IF NOT EXISTS "+TRIP+" (" + _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "Nome"+ " TEXT, "+
                    "Destino" +" TEXT, "+
                    "Orcamento"+" INTEGER, "+
                    "HoraInicio"+" TEXT, "+
                    "DataInicio"+" TEXT, "+
                    "DataFinal"+" TEXT, "+
                    "HoraFinal" +" TEXT)";
    final private static String CREATE_PREFERENCES = "CREATE TABLE IF NOT EXISTS "+PREFERENCES+" (" + _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "Preferencia"+" TEXT NOT NULL)";
    final private static String CREATE_LOCAL = "CREATE TABLE IF NOT EXISTS "+LOCAL+" (" + _ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "Nome"+ " TEXT)";
    final private static String CREATE_GOSTOS = "CREATE TABLE IF NOT EXISTS "+TB_GOSTOS+" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            GOSTOS+ " TEXT NOT NULL)";

    public DBOpenHelper(Context ctx){
        super(ctx, NAME, null, VERSION);
        mContext = ctx;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TRIP);
        db.execSQL(CREATE_PREFERENCES);
        db.execSQL(CREATE_LOCAL);
        Log.d("CRIEI GOSTO","GOSTOS");
        db.execSQL(CREATE_GOSTOS);
//        db.execSQL(CREATE_AGENDA);
//        db.execSQL(CREATE_PERFIL);
//        db.execSQL(CREATE_DAY);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
