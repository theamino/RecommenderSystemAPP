package com.example.recommendersystem;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recommendersystem.Data_Classes.DataClass;
import com.example.recommendersystem.Data_Classes.Movie;
import com.example.recommendersystem.Data_Classes.Rating;
import com.example.recommendersystem.Data_Classes.User;

import java.util.ArrayList;
import java.util.List;

public class DataShowRecyclerViewAdapter extends RecyclerView.Adapter {
    public static final int MOVIE = 0 ;
    public static final int LINK = 1;
    public static final int RATING = 2;
    public static final int TAG = 3;
    private Context context;
    private List<DataClass> data = new ArrayList<DataClass>();
   // private List<Movie> movies= new ArrayList<Movie>();
   // private List<User> user= new ArrayList<User>();
   // private List<Rating> ratings= new ArrayList<Rating>();
    private int type;
    public DataShowRecyclerViewAdapter(Context context, List<DataClass> data , @Nullable int type) {
        this.data = data;
        this.context = context;
        this.type = type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        switch (getItemViewType(i))
        {
            case V.RECYCLERVIEW.MOVIE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new MovieHolder(view);
            case V.RECYCLERVIEW.USER_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new MovieHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MovieHolder movieHolder = null;
        switch (getItemViewType(i)) {
            case V.RECYCLERVIEW.MOVIE_TYPE:
                movieHolder = (MovieHolder) viewHolder;
                movieHolder.itemView.setOnClickListener(onClick(viewHolder.getLayoutPosition()));
                movieHolder.txt.setText(((Movie)data.get(i)).getTitle());
                break;
            case V.RECYCLERVIEW.USER_TYPE:
                movieHolder = (MovieHolder) viewHolder;
                movieHolder.itemView.setOnClickListener(onClick(viewHolder.getLayoutPosition()));
                movieHolder.txt.setText("User Number " + String.valueOf(((User)data.get(i)).getUserID()));
                break;
        }
    }

    private View.OnClickListener onClick(final int layoutPosition) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getItemViewType(layoutPosition)) {
                    case V.RECYCLERVIEW.MOVIE_TYPE:
                        showAlertDialog(layoutPosition);
                        break;
                    case V.RECYCLERVIEW.USER_TYPE:
                        showUserAlertDialog(layoutPosition);
                        break;
                }
            }
        };
    }

    private void showUserAlertDialog(int layoutPosition) {
        new UserDialog(context , ((User)data.get(layoutPosition))).show();
    }

    private void showAlertDialog(int layoutPosition) {
        new MovieDialog(context , ((Movie)data.get(layoutPosition))).show();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(android.R.id.text1);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type;
    }

}
