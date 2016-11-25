package com.example.marco;

/**
 * Created by Walber Rodrigues on 10/11/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import base.Local;

public class CreateViagemAdapter extends RecyclerView.Adapter<CreateViagemAdapter.ContactViewHolder> {

    private ArrayList<Local> contactList;

    public CreateViagemAdapter(ArrayList<Local> contactList) {
        this.contactList = contactList;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {

        Local ci = contactList.get(i);
        contactViewHolder.vImagem.setImageResource(R.mipmap.museu);
        contactViewHolder.vNome.setText(ci.getName());
        contactViewHolder.vDesc.setText(ci.getDescription());
        contactViewHolder.vSchedule.setText(ci.getSchedule());
        contactViewHolder.vTimeSpend.setText(ci.getTimeSpend());
        contactViewHolder.vCategorias.setText(ci.getDescription());

//        contactViewHolder.vNome.setText(ci.nome + " " + ci.desc);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected ImageView vImagem;
        protected TextView vNome;
        protected TextView vDesc;
        protected TextView vSchedule;
        protected TextView vCategorias;
        protected TextView vTimeSpend;

        public ContactViewHolder(View v) {
            super(v);
            vImagem = (ImageView) v.findViewById(R.id.imagem);
            vNome =  (TextView) v.findViewById(R.id.txtNome);
            vDesc = (TextView)  v.findViewById(R.id.txtDesc);
            vSchedule = (TextView)  v.findViewById(R.id.txtSchedule);
            vCategorias = (TextView) v.findViewById(R.id.txtCategoria);
            vTimeSpend = (TextView) v.findViewById(R.id.txtTimeSpend);

        }
    }
}