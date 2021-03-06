package com.example.splitty.splitty;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.splitty.splitty.Classes.Contact;
import com.example.splitty.splitty.Classes.Purchase;

public class ListPurchaseActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private double owe = 0D;
    private double receive = 0D;
    private int eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_purchase);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        eventId = getIntent().getExtras().getInt("eventId");

        dbManager = new DatabaseManager(this);

        reload();

    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    public void reload() {
        double totalOwe = 0D;
        GridLayout friendsList = (GridLayout) findViewById(R.id.friendsList);

        friendsList.removeAllViews();
        TextView tvIdHeader = new TextView(ListPurchaseActivity.this);
        tvIdHeader.setText(R.string.placeholder_desc);
        TextView tvFirstNameHeader = new TextView(ListPurchaseActivity.this);
        tvFirstNameHeader.setText(R.string.placeholder_firstname);
        TextView tvLastNameHeader = new TextView(ListPurchaseActivity.this);
        tvLastNameHeader.setText(R.string.placeholder_cost);
        friendsList.addView(tvIdHeader);
        friendsList.addView(tvFirstNameHeader);
        friendsList.addView(tvLastNameHeader);
        for( Purchase purchase : dbManager.selectPurchasesByEvent(eventId)) {
            Contact buyer = dbManager.selectContactById(purchase.getBuyerId());
            TextView tvDesc = new TextView(ListPurchaseActivity.this);
            tvDesc.setText(purchase.getDesc());
            TextView tvFirstName = new TextView(ListPurchaseActivity.this);
            tvFirstName.setText(buyer.getFirstName());
            TextView tvCost = new TextView(ListPurchaseActivity.this);
            tvCost.setText(String.valueOf(purchase.getCost()));
            friendsList.addView(tvDesc);
            friendsList.addView(tvFirstName);
            friendsList.addView(tvCost);
            totalOwe += purchase.getCost();
        }

        totalOwe /= dbManager.getContactCountInEvent(eventId);
        TextView t = (TextView) findViewById(R.id.oweAmount);
        t.setText(String.valueOf(totalOwe));
    }

    public void addPurchase(View v){
        Intent i = new Intent(this, AddPurchaseActivity.class);
        i.putExtra("eventId", eventId);

        startActivity(i);
    }

}
