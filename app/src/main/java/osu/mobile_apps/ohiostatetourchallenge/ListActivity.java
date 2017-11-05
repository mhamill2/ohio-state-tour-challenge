package osu.mobile_apps.ohiostatetourchallenge;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import database.OsuTourDbSchema.DatabaseHelper;
import im.delight.android.location.SimpleLocation;

import static android.support.v4.app.NotificationCompat.*;
import static java.lang.Math.round;

public class ListActivity extends AppCompatActivity {

    private DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
    private User user;
    private String target;
    private boolean completed = false;
    private SimpleLocation mSimpleLocation;

    // Stops the back button
    @Override
    public void onBackPressed() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnCreate() Executed");
        setContentView(R.layout.activity_list);
        user = (User) getIntent().getSerializableExtra("User");

        RecyclerView mlocations = findViewById(R.id.locationList);
        //Every item has fixed size
        mlocations.setHasFixedSize(true);

        //Create location list
        mlocations.setLayoutManager(new LinearLayoutManager(this));
        List<Location> locations = mDatabaseHelper.getLocations();

        //Get list of locked and unlocked locations
        List<Location> unlocked = new ArrayList<>();
        final List<Location> locked = new ArrayList<>();
        for(Location place: locations){
            if(mDatabaseHelper.locationIsUnlocked(user.getId(), place.getId())){
                unlocked.add(place);
            }else{
                locked.add(place);
            }
        }
        Collections.sort(locations, Location.LocationNameComparator);

        //Button used to change target views
        Button switchButton = findViewById(R.id.locationButton);

        List<Location> targetLocations;
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

        Collections.sort(targetLocations, Location.LocationNameComparator);

        //Set text of header and switch button based on target locations
        TextView locationOption = findViewById(R.id.header);
        if(target.equals("Locked")){
            switchButton.setText(R.string.switch_button_locked_text);
            locationOption.setText(R.string.location_option_locked_text);
        }else{
            switchButton.setText(R.string.switch_button_unlocked_text);
            locationOption.setText(R.string.location_option_unlocked_text);
        }


        //Display locations
        List<ListItem> listItems = new ArrayList<>();
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

        RecyclerView.Adapter adapter = new myAdapter(listItems);

        mlocations.setAdapter(adapter);

        // Location listener setup for notification
        mSimpleLocation = new SimpleLocation(this);

        mSimpleLocation.setListener(new SimpleLocation.Listener() {
            public void onPositionChanged() {
                boolean newLocationInRange = false;
                for (int i = 0; i < locked.size(); i++) {
                    if ((int) round(SimpleLocation.calculateDistance(
                            mSimpleLocation.getLatitude(), mSimpleLocation.getLongitude(),
                            locked.get(i).getLatitude(), locked.get(i).getLongitude())) <= 50) {
                        newLocationInRange = true;
                    }
                }
                if (newLocationInRange) {
                    Builder mBuilder = new Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.ic_launcher_background)
                            .setContentTitle("Location In Range!")
                            .setContentText("A new location is in range!");
                    Intent mapIntent = new Intent(ListActivity.this, MapActivity.class);
                    mapIntent.putExtra("User", user);
                    PendingIntent resultPendingIntent = PendingIntent
                            .getActivity(getApplicationContext(), 0
                            ,mapIntent,PendingIntent.FLAG_UPDATE_CURRENT);
                    mBuilder.setContentIntent(resultPendingIntent);
                    // Sets an ID for the notification
                    int mNotificationId = R.integer.notification_id;
                    // Gets an instance of the NotificationManager service
                    NotificationManager mNotifyMgr =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    // Builds the notification and issues it.
                    assert mNotifyMgr != null;
                    mNotifyMgr.notify(mNotificationId, mBuilder.build());
                }
            }
        });
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
                mapIntent.putExtra("User", user);
                startActivityForResult(mapIntent, 0);
                return true;

            case R.id.about:
                //Start about activity
                Intent aboutIntent = new Intent(ListActivity.this, AboutActivity.class);
                aboutIntent.putExtra("User", user);
                startActivityForResult(aboutIntent, 0);
                return true;

            case R.id.logout:
                // Logout
                Intent intent = new Intent(ListActivity.this, LoginActivity.class);
                intent.putExtra("EXIT", true);
                startActivityForResult(intent, 0);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.textViewHead) {
            intent = new Intent(ListActivity.this, InformationActivity.class);
            TextView TV = v.findViewById(R.id.textViewHead);
            Location location = mDatabaseHelper.getLocation(TV.getText().toString());
            intent.putExtra("Location", location);
            intent.putExtra("User", user);
            intent.putExtra("isUnlocked", mDatabaseHelper.locationIsUnlocked(user.getId(), location.getId()));
            startActivityForResult(intent, 0);
        } else if (v.getId() == R.id.locationButton){
            if (!completed) {
                intent = new Intent(ListActivity.this, ListActivity.class);
                if (target.equals("Locked")) {
                    intent.putExtra("User", user);
                    intent.putExtra("Target", "Unlocked");
                } else {
                    intent.putExtra("User", user);
                    intent.putExtra("Target", "Locked");
                }
                startActivityForResult(intent, 0);
            }else{
                Toast.makeText(getApplicationContext(), "All locations completed. Good job!!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnStart() Executed");
        super.onStart();
    }

    @Override
    protected void onPause(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnPause() Executed");
        super.onPause();
    }

    @Override
    protected void onResume(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnResume() Executed");
        super.onResume();
    }

    @Override
    protected void onStop(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnStop() Executed");
        super.onStop();
    }

    @Override
    protected void onRestart(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnRestart() Executed");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnDestroy() Executed");
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(resultCode)
        {
            case 0:
                setResult(0);
                finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}


