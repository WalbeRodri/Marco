package com.example.marco;

/**
 * Created by Walber Rodrigues on 10/11/2016.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    public void onBindViewHolder(final ContactViewHolder contactViewHolder, int i) {

        Local ci = contactList.get(i);

        contactViewHolder.vNome.setText(ci.getName());
        contactViewHolder.vDesc.setText(ci.getDescription());
      //  contactViewHolder.vHora.setText(ci.getSchedule()+"");
        String preco = ci.getPrice()+"";


        contactViewHolder.vEntradas.setText(preco);


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(ci.getImage());



        final long tamanhoMax = 500 * 1024;
        storageRef.getBytes(tamanhoMax).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                contactViewHolder.vImagem.setImageBitmap(bm);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

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
        protected TextView vHora;
        protected TextView vEntradas;

        public ContactViewHolder(View v) {
            super(v);
            vImagem = (ImageView) v.findViewById(R.id.imagem);
            vNome =  (TextView) v.findViewById(R.id.txtNome);
            vDesc = (TextView)  v.findViewById(R.id.txtDesc);
            vHora = (TextView)  v.findViewById(R.id.txtHora);
            vEntradas = (TextView) v.findViewById(R.id.txtPreco);

        }
    }
}