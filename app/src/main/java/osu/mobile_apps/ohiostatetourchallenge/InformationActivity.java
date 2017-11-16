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
        startActivityForResult(intent, 0);
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
        //get distance to location, check if close enough within 50 meters
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
            if (distance > 50) {
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
                startActivityForResult(intent, 0);
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
        startActivityForResult(intent, 0);
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