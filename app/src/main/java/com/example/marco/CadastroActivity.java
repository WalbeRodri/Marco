package com.example.marco;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.api.model.StringList;

import java.util.Calendar;
import java.util.Date;

import base.Perfil;
import database.dataBaseMarco;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {
    protected Perfil perfil;
    private dataBaseMarco dbMarco;
    private static final String TAG = "CadastroActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    EditText nome;
    EditText genero;
    EditText email;
    DatePicker data;
    EditText senha1;
    Button cadastroButton;
    EditText senha2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        //dbMarco = new dataBaseMarco();
        nome = (EditText) findViewById(R.id.nomeCadastro);
        genero = (EditText) findViewById(R.id.genero);
        email = (EditText) findViewById(R.id.emailCadastro);
        data = (DatePicker) findViewById(R.id.dataNascimento);
        senha1 = (EditText) findViewById(R.id.senha1);
        cadastroButton = (Button) findViewById(R.id.confirmaCadastroButton);
        senha2 = (EditText) findViewById(R.id.senha2);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    dbMarco = new dataBaseMarco();
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Date date = new Date(data.getYear(), data.getMonth(), data.getDayOfMonth());
                    perfil = new Perfil(nome.getText().toString(), email.getText().toString(), genero.getText().toString(), date);
                    dbMarco.createPerfil(perfil);
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        cadastroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailT = email.getText().toString();
                String passwordT = senha1.getText().toString();

                // trims the input
                emailT = emailT.trim();
                passwordT = passwordT.trim();

                Toast.makeText(CadastroActivity.this, emailT, Toast.LENGTH_SHORT).show();
                Toast.makeText(CadastroActivity.this, passwordT, Toast.LENGTH_SHORT).show();
                if (nome.getText().length() == 0 || genero.getText().length() == 0 || email.getText().length() == 0) {
                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else if (!senha1.getText().toString().equals(senha2.getText().toString())) {
                    Toast.makeText(CadastroActivity.this, "As senhas precisam ser iguais", Toast.LENGTH_SHORT).show();
                } else{
                    mAuth.createUserWithEmailAndPassword(emailT, passwordT)
                            .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(CadastroActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}