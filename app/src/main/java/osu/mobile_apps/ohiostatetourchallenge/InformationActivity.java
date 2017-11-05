package osu.mobile_apps.ohiostatetourchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import im.delight.android.location.SimpleLocation;

public class InformationActivity extends AppCompatActivity {
    private User user;
    private boolean backToMap;

    // back button returns to list of locations
    @Override
    public void onBackPressed() {
        Intent intent;
        if (backToMap) {
            intent = new Intent(this, MapActivity.class);
        } else {
            intent = new Intent(this, ListActivity.class);
            String isLocked;
            if (this.getIntent().getBooleanExtra("isUnlocked", false)) {
                isLocked = "Unlocked";
            } else {
                isLocked = "Locked";
            }
            intent.putExtra("Target", isLocked);
        }
        intent.putExtra("User", user);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnCreate() Executed");
        setContentView(R.layout.activity_information);

        TextView TV;
        user = (User) getIntent().getSerializableExtra("User");

        boolean isUnlocked = getIntent().getBooleanExtra("isUnlocked", false);
        Location location = (Location) getIntent().getSerializableExtra("Location");

        if (!isUnlocked) {
        //get distance to location, check if close enough within 100 meters
            SimpleLocation deviceLocation = new SimpleLocation(this);
        // if we can't access the location yet
        if (!deviceLocation.hasLocationEnabled()) {
            // ask the user to enable location access
            SimpleLocation.openSettings(this);
        }

        SimpleLocation.Point myCoords = new SimpleLocation.Point(deviceLocation.getLatitude(), deviceLocation.getLongitude());
        SimpleLocation.Point targetCoords = new SimpleLocation.Point(location.getLatitude(), location.getLongitude());

        double distance = SimpleLocation.calculateDistance(myCoords, targetCoords);
        Log.d("TESTING", "Distance: " + distance + " meters");
            if (distance > 250) {
                //Say too far away
                TV = findViewById(R.id.description);
                if (TV != null) {
                    TV.setText(R.string.location_too_far_text);
                    TV = findViewById(R.id.textView);
                    TV.setText(location.getName());
                    ImageView image = findViewById(R.id.image);
                    int resourceId = this.getResources().getIdentifier("questionmark", "drawable", "osu.mobile_apps.ohiostatetourchallenge");
                    image.setImageResource(resourceId);
                    if (this.getIntent().getBooleanExtra("fromMap", false)) {
                        backToMap = true;
                    }
                }
            } else {
                //Show challenge question
                Intent intent = new Intent(InformationActivity.this, QuestionActivity.class);
                String caller = getIntent().getStringExtra("caller");
                if(caller == null ) {
                    caller = "ListActivity";
                }
                intent.putExtra("caller", caller);
                intent.putExtra("Location", location);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        } else {
            //Display everything, since location is unlocked and viewable from anywhere
            TV = findViewById(R.id.textView);
            ImageView image = findViewById(R.id.image);
            String locationImage = location.getName().toLowerCase().replace(" ", "").replace("(", "").replace(")", "");
            int resourceId = this.getResources().getIdentifier(locationImage, "drawable", "osu.mobile_apps.ohiostatetourchallenge");
            image.setImageResource(resourceId);
            if (TV != null) {
                TV.setText(location.getName());
            }
            TV = findViewById(R.id.description);
            if (TV != null) {
                TV.setText(location.getDescription());
            }
            Log.d("TESTING", "Data = " + location.getName());
            if (this.getIntent().getBooleanExtra("fromMap", false)) {
                backToMap = true;
            }
        }
    }

    public void onClick(View v){
        Intent intent = new Intent(InformationActivity.this, ListActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
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

}