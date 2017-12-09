package com.example.splitty.splitty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.splitty.splitty.Classes.Event;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Intent addEventIntent;
    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        db = new DatabaseManager(this);

        addEventIntent = new Intent(this, AddEventActivity.class);
        populate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.menu_main);
        tb.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return onOptionsItemSelected(item);
                    }
                });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.events:
                // code
                return true;
            case R.id.contacts:
                // code
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void populate(){
        ArrayList<Event> events = db.selectAllEvents();

        for(int i = 0; i < 50; i++){
            Event event = new Event();
            event.setName("Test" +i);
            events.add(event);
        }

        TableLayout event_table = new TableLayout(this);

        for(Event e : events){
            TableRow tr = new TableRow(this);
            tr.setGravity(Gravity.CENTER);

            TextView tv = new TextView(this);
            String text = e.getName();
            tv.setText(text);
            tv.setTextSize(40);

            tr.addView(tv);
            event_table.addView(tr);
        }

        ScrollView event_scroll = findViewById(R.id.event_scroll);
        event_scroll.addView(event_table);
    }

    public void addEvent(View v){
        startActivity(addEventIntent);
    }
}
