package com.example.gopinathana1074.anjalicontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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
        boolean isInserted = myDb.insertData(
                editName.getText().toString(),
                editPhone.getText().toString(),
                editEmail.getText().toString() );

        if(isInserted){
            Toast.makeText(this, "Contact inserted!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Failed to insert contact", Toast.LENGTH_LONG).show();
        }
    }
    public void viewData(View view){
        Cursor res = myDb.getAllData();
        Log.d("AnjaliContactApp", "MainActivity: viewData: received cursor");

        if(res.getCount()==0){
            showMessage("Error", "No data found in database");
            Toast.makeText(this, "There are no contacts to view.", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            //Append res column [0 (ID), 1 (Name), 2 (Phone), 3 (Email)] to the buffer
            //see StringBuffer and Cursor API's
            //Delimit each append with new line "\n"
            
        }
        showMessage("Data", buffer.toString());
    }
    public void showMessage(String title, String message){
        Log.d("AnjaliContactApp", "MainActivity: showMessage: assembling alertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);

        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }

}
