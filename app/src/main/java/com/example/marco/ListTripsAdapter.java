package com.example.marco;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.Trip;


public class ListTripsAdapter extends RecyclerView.Adapter<com.example.marco.ListTripsAdapter.ContactViewHolder>  {
    private ContactViewHolder cv;
    private List<Trip> tripList;
    private DBOpenHelper dbHelper;
    private int position;
    private final OnFragmentInteractionListener oilfil;
    public ListTripsAdapter(ArrayList<Trip> tripList, OnFragmentInteractionListener oifil) {
        this.tripList = tripList;
        this.oilfil = oifil;
    }




    @Override
    public int getItemCount() {
        return tripList.size();
    }


    public Object getItem( int position ) {
        return tripList.get( position );
    }
    public int getPosition(){
        return this.position;
    }

    @Override
    public void onBindViewHolder(final com.example.marco.ListTripsAdapter.ContactViewHolder contactViewHolder, final int i) {
        Trip ci = tripList.get(i);
        contactViewHolder.viagem = ci;
        contactViewHolder.vNome.setText(ci.getName());
        String data = ci.getStartDate().getDay()+"/"+ci.getStartDate().getMonth()+"/"+ci.getStartDate().getYear();
        contactViewHolder.vDataViagem.setText(data);
        contactViewHolder.vCidade.setText(ci.getDestiny());
        contactViewHolder.vHoraInicio.setText(ci.getTimeStart());
        contactViewHolder.vHoraFim.setText(ci.getTimeEnd());
        position = i;
        contactViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(oilfil!=null){
                    tripList.remove(contactViewHolder.viagem);

                    oilfil.onDeleteClick(contactViewHolder.viagem);
                    notifyDataSetChanged();
                }
            }
        });
        contactViewHolder.btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                oilfil.onInfoClick(contactViewHolder.viagem);
            }
        });


    }

    @Override
    public com.example.marco.ListTripsAdapter.ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, final int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trip_view, viewGroup, false);
        return new com.example.marco.ListTripsAdapter.ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vNome;
        protected TextView vDataViagem;
        protected TextView vCidade;
        protected TextView vHoraInicio;
        protected TextView vHoraFim;
        protected Button btnDelete;
        protected Button btnInfo;
        private Trip viagem;
        public ContactViewHolder(View v) {
            super(v);

            vNome =  (TextView) v.findViewById(R.id.txtNome);
            vDataViagem =  (TextView) v.findViewById(R.id.txtData);
            vCidade =  (TextView) v.findViewById(R.id.txtCidade);
            vHoraInicio = (TextView) v.findViewById(R.id.txtHoraInicio);
            vHoraFim = (TextView) v.findViewById(R.id.txtHoraFim);
            btnDelete = (Button) v.findViewById(R.id.delete);
            btnInfo = (Button) v.findViewById(R.id.info);

        }
    }

}