package com.example.gopinathana1074.anjalicontactapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    EditText editName, editPhone, editEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_Name);
        editPhone = findViewById(R.id.editText_Phone);
        editEmail = findViewById(R.id.editText_Email);

        myDb = new DatabaseHelper(this);
        Log.d("AnjaliContactApp", "MainActivity: instantiated myDb as DatabaseHelper");

    }
    public void addData(View view){
        Log.d("AnjaliContactApp", "MainActivity: adding data");
        boolean isInserted = myDb.insertData(   editName.getText().toString(),
                                                editPhone.getText().toString(),
                                                editEmail.getText().toString() );

        if(isInserted){
            Toast.makeText(this, "Contact inserted!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Failed to insert contact", Toast.LENGTH_LONG).show();
        }
    }
}
