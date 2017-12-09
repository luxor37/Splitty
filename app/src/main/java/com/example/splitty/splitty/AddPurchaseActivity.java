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
import com.example.splitty.splitty.Classes.Purchase;
import com.example.splitty.splitty.Classes.PurchaseGroup;

import java.util.ArrayList;

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

        final ArrayList<String> names = dbManager.selectAllContactNames();

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);

        AutoCompleteTextView tv = (AutoCompleteTextView)findViewById(R.id.input_buyer);
        tv.setThreshold(1);
        tv.setAdapter(arrayAdapter);

        tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int contactIndex = names.indexOf(arrayAdapter.getItem((int) arg3 ));
                selectedContact = dbManager.selectContactById(contactIndex);
            }
        });
    }

    public void add(View v){
        try{
            EditText input_desc = findViewById(R.id.input_desc);
            String desc = input_desc.getText().toString();

            EditText input_cost = findViewById(R.id.input_cost);
            double cost = Double.parseDouble(input_cost.getText().toString());

            Purchase p = new Purchase(0, desc, selectedContact.getId(), cost);
            dbManager.insertPurchase(p);
            ArrayList<Purchase> allPurchases = dbManager.selectAllPurchases();
            p = allPurchases.get(allPurchases.size() - 1);
            PurchaseGroup pg = new PurchaseGroup(0,p.getId(), eventId);
            dbManager.insertPurchaseGroup(pg);
            finish();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}