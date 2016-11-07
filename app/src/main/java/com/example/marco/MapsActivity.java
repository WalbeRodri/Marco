package com.example.marco;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng paco_alfandega = new LatLng(-8.0648251, -34.873804);
        LatLng marco_zero = new LatLng(-8.0631534, -34.8711168);
        LatLng torre_malakoff = new LatLng(-8.060726, -34.870684);

        mMap.addMarker(new MarkerOptions()
                .position(paco_alfandega)
                .title("Paço Alfandega"));

        mMap.addMarker(new MarkerOptions()
                .position(marco_zero)
                .title("Marco Zero")
                .snippet("Preço: Gratuito\nHorário: 24h"));

        mMap.addMarker(new MarkerOptions()
                .position(torre_malakoff)
                .title("Torre Malakoff"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(marco_zero));

        // Valor arbitrário de zoom; isso precisa ficar dinâmico de acordo com a distância entre os pontos
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

        // Adiciona linha direta entre os pontos (não é a rota)
        mMap.addPolyline(
                (new PolylineOptions())
                        .add(paco_alfandega, marco_zero, torre_malakoff)
                        .width(3)
                        .color(R.color.orange)  // não está pegando a cor certa, não sei pq
        );

    }

}
