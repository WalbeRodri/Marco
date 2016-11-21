package com.example.marco;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by paim on 21/11/16.
 */

public class ListTripsAdapter extends RecyclerView.Adapter<com.example.marco.ListTripsAdapter.ContactViewHolder> {

    private List<TripInfo> contactList;

    public ListTripsAdapter(List<TripInfo> contactList) {
        this.contactList = contactList;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(com.example.marco.ListTripsAdapter.ContactViewHolder contactViewHolder, int i) {
        TripInfo ci = contactList.get(i);
        contactViewHolder.vNome.setText(ci.nome);
        contactViewHolder.vDataViagem.setText(ci.data_viagem);
        contactViewHolder.vCidade.setText(ci.cidade);
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