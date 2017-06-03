package adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.Query;

import base.Perfil;
/**
 * Created by Matheus on 15/11/2016.
 */

public class PerfilAdapter extends FirebaseRecyclerAdapterNovo<PerfilAdapter.ViewHolder,Perfil> {


    public PerfilAdapter(Query query, Class<Perfil> itemClass) {
        super(query, itemClass);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    protected void itemAdded(Perfil item, String key, int position) {
    }

    @Override
    protected void itemChanged(Perfil oldItem, Perfil newItem, String key, int position) {
    }

    @Override
    protected void itemRemoved(Perfil item, String key, int position) {
    }

    @Override
    protected void itemMoved(Perfil item, String key, int oldPosition, int newPosition) {
    }
}
