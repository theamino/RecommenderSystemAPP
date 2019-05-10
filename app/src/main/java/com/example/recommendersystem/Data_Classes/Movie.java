package com.example.recommendersystem.Data_Classes;

import com.example.recommendersystem.Genres;

import java.util.ArrayList;
import java.util.List;

public class Movie extends DataClass {
    private int movieID;
    private String title;
    private String genres;
    private String releaseDate , url;

    public Movie(int movieID, String title , String releaseDate , String url , String genres) {
        this.movieID = movieID;
        this.title = title;
        this.genres = genres;
        this.url = url;
        this.releaseDate = releaseDate;
    }

    public int getMovieID() {
        return movieID;
    }

    public String getTitle() {
        String ttl = "";
        for (int i = 0 ; i < title.length() ; i++)
            if (title.charAt(i) != 39)
                ttl += title.charAt(i);
        return ttl;
    }

    public String getGenres() {
        return genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getUrl() {
        List<Integer> points = new ArrayList<Integer>();
        for (int i = 0 ; i < url.length() ; i++)
            if (url.charAt(i) == 39)
                points.add(i);
            if (points.size() > 0)
                url = "http://google.com";
        return url;
    }
}


