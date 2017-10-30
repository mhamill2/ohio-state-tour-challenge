package osu.mobile_apps.ohiostatetourchallenge;

import android.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import im.delight.android.location.SimpleLocation;

public class InformationActivity extends AppCompatActivity {
    private int mLocationPermissionGranted;
    private SimpleLocation deviceLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTING", "InformationActivity onCreate()");
        setContentView(R.layout.activity_information);

        TextView TV;

        //TODO Check if location is locked for user
        //if (location locked) {
        //Check for location permission
            mLocationPermissionGranted = ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION);

            //TODO get distance to location, check if close enough
            deviceLocation = new SimpleLocation(this);
            // if we can't access the location yet
            if (!deviceLocation.hasLocationEnabled()) {
                // ask the user to enable location access
            SimpleLocation.openSettings(this);
            }

            Location location = (Location) getIntent().getSerializableExtra("Location");

            Log.d("TESTING","Target: " + location.getLatitude() + ", " + location.getLongitude());
            Log.d("TESTING","" + deviceLocation.getLatitude() + ", " + deviceLocation.getLongitude());
            SimpleLocation.Point myCoords = new SimpleLocation.Point(deviceLocation.getLatitude(), deviceLocation.getLongitude());
            SimpleLocation.Point targetCoords = new SimpleLocation.Point(location.getLatitude(), location.getLongitude());

            double distance = deviceLocation.calculateDistance(myCoords, targetCoords);
            Log.d("TESTING", "Distance: " + distance);

                /* TODO
                //meters?
                if(distance > 100){
                    //Say too far away
                    TV = (TextView) findViewById(R.id.description);
                    if (TV != null){
                        TV.setText("Please move closer to location");
                    }
                }else{
                    //Show challenge question
                    Intent intent = new Intent(ListActivity.this, QuestiontActivity.class);
                    intent.putExtra("Location", location.getName());
                    startActivity(intent);
                }
                */
        //}else{
            //Display everything, since location is unlocked and viewable from anywhere
            TV = (TextView) findViewById(R.id.textView);
            ImageView image = findViewById(R.id.image);
            String locationImage = location.getName().toLowerCase().replace(" ", "").replace("(", "").replace(")", "");
            int resourceId = this.getResources().getIdentifier(locationImage, "drawable", "osu.mobile_apps.ohiostatetourchallenge");
            image.setImageResource(resourceId);
            if (TV != null){
                TV.setText(location.getName());
            }
            TV = (TextView) findViewById(R.id.description);
            if (TV != null){
                TV.setText(location.getDescription());
            }
            Log.d("TESTING", "Data = " + location.getName());
        //}
    }
}
