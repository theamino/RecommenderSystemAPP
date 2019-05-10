package com.example.recommendersystem;

public class V {
    public class RECYCLERVIEW {
        public static final int MOVIE_TYPE = 0;
        public static final int RATING_TYPE = 1;
        public static final int USER_TYPE = 2;

    }
    public static class DATABASE {
        public static final String NAME = "@recommender_databases" +
                "s6";
        public static final int VERSION = 1;

        public static class QUERY {
            public static class CREATE {
                public static final String USER_TABLE = "create table user (" +
                        "id int primary key ," +
                        "age int not null ," +
                        "gender char not null ," +
                        "job varchar(20) ," +
                        "zipcode varchar(20)" +
                        ");";
                public static final String ITEM_TABLE = "create table item (" +
                        "id int primary key , " +
                        "name varchar(100) not null," +
                        "release_date varchar(20) ," +
                        "url varchar(200) ," +
                        "genre char(20) not null" +
                        ");";
                public static final String DATA_TABLE = "create table data (" +
                        "user_id int references user(id) ," +
                        "item_id int references item(id) ," +
                        "rate int not null," +
                        "timestamp varchar(20)" +
                        ");";
            }
        }
    }
}
