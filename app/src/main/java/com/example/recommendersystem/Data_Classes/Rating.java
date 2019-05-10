package com.example.recommendersystem.Data_Classes;

public class Rating extends DataClass {
    private int userID, movieID, rating;
    private String timeStamp;

    public Rating(int userID, int movieID, int rating, String timeStamp) {
        this.userID = userID;
        this.movieID = movieID;
        this.rating = rating;
        this.timeStamp = timeStamp;
    }

    public int getUserID() {
        return userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public int getRating() {
        return rating;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
