package osu.mobile_apps.ohiostatetourchallenge;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

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
    private List<Location> mCompletedLocations;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE",this.getClass().getSimpleName() + " OnCreate() Executed");
        setContentView(R.layout.activity_map);

        user = mDatabaseHelper.getUser(this.getIntent().getStringExtra("UserName"));

        // Get locations and completed locations.
        mLocations = mDatabaseHelper.getLocations();
        mCompletedLocations = mDatabaseHelper.getCompletedLocations(user.getId());

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
                mMap.setMinZoomPreference(0);
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

            // Change each completed location marker to green
            for (int j = 0; j < mCompletedLocations.size(); j++) {
                if (m.getTitle().equalsIgnoreCase(mCompletedLocations.get(j).getName())) {
                    m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    m.setTag("Complete");
                }
            }
            // TODO - remove if block after testing
            if (i == 1) {
                m.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                m.setTag("Complete");
            }
            createInfoWindow(m);
        }
    }

    private void createInfoWindow(Marker marker) {
        if (marker.getTag().equals("Complete")) {
            marker.setSnippet("Location Visited!");
        } else {
            marker.setSnippet("Location not visited! Distance to location = " +
                    round(calculateDistance(marker.getPosition().latitude,
                            marker.getPosition().longitude, myLocation.getLatitude(),
                            myLocation.getLongitude())) + " m");
        }
    }

    // TODO - go from location marker to information activity
    @Override
    public void onInfoWindowClick(Marker marker) {
//        Location loc = new Location();
//        loc.setLatitude(marker.getPosition().latitude);
//        loc.setLongitude(marker.getPosition().longitude);
//        loc.setName(marker.getTitle());
//        loc.setId(Integer.parseInt(marker.getId()));

        Location loc = mDatabaseHelper.getLocation(marker.getTitle());

        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra("caller", "MapActivity");
        intent.putExtra("Location", loc);
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
