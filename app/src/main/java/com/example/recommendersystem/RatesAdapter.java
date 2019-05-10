package com.example.recommendersystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.recommendersystem.Data_Classes.Rating;

import java.util.List;

public class RatesAdapter extends BaseAdapter {
    Context context;
    List<Rating> ratings;

    public RatesAdapter(Context context, List<Rating> ratings) {
        this.context = context;
        this.ratings = ratings;
    }

    @Override
    public int getCount() {
        return ratings.size();
    }

    @Override
    public Object getItem(int position) {
        return ratings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Holder holder;
        DatabaseManager db = new DatabaseManager(context , false);
        if (view == null) {
            holder = new Holder();
            view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(android.R.layout.simple_list_item_2, null);
            holder.txt1 = view.findViewById(android.R.id.text1);
            holder.txt2 = view.findViewById(android.R.id.text2);
            holder.txt1.setText(db.getMovieName(ratings.get(position).getMovieID()));
            holder.txt2.setText(String.valueOf(ratings.get(position).getRating()));
        }
        return view;
    }

    public class Holder {
        public TextView txt1 , txt2;
    }
}
