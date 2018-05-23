package com.example.gopinathana1074.anjalicontactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2018.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";

    public static final String COLUMN_NAME_CONTACT = "contactName";
    public static final String COLUMN_PHONE = "PhoneNumber";
    public static final String COLUMN_EMAIL = "EmailAddress";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_NAME_CONTACT + " TEXT," +
            COLUMN_PHONE + " TEXT," +
            COLUMN_EMAIL + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("AnjaliContactApp", "DatabaseHelper: constructed DatabaseHelper");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("AnjaliContactApp", "DatabaseHelper: creating database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("AnjaliContactApp", "DatabaseHelper: upgrading database");
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public boolean insertData(String name, String phoneNum, String email){
        Log.d("AnjaliContactApp", "DatabaseHelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVals = new ContentValues();

        contentVals.put(COLUMN_NAME_CONTACT, name);
        contentVals.put(COLUMN_PHONE, phoneNum);
        contentVals.put(COLUMN_EMAIL, email);

        long result = db.insert(TABLE_NAME, null, contentVals);

        if(result == -1){
            Log.d("AnjaliContactApp", "DatabaseHelper: Contact insert FAILED");
            return false;
        }
        else {
            Log.d("AnjaliContactApp", "DatabaseHelper: Contact insert PASSED");
            return true;
        }
    }
}
