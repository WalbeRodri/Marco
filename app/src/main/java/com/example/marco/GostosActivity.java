package com.example.marco;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ToggleButton;

public class GostosActivity extends AppCompatActivity {
    private ToggleButton teatroButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gostos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable teatro = (Drawable) getResources().getDrawable(R.mipmap.teatro);
        final Drawable teatroSelected = (Drawable) getResources().getDrawable(R.mipmap.teatroselct);
        final Drawable bar = (Drawable) getResources().getDrawable(R.mipmap.bar);
        final Drawable barSelected = (Drawable) getResources().getDrawable(R.mipmap.barselect);
        final Drawable praia = (Drawable) getResources().getDrawable(R.mipmap.praia);
        final Drawable praiaSelected = (Drawable) getResources().getDrawable(R.mipmap.praiaselect);
        final Drawable praca = (Drawable) getResources().getDrawable(R.mipmap.praca);
        final Drawable pracaSelected = (Drawable) getResources().getDrawable(R.mipmap.pracaselect);


        teatroButton = (ToggleButton) findViewById(R.id.tbTeatro);
        teatroButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (teatroButton.getBackground().equals(teatro)) {
                    teatroButton.setBackgroundDrawable(teatroSelected);
                } else {
                    teatroButton.setBackgroundDrawable(teatro);
                }
            }
        });
        final ToggleButton pracaButton = (ToggleButton) findViewById(R.id.tbPraca);
        pracaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pracaButton.getBackground().equals(praca)) {
                    pracaButton.setBackgroundDrawable(praca);
                } else {
                    pracaButton.setBackgroundDrawable(pracaSelected);

                }
            }
        });
        final ToggleButton praiaButton = (ToggleButton) findViewById(R.id.tbPraia);
        praiaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (praiaButton.getBackground().equals(praia)) {
                    praiaButton.setBackgroundDrawable(praiaSelected);
                } else {
                    praiaButton.setBackgroundDrawable(praia);
                }
            }
        });
        final ToggleButton barButton = (ToggleButton) findViewById(R.id.tbBar);
        barButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (barButton.getBackground().equals(bar)) {
                    barButton.setBackgroundDrawable(barSelected);
                } else {
                    barButton.setBackgroundDrawable(bar);
                }
            }
        });
    }
}
