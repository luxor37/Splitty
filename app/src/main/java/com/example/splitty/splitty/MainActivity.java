package com.example.splitty.splitty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Intent addEventIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        addEventIntent = new Intent(this, AddEventActivity.class);
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

    public void addEvent(View v){
        startActivity(addEventIntent);
    }
}
