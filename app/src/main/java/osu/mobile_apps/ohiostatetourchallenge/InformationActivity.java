package osu.mobile_apps.ohiostatetourchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TESTING", "InformationActivity onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        String locationName = getIntent().getExtras().getString("Header");

        TextView TV = (TextView) findViewById(R.id.textView);
        if (TV != null){
            TV.setText(locationName);
        }else{
            Log.d("TESTING","TV is null");
        }
        Log.d("TESTING", "Data = " + locationName);
    }
}
