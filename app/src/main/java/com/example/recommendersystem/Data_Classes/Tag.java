package com.example.recommendersystem.Data_Classes;

public class Tag extends DataClass{
    private int userID , movieID , timeStamp;
    private String tag;

    public Tag(int userID, int movieID, int timeStamp, String tag) {
        this.userID = userID;
        this.movieID = movieID;
        this.timeStamp = timeStamp;
        this.tag = tag;
    }
}
