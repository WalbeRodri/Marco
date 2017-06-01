package com.example.marco;

/**
 * Created by Walber Rodrigues on 10/11/2016.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import base.Local;

import static com.facebook.FacebookSdk.getApplicationContext;

public class CreateViagemAdapter extends RecyclerView.Adapter<CreateViagemAdapter.ContactViewHolder> implements Serializable{

    static ArrayList<Local> contactList;
    static Context travelCA;
    public CreateViagemAdapter(ArrayList<Local> contactList, Context TCA) {
        this.contactList = contactList;
        this.travelCA = TCA;
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder contactViewHolder, int i) {
        Random r = new Random();
        Local ci = contactList.get(i);
        contactViewHolder.rBar.setStepSize(1);
        contactViewHolder.rBar.setMax(5);
        contactViewHolder.rBar.setNumStars(5);
        contactViewHolder.rBar.setRating(r.nextInt(5));
        contactViewHolder.vNome.setText(ci.getName());
        contactViewHolder.vDesc.setText(ci.getDescription());
        contactViewHolder.vCategorias.setText(ci.getType());

        // Formatando para exibir Schedule como horário
        float time = Float.valueOf(ci.getHorario());
        int hours = (int) time;
        int minutes = (int) (60 * (time - hours));
        contactViewHolder.vSchedule.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + " -");

        // Formatando para exibir timeSpend como tempo
        double duration = Double.valueOf(ci.getTimespend());
        hours = (int) duration;
        minutes = (int) (60 * (duration - hours));
        String str_duration = "Tempo Estimado: ";
        if (hours > 0) {
            str_duration += (String.valueOf(hours) + "h");
        }
        if (minutes > 0) {
            str_duration += (String.valueOf(minutes) + "m");
        }
        contactViewHolder.vTimeSpend.setText(str_duration);



        // TODO: Trocar para o general_category
        String cat = ci.getGeneral_category();
        switch (cat) {
            case "food":
                contactViewHolder.vHead.setBackgroundColor(Color.parseColor("#f9524c"));
                break;
            case "outdoor":
                contactViewHolder.vHead.setBackgroundColor(Color.parseColor("#658e74"));
                break;
            case "shop":
                contactViewHolder.vHead.setBackgroundColor(Color.parseColor("#7d5bb2"));
                break;
            case "indoor":
                contactViewHolder.vHead.setBackgroundColor(Color.parseColor("#ffb14d"));
                break;

            default:
                contactViewHolder.vHead.setBackgroundColor(Color.parseColor("#000000"));
        }


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


    public ArrayList<Local> getContactList() {
        return contactList;
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
        protected LinearLayout vHead;
        protected RatingBar rBar;
        public ContactViewHolder(View v) {
            super(v);
            vImagem = (ImageView) v.findViewById(R.id.imagem);
            vNome =  (TextView) v.findViewById(R.id.txtNome);
            vDesc = (TextView)  v.findViewById(R.id.txtDesc);
            vSchedule = (TextView)  v.findViewById(R.id.txtSchedule);
            vCategorias = (TextView) v.findViewById(R.id.txtCategoria);
            vTimeSpend = (TextView) v.findViewById(R.id.txtTimeSpend);
            vHead = (LinearLayout) v.findViewById(R.id.head);
            rBar=(RatingBar) v.findViewById(R.id.rating_bar);
            rBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser){
                    Toast.makeText(travelCA.getApplicationContext(), "Opa, olha nosso rating"+rBar.getRating(), Toast.LENGTH_SHORT ).show();
                }
            });
        }
    }
}