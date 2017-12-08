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

public class AddEventActivity extends AppCompatActivity implements Serializable{
    private Intent mainIntent;
    private Intent friendIntent;
    private ArrayList<Contact> friendList;
    private TableLayout resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainIntent = new Intent(this, MainActivity.class);
        friendIntent = new Intent(this, AddFriendActivity.class);
        resultView = (TableLayout)findViewById(R.id.resultView);

        if(getIntent().getSerializableExtra("friendList") !=null) {
            Intent temp = getIntent();
            Bundle args = temp.getBundleExtra("BUNDLE");
            friendList = (ArrayList<Contact>) args.getSerializable("friendList");
        }
        populateScroll();
    }

    public void createEvent (View v){
        if(friendList!=null) {
            Bundle args = new Bundle();
            args.putSerializable("friendList",(Serializable)friendList);
            mainIntent.putExtra("bundle", args);
            startActivity(mainIntent);
        }
    }

    public void populateScroll(){
        if(friendList!=null){

            for (int i = 0;i<friendList.size();i++) {
                Log.d("DDDDDDDEEEEEEEBBBUUUUUUGGGGG",friendList.size()+"");
                TableRow row = new TableRow(this);
                TextView info = new TextView(this);
                Button rmvBtn = new Button(this);
                info.setText(friendList.get(i).getFirstName()+" "+friendList.get(i).getLastName());
                final int temp = i;
                rmvBtn.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        removeFriend(friendList.get(temp).getId());
                    }
                });
                rmvBtn.setText("@string/delete");
                row.addView(info);
                row.addView(rmvBtn);
                resultView.addView(row, i);
            }
        }
        else {
            TableRow row = new TableRow(this);
            TextView info = new TextView(this);
            info.setText("No Contact");
            row.addView(info);
            resultView.addView(row, 0);
        }
    }

    public void removeFriend(int id){

    }

    public void addFriend (View v){

        if(friendList!=null){
            Bundle args = new Bundle();
            args.putSerializable("friendList",(Serializable)friendList);
            friendIntent.putExtra("bundle", args);
        }
        startActivity(friendIntent);
    }
}