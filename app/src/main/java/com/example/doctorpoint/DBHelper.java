package com.example.doctorpoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Patterns;

import androidx.annotation.Nullable;

import com.example.doctorpoint.DBContract.UsersEntry;

import java.util.regex.Pattern;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users.db";
    public static final int DATABASE_VERSION = 1;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsersEntry.SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Boolean insertData(String username, String password,String email, String ph_num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(UsersEntry.COLUMN_USER_NAME, username);
        contentValues.put(UsersEntry.COLUMN_PASSWORD, password);
        contentValues.put(UsersEntry.COLUMN_PHONE_NUMBER, ph_num);
        contentValues.put(UsersEntry.COLUMN_EMAIL, email);
        long result = db.insert(UsersEntry.TABLE_NAME, null, contentValues);

        if(result==-1) {//failure
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean isExistedEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = UsersEntry.COLUMN_EMAIL+ " = ?";
        String[] columns = {
                UsersEntry.COLUMN_EMAIL,
//                UsersEntry.COLUMN_PASSWORD,
//                UsersEntry.COLUMN_PHONE_NUMBER,
//                UsersEntry.COLUMN_USER_NAME,
        };
        Cursor cursor = db.query(UsersEntry.TABLE_NAME , columns,selection, new String[]{email}, null, null, null);
        if (cursor.getCount() > 0) //user exists
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = UsersEntry.COLUMN_EMAIL+ " = ? AND " + UsersEntry.COLUMN_PASSWORD+" = ?";
        String[] columns = {
                UsersEntry.COLUMN_EMAIL,
                UsersEntry.COLUMN_PASSWORD,
//                UsersEntry.COLUMN_PHONE_NUMBER,
//                UsersEntry.COLUMN_USER_NAME,
        };
        Cursor cursor = db.query(UsersEntry.TABLE_NAME , columns,selection, new String[]{email,password}, null, null, null);

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean validateEmail(String email) {
        String emailInput = email.trim();

        if (emailInput.isEmpty()) {
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean validatePassword(String password) {
        String passwordInput = password.trim();

        if (passwordInput.isEmpty()) {
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            return false;
        } else {
            return true;
        }
    }

}
