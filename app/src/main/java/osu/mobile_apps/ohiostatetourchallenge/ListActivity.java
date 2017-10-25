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
        mlocations = (RecyclerView) findViewById(R.id.locationList);
        //Every item has fixed size
        mlocations.setHasFixedSize(true);

        mlocations.setLayoutManager(new LinearLayoutManager(this));
        List<Location> locations = mDatabaseHelper.getLocations();
        Collections.sort(locations, Location.LocationNameComparator);
        listItems = new ArrayList<>();
        for(Location entry: locations) {
            ListItem item = new ListItem(entry.getName(), entry.getDescription());
            listItems.add(item);
        }

        mAdapter = new myAdpater(listItems);

        mlocations.setAdapter(mAdapter);

    }

    public void onClick(View v){
        //TODO Make this open a new activity and pass in information
        Intent intent = new Intent(ListActivity.this, InformationActivity.class);
        if(v.getId()==R.id.textViewHead){
            TextView TestView = (TextView) v.findViewById(R.id.textViewHead);
            intent.putExtra("Header", TestView.getText());
        }
        startActivity(intent);
    }

}


