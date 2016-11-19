package adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.Query;

import java.util.ArrayList;

import base.Local;


public class LocalAdapter extends FirebaseRecyclerAdapterNovo<LocalAdapter.ViewHolder, Local> {



    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDescription;

        public ViewHolder(View view) {
            super(view);
//            textViewName = (TextView) view.findViewById(R.id.textview_name);
//            textViewDescription = (TextView) view.findViewById(R.id.textview_description);
        }
    }

    public LocalAdapter(Query query, Class<Local> itemClass, @Nullable ArrayList<Local> items,@Nullable ArrayList<String> mKeys) {
        super(query, itemClass, items, mKeys);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_local, parent, false);
//
//        return new ViewHolder(view);
    return null;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Local local = getItem(position);
        holder.textViewName.setText(local.getName());
        holder.textViewDescription.setText(local.getDescription());

    }

    @Override
    protected void itemAdded(Local item, String key, int position) {

    }

    @Override
    protected void itemChanged(Local oldItem, Local newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Local item, String key, int position) {

    }

    @Override
    protected void itemMoved(Local item, String key, int oldPosition, int newPosition) {

    }

}
