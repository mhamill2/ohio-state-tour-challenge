package osu.mobile_apps.ohiostatetourchallenge;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import database.OsuTourDbSchema.DatabaseHelper;
import im.delight.android.location.SimpleLocation;

import static im.delight.android.location.SimpleLocation.calculateDistance;
import static java.lang.Math.round;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private User user;
    private int mLocationPermissionGranted;
    private SimpleLocation myLocation;
    private DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
    private List<Location> mLocations;
    private List<Location> unlocked;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("User", user);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleLocation deviceLocation = new SimpleLocation(this);
        if (!deviceLocation.hasLocationEnabled()) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Location Services Disabled!")
                    .setMessage("This application requires location services. " +
                            "Please enable location services before continuing. ")
                    .setNeutralButton("Enable location services", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SimpleLocation.openSettings(getApplicationContext());
                        }
                    })
                    .show();
        }
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnCreate() Executed");
        setContentView(R.layout.activity_map);

        user = (User) this.getIntent().getSerializableExtra("User");

        // Get locations and completed locations.
        mLocations = mDatabaseHelper.getLocations();
        //mCompletedLocations = mDatabaseHelper.getCompletedLocations(user.getId());

        //Get list of locked and unlocked locations
        unlocked = new ArrayList<>();
        for(Location place: mLocations){
            if(mDatabaseHelper.locationIsUnlocked(user.getId(), place.getId())){
                unlocked.add(place);
            }
        }

        mLocationPermissionGranted = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        mMap.setOnInfoWindowClickListener(this);
    }

    public void getDeviceLocation() {
        Context context = this;
        myLocation = new SimpleLocation(context, false);
    }

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.setMinZoomPreference(15);
                mMap.setMaxZoomPreference(20);
                getDeviceLocation();
                LatLng currentLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                createMarkers();
                mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);

                // Add a marker in Caldwell and move the camera
                LatLng caldwell = new LatLng(40.0024380, -83.0150030);
                mMap.addMarker(new MarkerOptions().position(caldwell).title("Marker in Caldwell lab"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(caldwell));
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void createMarkers() {
        for (int i = 0; i < mLocations.size(); i++) {
            // Add markers for each location
            Marker m;
            m = mMap.addMarker(new MarkerOptions().position(new LatLng(mLocations.get(i).getLatitude(),
                    mLocations.get(i).getLongitude())).title(mLocations.get(i).getName()));
            m.setTag("Incomplete");

            // Change locations in range to yellow markers
            int distance = (int) round(calculateDistance(m.getPosition().latitude,
                    m.getPosition().longitude, myLocation.getLatitude(),
                    myLocation.getLongitude()));
            if (distance < 50) {
                m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
            }

            // Change each completed location marker to green
            for (int j = 0; j < unlocked.size(); j++) {
                if (m.getTitle().equalsIgnoreCase(unlocked.get(j).getName())) {
                    m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    m.setTag("Complete");
                }
            }
            createInfoWindow(m);
        }
    }

    private void createInfoWindow(Marker marker) {
        float distance = round(calculateDistance(marker.getPosition().latitude,
                marker.getPosition().longitude, myLocation.getLatitude(),
                myLocation.getLongitude()));
        if (Objects.equals(marker.getTag(), "Complete")) {
            marker.setSnippet("Location Visited!");
        } else {
            marker.setSnippet("Location not visited! Distance to location = " +
                    distance + " m");
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Location loc = mDatabaseHelper.getLocation(marker.getTitle());

        Intent intent = new Intent(this, InformationActivity.class);
        if (Objects.equals(marker.getTag(), "Complete")) {
            intent.putExtra("isUnlocked", true);
        } else {
            intent.putExtra("isUnlocked", false);
        }
        intent.putExtra("fromMap", true);
        intent.putExtra("caller", "MapActivity");
        intent.putExtra("Location", loc);
        intent.putExtra("User", user);
        startActivityForResult(intent, 0);
    }

    private BroadcastReceiver mGpsSwitchStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().matches(getString(R.string.location_change))) {
                recreate();
            }
        }
    };


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
        registerReceiver(mGpsSwitchStateReceiver, new IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION));
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
        unregisterReceiver(mGpsSwitchStateReceiver);
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
