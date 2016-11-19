package adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.Query;

import java.util.ArrayList;

import base.Day;

public class DayAdapter extends FirebaseRecyclerAdapterNovo<DayAdapter.ViewHolder, Day> {

    public DayAdapter(Query query, Class<Day> itemClass, @Nullable ArrayList<Day> items, @Nullable ArrayList<String> keys) {
        super(query, itemClass, items, keys);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDescription;

        public ViewHolder(View view) {
            super(view);
//            textViewName = (TextView) view.findViewById(R.id.textview_name);
//            textViewDescription = (TextView) view.findViewById(R.id.textview_description);
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
    protected void itemAdded(Day item, String key, int position) {

    }

    @Override
    protected void itemChanged(Day oldItem, Day newItem, String key, int position) {

    }

    @Override
    protected void itemRemoved(Day item, String key, int position) {

    }

    @Override
    protected void itemMoved(Day item, String key, int oldPosition, int newPosition) {

    }
}
