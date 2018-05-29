package com.example.gopinathana1074.anjalicontactapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        StringBuffer buffer = new StringBuffer();
//        Cursor cursor = myDb.getAllData();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Get intent that started activity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Set the string in the textview on ui
        TextView tv4 = findViewById(R.id.textView4);
        tv4.setText(message);

        //Display what your search was
        Intent intent2 = getIntent();
//        String yourSearch = intent.getStringExtra(MainActivity.YOUR_SEARCH);
//        TextView tv6 = findViewById(R.id.textView6);
//        tv6.setText(yourSearch);


    }
}
