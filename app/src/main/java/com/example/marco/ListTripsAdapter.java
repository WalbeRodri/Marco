package com.example.marco;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.Trip;


public class ListTripsAdapter extends RecyclerView.Adapter<com.example.marco.ListTripsAdapter.ContactViewHolder> {

    private List<Trip> tripList;
    public ListTripsAdapter(ArrayList<Trip> contactList) {
        this.tripList = contactList;
    }


    @Override
    public int getItemCount() {
        return tripList.size();
    }

    @Override
    public void onBindViewHolder(com.example.marco.ListTripsAdapter.ContactViewHolder contactViewHolder, int i) {
        Trip ci = tripList.get(i);
        contactViewHolder.vNome.setText(ci.getName());
        contactViewHolder.vDataViagem.setText(ci.getTimeStar());
        contactViewHolder.vCidade.setText(ci.getAdress());
    }

    @Override
    public com.example.marco.ListTripsAdapter.ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.trip_view, viewGroup, false);

        return new com.example.marco.ListTripsAdapter.ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vNome;
        protected TextView vDataViagem;
        protected TextView vCidade;


        public ContactViewHolder(View v) {
            super(v);
            vNome =  (TextView) v.findViewById(R.id.txtNome);
            vDataViagem =  (TextView) v.findViewById(R.id.txtData);
            vCidade =  (TextView) v.findViewById(R.id.txtCidade);

        }
    }
}