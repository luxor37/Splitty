package com.example.splitty.splitty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
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
    private Intent eventIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        db = new DatabaseManager(this);

        addEventIntent = new Intent(this, AddEventActivity.class);
        eventIntent = new Intent(this, ListPurchaseActivity.class);
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
        try {
            ArrayList<Event> events = db.selectAllEvents();

            TableLayout event_table = new TableLayout(this);

            for (final Event e : events) {
                Log.d("somthin new", e.getId()+"");
                TableRow tr = new TableRow(this);
                Button btn = new Button(this);
                tr.setGravity(Gravity.CENTER);

                TextView tv = new TextView(this);
                tv.setText(e.getName());
                tv.setTextSize(40);

                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        viewEvent(e.getId());
                    }
                });
                btn.setText("View Details");

                tr.addView(tv);
                tr.addView(btn);
                event_table.addView(tr);
            }



            ScrollView event_scroll = findViewById(R.id.event_scroll);
            event_scroll.addView(event_table);
        } catch (Exception e){
            Log.d("ERROR", "NO EVENTS YET");
        }
    }

    public void addEvent(View v){
        startActivity(addEventIntent);
    }

    public  void viewEvent(int id){
        Log.wtf("noob", String.valueOf(id));
        eventIntent.putExtra("eventId", id);
        startActivity(eventIntent);
    }
}
