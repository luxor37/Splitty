package com.example.splitty.splitty;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AddPurchaseActivity extends AppCompatActivity {
    private DatabaseManager dbManager = new DatabaseManager(this);
    private int eventId = getIntent().getExtras().getInt("eventId");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void add(View v){
        try{
            EditText input_desc = findViewById(R.id.input_desc);
            String desc = input_desc.getText().toString();

            EditText input_cost = findViewById(R.id.input_cost);
            double cost = Double.parseDouble(input_cost.getText().toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}