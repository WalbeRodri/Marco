package com.example.marco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ListTripsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trips);

        RecyclerView recList = (RecyclerView) findViewById(R.id.tripsList);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        ListTripsAdapter ca = new ListTripsAdapter(createList(3));
        recList.setAdapter(ca);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<TripInfo> createList(int size) {

        List<TripInfo> result = new ArrayList<TripInfo>();
        for (int i = 1; i <= size; i++) {
            TripInfo ci = new TripInfo();
            ci.nome = TripInfo.NOME + i;
            ci.data_viagem = TripInfo.DATA_VIAGEM + i;
            ci.cidade = TripInfo.CIDADE + i;
            result.add(ci);
        }
        return result;
    }

    public void createTrip(View view) {
        Intent intent = new Intent(this, CreateTripActivity.class);
        startActivity(intent);
    }

    public void openTrip(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
