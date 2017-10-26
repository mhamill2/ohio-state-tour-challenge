package osu.mobile_apps.ohiostatetourchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import database.OsuTourDbSchema.DatabaseHelper;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mlocations;
    private RecyclerView.Adapter mAdapter;
    private List<ListItem> listItems;
    private DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTING","ON CREATE() FOR LIST");
        setContentView(R.layout.activity_list);
        String userName = getIntent().getExtras().getString("User");

        TextView welcome = (TextView) findViewById(R.id.welcome);
        if(welcome != null) {
            welcome.setText("Welcome " + userName + "!");
        }
        mlocations = (RecyclerView) findViewById(R.id.locationList);
        //Every item has fixed size
        mlocations.setHasFixedSize(true);

        mlocations.setLayoutManager(new LinearLayoutManager(this));
        List<Location> locations = mDatabaseHelper.getLocations();
        Collections.sort(locations, Location.LocationNameComparator);
        listItems = new ArrayList<>();

        String description;
        String smallDescription;

        for(Location entry: locations) {
            description = entry.getDescription();
            //TODO Check if location is unlocked or not
            if (description.length()>110){
                smallDescription = description.substring(0,110) + "...";
            } else {
                smallDescription = description;
            }
            ListItem item = new ListItem(entry.getName(), smallDescription);
            listItems.add(item);
        }

        mAdapter = new myAdpater(listItems);

        mlocations.setAdapter(mAdapter);

    }

    public void onClick(View v){
        Intent intent = new Intent(ListActivity.this, InformationActivity.class);
        if(v.getId()==R.id.textViewHead){
            TextView TestView = (TextView) v.findViewById(R.id.textViewHead);
            intent.putExtra("Header", TestView.getText());
        }
        startActivity(intent);
    }

}


