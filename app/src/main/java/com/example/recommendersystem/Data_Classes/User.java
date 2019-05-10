package com.example.recommendersystem.Data_Classes;

public class User extends DataClass{
    private int userID , age;
    private String job , zipcode;
    private char gender;

    public User(int userID, int age, String job, String zipcode, char gender) {
        this.userID = userID;
        this.age = age;
        this.job = job;
        this.zipcode = zipcode;
        this.gender = gender;
    }

    public int getUserID() {
        return userID;
    }

    public int getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public String getZipcode() {
        return zipcode;
    }

    public char getGender() {
        return gender;
    }
}
