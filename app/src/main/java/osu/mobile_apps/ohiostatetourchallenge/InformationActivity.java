package osu.mobile_apps.ohiostatetourchallenge;

import android.content.Context;
import android.content.pm.PackageManager;
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
    private GoogleMap mMap;
    private SimpleLocation deviceLocation;
    private SimpleLocation targetLocation;
    private SimpleLocation information;

    public void getDeviceLocation() {
        Context context = this;
        deviceLocation = new SimpleLocation(context, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TESTING", "InformationActivity onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Location location = (Location) getIntent().getSerializableExtra("Location");

        TextView TV = (TextView) findViewById(R.id.textView);
        ImageView image = findViewById(R.id.image);

        //TODO check if at location
        try {
            if (mLocationPermissionGranted == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                getDeviceLocation();
            }
        }catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }

            // Add a marker in current location
            LatLng myLocation = new LatLng(deviceLocation.getLatitude(), deviceLocation.getLongitude());
            LatLng targetCoords = new LatLng(location.getLatitude(),location.getLongitude());

            double distance = information.calculateDistance(myLocation.latitude,myLocation.longitude, targetCoords.latitude, targetCoords.longitude);

            Log.d("TESTING", "Distance: " + distance);

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
    }
}
