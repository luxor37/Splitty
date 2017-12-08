package com.example.splitty.splitty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.splitty.splitty.Classes.Contact;

import java.io.Serializable;
import java.util.ArrayList;

public class AddEventActivity extends AppCompatActivity implements Serializable {
    private Intent mainIntent;
    private Intent friendIntent;
    private ArrayList<Integer> friendList;
    private TableLayout resultView;
    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainIntent = new Intent(this, MainActivity.class);
        friendIntent = new Intent(this, AddFriendActivity.class);
        resultView = findViewById(R.id.resultView);
        db = new DatabaseManager(this);

        if (getIntent().getIntegerArrayListExtra("friendList") != null) {
            friendList = getIntent().getIntegerArrayListExtra("friendList");
        }
        populateScroll();
    }

    public void createEvent(View v) {
        if (friendList != null) {
            mainIntent.putIntegerArrayListExtra("friendList", friendList);
            startActivity(mainIntent);
        }
    }

    public void populateScroll() {
        if (friendList != null) {

            for (int i = 0; i < friendList.size(); i++) {
                Log.d("DDDEEEBBBUUUGGG", friendList.size()+"");
                final Contact friend = db.selectContactById(friendList.get(i));Log.d("DDDEEEBBBUUUGGG", friend.getFirstName());
                TableRow row = new TableRow(this);
                TextView info = new TextView(this);
                Button rmvBtn = new Button(this);
                info.setText(friend.getFirstName() + " " + friend.getLastName());
                rmvBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        removeFriend(friend.getId());
                    }
                });
                rmvBtn.setText("@string/delete");
                row.addView(info);
                row.addView(rmvBtn);
                resultView.addView(row, i);
            }
        } else {
            TableRow row = new TableRow(this);
            TextView info = new TextView(this);
            info.setText("No Contact");
            row.addView(info);
            resultView.addView(row, 0);
        }
    }

    public void removeFriend(int id) {

    }

    public void addFriend(View v) {
        if (friendList != null) {
            friendIntent.putIntegerArrayListExtra("friendList", friendList);
        }
        startActivity(friendIntent);
    }
}