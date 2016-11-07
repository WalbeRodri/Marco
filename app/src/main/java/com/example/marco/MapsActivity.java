package com.example.marco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/*
    Baseado nos tutoriais abaixo
    http://wptrafficanalyzer.in/blog/drawing-driving-route-directions-between-two-locations-using-google-directions-in-google-map-android-api-v2/
    http://wptrafficanalyzer.in/blog/route-between-two-locations-with-waypoints-in-google-map-android-api-v2/
*/


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    ArrayList<LatLng> markerPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Adicionando toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initializing
        markerPoints = new ArrayList<LatLng>();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        // Posição inicial arbitrária, está jogando pro Marco Zero; isso deve ficar dinâmico
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-8.0631534, -34.8711168)));
        // Valor arbitrário de zoom; isso precisa ficar dinâmico de acordo com a distância entre os pontos
        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


        // Roteiro 1
        /*
        LatLng cais_sertao = new LatLng(-8.0598311, -34.8699349);
        LatLng rock_ribs = new LatLng(-8.0639807, -34.8715432);
        LatLng seu_boteco = new LatLng(-8.0637666, -34.8713085);
        LatLng paco_frevo = new LatLng(-8.0613593, -34.8715237);
        LatLng torre_malakoff = new LatLng(-8.060726, -34.870684);
        LatLng parque_esculturas = new LatLng(-8.063934, -34.869167);

        map.addMarker(new MarkerOptions()
                .position(cais_sertao)
                .title("Cais do Sertão"));
        map.addMarker(new MarkerOptions()
                .position(seu_boteco)
                .title("Almoço - Seu Boteco"));
        map.addMarker(new MarkerOptions()
                .position(paco_frevo)
                .title("Paço do Frevo"));
        map.addMarker(new MarkerOptions()
                .position(torre_malakoff)
                .title("Torre Malakoff"));
        map.addMarker(new MarkerOptions()
                .position(parque_esculturas)
                .title("Parque de Esculturas Francisco Brennand"));
        map.addMarker(new MarkerOptions()
                .position(rock_ribs)
                .title("Jantar - Rock & Ribs"));

        markerPoints.add(cais_sertao);
        markerPoints.add(rock_ribs);
        markerPoints.add(seu_boteco);
        markerPoints.add(paco_frevo);
        markerPoints.add(torre_malakoff);
        markerPoints.add(parque_esculturas);
        */

        // Roteiro 2
        LatLng instituto_brennand = new LatLng(-8.0650775, -34.9628285);
        LatLng castellus = new LatLng(-8.0640736, -34.9624825);
        LatLng oficina_ceramica = new LatLng(-8.0527211, -34.9738586);
        LatLng paco_alfandega = new LatLng(-8.0648251, -34.873804);

        map.addMarker(new MarkerOptions()
                .position(instituto_brennand));
        map.addMarker(new MarkerOptions()
                .position(castellus));
        map.addMarker(new MarkerOptions()
                .position(oficina_ceramica));
        map.addMarker(new MarkerOptions()
                .position(paco_alfandega));

        markerPoints.add(instituto_brennand);
        markerPoints.add(castellus);
        markerPoints.add(oficina_ceramica);
        markerPoints.add(paco_alfandega);

        // Getting URL to the Google Directions API
        String url = getDirectionsUrl();

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
    }

    private String getDirectionsUrl(){

        // Origin of route
        LatLng origin = markerPoints.get(0);
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        int last_item = markerPoints.size()-1;
        LatLng destination = markerPoints.get(last_item);
        String str_dest = "destination="+destination.latitude+","+destination.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Modo de transporte
        String mode = "mode=driving";
        // String mode = "mode=walking";

        // Waypoints
        String waypoints = "";
        for(int i=1; i<last_item; i++){
            LatLng point  = (LatLng) markerPoints.get(i);
            if(i==1)
                waypoints = "waypoints=";
            waypoints += point.latitude + "," + point.longitude + "|";
        }

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor+"&"+mode+"&"+waypoints;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        Toast.makeText( getApplicationContext(), url, Toast.LENGTH_LONG ).show();

        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception download url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {

            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(2);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }
}
