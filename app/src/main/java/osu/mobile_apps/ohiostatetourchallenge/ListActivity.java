package osu.mobile_apps.ohiostatetourchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import database.OsuTourDbSchema.DatabaseHelper;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mlocations;
    private RecyclerView.Adapter mAdapter;
    private List<ListItem> listItems;
    private DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
    private User user;
    private String target;
    private boolean completed = false;

    // Stops the back button
    @Override
    public void onBackPressed() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnCreate() Executed");
        setContentView(R.layout.activity_list);
        user = (User) getIntent().getSerializableExtra("User");
        //Get username from log in activity
        //String userName = getIntent().getExtras().getString("User");

        TextView welcome = (TextView) findViewById(R.id.welcome);
        if(welcome != null) {
            //welcome.setText("Welcome " + userName + "!");
        }
        mlocations = (RecyclerView) findViewById(R.id.locationList);
        //Every item has fixed size
        mlocations.setHasFixedSize(true);

        //Create location list
        mlocations.setLayoutManager(new LinearLayoutManager(this));
        List<Location> locations = mDatabaseHelper.getLocations();

        //Get list of locked and unlocked locations
        List<Location> unlocked = new ArrayList<>();
        List<Location> locked = new ArrayList<>();
        for(Location place: locations){
            if(mDatabaseHelper.locationIsUnlocked(user.getId(), place.getId())){
                unlocked.add(place);
            }else{
                locked.add(place);
            }
        }
        Collections.sort(locations, Location.LocationNameComparator);

        //Button used to change target views
        Button switchButton = (Button) findViewById(R.id.locationButton);

        List<Location> targetLocations = new ArrayList<>();
        target = (String) getIntent().getSerializableExtra("Target");
        //If the target is null then make the locked locations
        if(target == null){
            target = "Locked";
        }

        //If everything is unlocked then just display the unlocked locations and mark complete
        if(locations.size() == unlocked.size()){
            target = "Unlocked";
            completed = true;
        }

        //Get the correct list as the target list
        if(target.equals("Locked")){
            targetLocations = locked;
        }else{
            targetLocations = unlocked;
        }

        //Set text of header and switch button based on target locations
        TextView locationOption = (TextView) findViewById(R.id.header);
        if(target.equals("Locked")){
            switchButton.setText("Unlocked Locations");
            locationOption.setText("Your Locked Locations");
        }else{
            switchButton.setText("Locked Locations");
            locationOption.setText("Your Unlocked Locations");
        }


        //Display locations
        listItems = new ArrayList<>();
        String description;
        String smallDescription;
        for(Location entry: targetLocations) {
            description = entry.getDescription();
            //Check if location is unlocked or not
            if(mDatabaseHelper.locationIsUnlocked(user.getId(), entry.getId())) {
                if (description.length() > 110) {
                    smallDescription = description.substring(0, 110) + "...";
                } else {
                    smallDescription = description;
                }
            } else {
                //Locked location displays ??? as description
                smallDescription = "???";
            }
            ListItem item = new ListItem(entry.getName(), smallDescription);
            listItems.add(item);
        }

        mAdapter = new myAdpater(listItems);

        mlocations.setAdapter(mAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.map:
                //Start map activity
                Intent mapIntent = new Intent(ListActivity.this, MapActivity.class);
                mapIntent.putExtra("UserID", user.getId());
                startActivity(mapIntent);
                return true;

            case R.id.about:
                //Start about activity
                Intent aboutIntent = new Intent(ListActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;

            case R.id.logout:
                // Logout
                Intent logoutIntent = new Intent(ListActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.textViewHead) {
            intent = new Intent(ListActivity.this, InformationActivity.class);
            TextView TV = (TextView) v.findViewById(R.id.textViewHead);
            Location location = mDatabaseHelper.getLocation(TV.getText().toString());
            intent.putExtra("Location", location);
            intent.putExtra("User", user);
            intent.putExtra("isUnlocked", mDatabaseHelper.locationIsUnlocked(user.getId(), location.getId()));
        } else {
            if (!completed) {
                intent = new Intent(ListActivity.this, ListActivity.class);
                if (target.equals("Locked")) {
                    intent.putExtra("User", user);
                    intent.putExtra("Target", "Unlocked");
                } else {
                    intent.putExtra("User", user);
                    intent.putExtra("Target", "Locked");
                }
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "All locations completed. Good job!!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnStart() Executed");
        super.onStart();
    }

    @Override
    protected void onPause(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnPause() Executed");
        super.onPause();
    }

    @Override
    protected void onResume(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnResume() Executed");
        super.onResume();
    }

    @Override
    protected void onStop(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnStop() Executed");
        super.onStop();
    }

    @Override
    protected void onRestart(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnRestart() Executed");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnDestroy() Executed");
        super.onDestroy();
    }

}


