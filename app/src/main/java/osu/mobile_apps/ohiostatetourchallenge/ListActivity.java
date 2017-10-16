package osu.mobile_apps.ohiostatetourchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTING","ON CREATE FOR LIST");
        setContentView(R.layout.activity_list);
    }
}
