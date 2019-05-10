package com.example.recommendersystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ListView;

import com.example.recommendersystem.Data_Classes.DataClass;
import com.example.recommendersystem.Data_Classes.Movie;
import com.example.recommendersystem.Data_Classes.Rating;
import com.example.recommendersystem.Data_Classes.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static OpenHelper openHelper;
    private static SQLiteDatabase readableDatabase, writableDatabase;
    private static boolean isDatabaseCreated = false;
    private Context mContext;


    public DatabaseManager(Context context , boolean withReadFile) {
        if (!isDatabaseCreated) {
            openHelper = new OpenHelper(context);
            isDatabaseCreated = true;
            readableDatabase = openHelper.getReadableDatabase();
            writableDatabase = openHelper.getWritableDatabase();
            mContext = context;
        }
        if (withReadFile)
            ReadFile(context);

    }

    private int intParser(String s) {
        int d = 0;

        for (int i = 0; i < s.length(); i++) {
            d *= 10;
            switch (s.charAt(i)) {
                case '0':
                    break;
                case '1':
                    d+=1;
                    break;
                case '2':
                    d+=2;
                    break;
                case '3':
                    d+=3;
                    break;
                case '4':
                    d+=4;
                    break;
                case '5':
                    d+=5;
                    break;
                case '6':
                    d+=6;
                    break;
                case '7':
                    d+=7;
                    break;
                case '8':
                    d+=8;
                    break;
                case '9':
                    d+=9;
                    break;
            }
        }
        return d;
    }


    public void ReadFile(Context context) {
        openHelper.deleteAll();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open( "user")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                String[] attr = split(mLine , '|');
                new User(intParser(attr[0]) , intParser(attr[1]) , attr[3] , attr[4] , attr[2].charAt(0));
                openHelper.addUser(new User(intParser(attr[0]) , intParser(attr[1]) , attr[3] , attr[4] , attr[2].charAt(0)));
            }

            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open( "item")));

            // do reading, usually loop until end of file reading
            while ((mLine = reader.readLine()) != null) {
                //process line
                String[] attr = split(mLine , '|');
                String gn = "";
                for(int i = 5 ; i < attr.length ; i++)
                    gn += attr[i];
                openHelper.addMovie(new Movie(intParser(attr[0]) , (attr[1]) , attr[2] , attr[4] , gn));
            }

            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open( "data")));

            // do reading, usually loop until end of file reading
            while ((mLine = reader.readLine()) != null) {
                //process line
                String[] attr = split(mLine , '\t');
                openHelper.addRating(new Rating(intParser(attr[0]) , intParser(attr[1]) , intParser(attr[2]) , attr[3]));
            }


        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
    }

    private String[] split(String mLine , char reg) {
        int n = 1;
        for (int i = 0 ; i < mLine.length() ; i++)
            if (mLine.charAt(i) == reg)
              n++;
        String[] ret = new String[n];
        int itr = 0;
        for (int i = 0 ; i < n ; i++) {
            String nxt = "";
            while (itr < mLine.length() && mLine.charAt(itr) != reg ) {
                nxt += mLine.charAt(itr++);
            }
            ret[i] = nxt;
            itr++;
        }
        return ret;
    }

    public List<DataClass> getMovies() {
        return openHelper.getMovies();
    }

    public List<DataClass> getUsers() {
        return openHelper.getUsers();
    }

    public String getMovieName(int movieID) {
        return openHelper.getMovieName(movieID);
    }

    public List<Rating> getUserRates(int userID) {
        return openHelper.getUsersRates(userID);
    }

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, V.DATABASE.NAME, null, V.DATABASE.VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(V.DATABASE.QUERY.CREATE.USER_TABLE);
            sqLiteDatabase.execSQL(V.DATABASE.QUERY.CREATE.ITEM_TABLE);
            sqLiteDatabase.execSQL(V.DATABASE.QUERY.CREATE.DATA_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            onCreate(sqLiteDatabase);
        }

        public void addMovie(Movie movie) {
            String sql = "INSERT INTO item values (" +
                    String.valueOf(movie.getMovieID()) + " , '" +
                    String.valueOf(movie.getTitle()) + "' , '" +
                    String.valueOf(movie.getReleaseDate()) +  "' , '" +
                    movie.getUrl() + "' , '"+
                    movie.getGenres() + "'" +
                    ");";
            getWritableDatabase().execSQL(sql);
        }

        public void addUser(User user) {
            String sql = "INSERT INTO user values (" +
                    String.valueOf(user.getUserID()) + " , "
                    + String.valueOf(user.getAge()) + " , '"
                    + user.getGender() + "' , '"
                    + user.getJob() + "' , '"
                    + user.getZipcode() + "'" +
                    ");";
            getWritableDatabase().execSQL(sql);
        }

        public void addRating(Rating rating) {
            String sql = "INSERT INTO data values (" +
                    String.valueOf(rating.getUserID()) + " , " +
                    String.valueOf(rating.getMovieID()) + " , " +
                    String.valueOf(rating.getRating()) + " , '" +
                    rating.getTimeStamp() +
                    "');";
            getWritableDatabase().execSQL(sql);
        }

        public void deleteAll() {
            String sql = "DELETE FROM user;";
            getWritableDatabase().execSQL(sql);
            sql = "DELETE FROM item;";
            getWritableDatabase().execSQL(sql);
            sql = "DELETE FROM data;";
            getWritableDatabase().execSQL(sql);
        }

        public List<DataClass> getMovies() {
            @SuppressLint("Recycle") Cursor cursor = readableDatabase.rawQuery("SELECT * FROM item;", null);
            List<DataClass> movies = new ArrayList<DataClass>();
            while (cursor.moveToNext()) {
                movies.add(new Movie(cursor.getInt(0)
                        , cursor.getString(1)
                        , cursor.getString(2)
                        , cursor.getString(3)
                        , cursor.getString(4)));
            }
            return movies;
        }

        public List<DataClass> getUsers() {
            @SuppressLint("Recycle") Cursor cursor = readableDatabase.rawQuery("SELECT * FROM user;", null);
            List<DataClass> movies = new ArrayList<DataClass>();
            while (cursor.moveToNext()) {
                movies.add(new User(cursor.getInt(0)
                        , cursor.getInt(1)
                        , cursor.getString(3)
                        , cursor.getString(4)
                        , cursor.getString(2).charAt(0)));
            }
            return movies;
        }

        public String getMovieName(int movieID) {
            @SuppressLint("Recycle") Cursor cursor = readableDatabase.rawQuery("SELECT name FROM item WHERE id = "+ String.valueOf(movieID) +";", null);
            while (cursor.moveToNext())
                return cursor.getString(0);
            return "No Name Found";
        }

        public List<Rating> getUsersRates(int userID) {
            List<Rating> ratings = new ArrayList<Rating>();
            @SuppressLint("Recycle") Cursor cursor = readableDatabase.rawQuery("SELECT * FROM data WHERE user_id = "+ String.valueOf(userID) +";", null);
            while (cursor.moveToNext())
            {
                ratings.add(new Rating(cursor.getInt(0) , cursor.getInt(1) , cursor.getInt(2) , cursor.getString(3)));
            }
            return ratings;
        }
    }
}
