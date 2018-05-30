package com.example.gopinathana1074.anjalicontactapp;

import android.content.Intent;
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

    public static final String EXTRA_MESSAGE = "com.example.gopinathana1074.anjalicontactapp.MESSAGE";
    public static final String YOUR_SEARCH = "com.example.gopinathana1074.anjalicontactapp.MESSAGE";
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
    public EditText getEditName(){
        return editName;
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
            Log.d("AnjaliContactApp", "MainActivity: viewData: appending data");
            buffer.append("ID: " + res.getString(0));
            buffer.append("\n");
            buffer.append("Name: " + res.getString(1));
            buffer.append("\n");
            buffer.append("Phone: " + res.getString(2));
            buffer.append("\n");
            buffer.append("Email: " + res.getString(3));
            buffer.append("\n");
            buffer.append("\n");
            Log.d("AnjaliContactApp", "MainActivity: viewData: appended data");
        }
        Log.d("AnjaliContactApp", "MainActivity: viewData: showing message ... ");
        showMessage("Data", buffer.toString());
        Log.d("AnjaliContactApp", "MainActivity: viewData: showed message");
    }
    public void showMessage(String title, String message){
        Log.d("AnjaliContactApp", "MainActivity: showMessage: assembling alertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);

        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
    }

    public void SearchRecord(View view){
        StringBuffer buffer = new StringBuffer();
        Cursor res = myDb.getAllData();

        Log.d("AnjaliContactApp", "MainActivity: launching SearchActivity");
        if(res.getCount()==0){
            showMessage("Error", "No data found in database");
            Toast.makeText(this, "There are no contacts to view.", Toast.LENGTH_LONG).show();
            return;
        }
        while(res.moveToNext()) {
            if (res.getString(1).equals(editName.getText().toString())) {
                //Append res column [0 (ID), 1 (Name), 2 (Phone), 3 (Email)] to the buffer
                //see StringBuffer and Cursor API's
                //Delimit each append with new line "\n"

                Log.d("AnjaliContactApp", "MainActivity: searchRecord: appending to buffer");
                buffer.append("ID: " + res.getString(0));
                buffer.append("\n");
                buffer.append("Name: " + res.getString(1));
                buffer.append("\n");
                buffer.append("Phone: " + res.getString(2));
                buffer.append("\n");
                buffer.append("Email: " + res.getString(3));
                buffer.append("\n");
                buffer.append("\n");
                Log.d("AnjaliContactApp", "MainActivity: searchRecord: appended data");
            }

//

        }
        Intent intent = new Intent(this, SearchActivity.class);
        Intent intent2 = new Intent(this, SearchActivity.class);

        intent.putExtra(EXTRA_MESSAGE, buffer.toString());

        intent2.putExtra(YOUR_SEARCH, editName.getText().toString());
        startActivity(intent);
    }

}
