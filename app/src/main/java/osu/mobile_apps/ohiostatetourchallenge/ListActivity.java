package osu.mobile_apps.ohiostatetourchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mlocations;
    private RecyclerView.Adapter mAdapter;

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTING","ON CREATE() FOR LIST");
        setContentView(R.layout.activity_list);
        mlocations = (RecyclerView) findViewById(R.id.locationList);
        //Every item has fixed size
        mlocations.setHasFixedSize(true);

        mlocations.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for( int i = 0; i<10; i++){
            ListItem listItem = new ListItem(
                    "Heading" + (i+1),
                    "Description" + (i+1)
            );
            listItems.add(listItem);
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


