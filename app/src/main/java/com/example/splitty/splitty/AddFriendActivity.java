package com.example.splitty.splitty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splitty.splitty.Classes.Contact;

import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity {
    private TableLayout resultView;
    private DatabaseManager db;
    private ArrayList<Integer> friendList;
    private Intent eventIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseManager(this);
        resultView = findViewById(R.id.resultView);
        eventIntent = new Intent(this, AddEventActivity.class);

        //Hard coded contacts=========================
        db.reload();
        Contact temp = new Contact();
        temp.setEmail("shitstain.bepis");
        temp.setFirstName("coullon");
        temp.setLastName("bagin");
        db.insertContact(temp);

        Contact temp1 = new Contact();
        temp1.setEmail("somethingNew");
        temp1.setFirstName("manger");
        temp1.setLastName("delamarde");
        db.insertContact(temp1);

        //Hard coded contacts=========================

        final EditText myTextBox = findViewById(R.id.search);
        myTextBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                search(s);
            }
        });

        if (getIntent().getIntegerArrayListExtra("friendList") != null)
            friendList = getIntent().getIntegerArrayListExtra("friendList");
    }

    public void search(CharSequence s) {
        resultView.removeAllViews();
        final ArrayList<Contact> contacts = db.selectContactByName(s.toString());
        for (int i = 0; i < contacts.size(); i++) {
            final TableRow row = new TableRow(this);
            TextView info = new TextView(this);
            Button addBtn = new Button(this);
            info.setText(contacts.get(i).getId()+""+contacts.get(i).getFirstName() + " "
                    + contacts.get(i).getLastName() + " " + contacts.get(i).getEmail());
            final int j = i;
            addBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    addFriend(contacts.get(j).getId());
                }
            });
            row.addView(info);
            row.addView(addBtn);
            resultView.addView(row, i);
        }
    }

    public void addFriend(int id) {
        if (friendList != null) {
            if (!(friendList.contains(id))) {
                friendList.add(id);
            } else {
                Toast toast = Toast.makeText(this, "Friend already added", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            friendList = new ArrayList<>();
            friendList.add(id);
        }
        eventIntent.putIntegerArrayListExtra("friendList", friendList);
        Log.d("DDDEEEBBBUUUGGG", friendList.get(0)+"");
        startActivity(eventIntent);
    }
}