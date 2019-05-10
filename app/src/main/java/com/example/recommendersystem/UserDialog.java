package com.example.recommendersystem;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recommendersystem.Data_Classes.Movie;
import com.example.recommendersystem.Data_Classes.Rating;
import com.example.recommendersystem.Data_Classes.User;

import java.util.List;

public class UserDialog extends Dialog {
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
    private User user;
    public UserDialog(@NonNull Context context , User user) {
        super(context);
        this.user = user;

    }

    TextView title , url , date , genre;
    ListView rates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_dialog);
        title = findViewById(R.id.name_movie_dialog);
        url = findViewById(R.id.url_movie_dialog);
        date = findViewById(R.id.release_date_movie_dialog);
        genre = findViewById(R.id.genres_video_dialog);
        setCancelable(true);
        title.setText("USER NUMBER " + String.valueOf(user.getUserID()) + "     ");
        url.setText(String.valueOf(user.getAge()) + " " + (user.getGender() == 'F' ? "Female" : "Male" + "             " ));
        date.setText(user.getZipcode());
        genre.setText(user.getJob());
        rates = findViewById(R.id.list_view_rates);
        DatabaseManager db = new DatabaseManager(context , false);
        List<Rating> ratings = db.getUserRates(user.getUserID());
        rates.setAdapter(new RatesAdapter(getContext() , ratings));
        ((TextView)findViewById(R.id.textViewRates)).setText("Rates " + String.valueOf(ratings.size()));
    }
}

