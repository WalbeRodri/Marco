package com.example.marco.map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.marco.R;

import java.util.ArrayList;

import base.Local;

/**
 * Created by Usuário on 03/07/2017.
 */

public class ListLocaisAdapter extends BaseAdapter {

    ArrayList<Local> locais;
    Context context;
    ViewHolder vh;

    public ListLocaisAdapter(ArrayList<Local> l, Context c){
        this.locais = l;
        this.context = c;
    }

    @Override
    public int getCount() {
        return this.locais.size();
    }

    @Override
    public Object getItem(int position) {
        return this.locais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.locais.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }

    private class ViewHolder {
        final TextView textViewNome;
        final TextView textViewDesc;

        private ViewHolder(View view) {
            this.textViewNome = (TextView) view.findViewById(R.id.nominho);
            this.textViewDesc = (TextView) view.findViewById(R.id.descinho);
        }
    }
}
