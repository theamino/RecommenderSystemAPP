package com.example.recommendersystem;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.recommendersystem.Data_Classes.Movie;

public class MovieDialog extends Dialog {
    private String[] genres = { "Unknown ," ,
            "Action ," , "Adventure ," ,
            "Animation ," , "Children's ," ,
            "Comedy ," , "Crime ," ,
            "Documentary ," , "Drama ," ,
            "Fantasy ," , "Film-Noir ," ,
            "Horror ," , "Musical ," ,
            "Mystery ," , "Romance ," ,
            "Sci-Fi ," , "Thriller ," ,
            "War ," , "Western ,"
    };
    private Context context;
    private Movie movie;
    public MovieDialog(@NonNull Context context , Movie movie) {
        super(context);
        this.movie = movie;

    }

    TextView title , url , date , genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_dialog);
        title = findViewById(R.id.name_movie_dialog);
        url = findViewById(R.id.url_movie_dialog);
        date = findViewById(R.id.release_date_movie_dialog);
        genre = findViewById(R.id.genres_video_dialog);
        setCancelable(true);
        title.setText(movie.getTitle());
        url.setText(movie.getUrl());
        date.setText(movie.getReleaseDate());
        String s = "";
        for (int i = 0; i < movie.getGenres().length(); i++)
            if (movie.getGenres().charAt(i) == '1')
                s += genres[i];
        genre.setText(s);
        findViewById(R.id.list_view_rates).setVisibility(View.GONE);
        findViewById(R.id.textViewRates).setVisibility(View.GONE);
    }
}
