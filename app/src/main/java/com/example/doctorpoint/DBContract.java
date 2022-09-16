package com.example.doctorpoint;

import android.provider.BaseColumns;

public final class DBContract {
    private DBContract(){}


    public static final class UsersEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_info";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE_NUMBER = "phone_number";
        public static final String COLUMN_PASSWORD = "password";

        // CREATE TABLE course_info (course_id, course_title)
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                        COLUMN_USER_NAME + " TEXT NOT NULL, " +
                        COLUMN_PASSWORD + " TEXT NOT NULL, " +
                        COLUMN_PHONE_NUMBER + " INTEGER UNIQUE NOT NULL)";
    }
}
