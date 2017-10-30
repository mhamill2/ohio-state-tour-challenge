package osu.mobile_apps.ohiostatetourchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TESTING", "InformationActivity onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Location location = (Location) getIntent().getSerializableExtra("Location");

        TextView TV = (TextView) findViewById(R.id.textView);
        ImageView image = findViewById(R.id.image);
        String locationImage = location.getName().toLowerCase().replace(" ", "").replace("(", "").replace(")", "");
        int resourceId = this.getResources().getIdentifier(locationImage, "drawable", "osu.mobile_apps.ohiostatetourchallenge");
        image.setImageResource(resourceId);
        if (TV != null){
            TV.setText(location.getName());
        }else{
            Log.d("TESTING","TV is null");
        }
        TV = (TextView) findViewById(R.id.description);
        if (TV != null){
            TV.setText(location.getDescription());
        }else{
            Log.d("TESTING","TV is null");
        }
        Log.d("TESTING", "Data = " + location.getName());
    }
}
