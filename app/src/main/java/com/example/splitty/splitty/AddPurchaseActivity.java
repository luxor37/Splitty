package com.example.splitty.splitty;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.splitty.splitty.Classes.Contact;

public class AddPurchaseActivity extends AppCompatActivity {
    private DatabaseManager dbManager = new DatabaseManager(this);
    private int eventId;
    private Contact selectedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eventId = getIntent().getExtras().getInt("eventId");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dbManager.selectAllContactNames());

        AutoCompleteTextView tv = (AutoCompleteTextView)findViewById(R.id.input_buyer);
        tv.setThreshold(1);
        tv.setAdapter(arrayAdapter);

        tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selectedContact = dbManager.selectContactById(arg2);
            }
        });
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