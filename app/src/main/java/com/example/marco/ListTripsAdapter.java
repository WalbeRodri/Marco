package com.example.marco;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import base.Trip;


public class ListTripsAdapter extends RecyclerView.Adapter<com.example.marco.ListTripsAdapter.ContactViewHolder> {

    private List<Trip> tripList;
    public ListTripsAdapter(ArrayList<Trip> tripList) {
        this.tripList = tripList;
    }


    @Override
    public int getItemCount() {
        return tripList.size();
    }


    public Object getItem( int position ) {
        return tripList.get( position );
    }


    @Override
    public void onBindViewHolder(com.example.marco.ListTripsAdapter.ContactViewHolder contactViewHolder, int i) {
        Trip ci = tripList.get(i);
        contactViewHolder.vNome.setText(ci.getName());
        String data = ci.getStartDate().getDay()+"/"+ci.getStartDate().getMonth()+"/"+ci.getStartDate().getYear();
        contactViewHolder.vDataViagem.setText(data);
        contactViewHolder.vCidade.setText(ci.getDestiny());
        contactViewHolder.vHoraInicio.setText(ci.getTimeStart());
        contactViewHolder.vHoraFim.setText(ci.getTimeEnd());
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
        protected TextView vHoraInicio;
        protected TextView vHoraFim;

        public ContactViewHolder(View v) {
            super(v);
            vNome =  (TextView) v.findViewById(R.id.txtNome);
            vDataViagem =  (TextView) v.findViewById(R.id.txtData);
            vCidade =  (TextView) v.findViewById(R.id.txtCidade);
            vHoraInicio = (TextView) v.findViewById(R.id.txtHoraInicio);
            vHoraFim = (TextView) v.findViewById(R.id.txtHoraFim);
        }
    }

}