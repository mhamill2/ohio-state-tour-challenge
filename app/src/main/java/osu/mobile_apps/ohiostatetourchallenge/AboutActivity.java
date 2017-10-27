package osu.mobile_apps.ohiostatetourchallenge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView about_text = (TextView) findViewById(R.id.about_text);
        String about = "This app allows you to become familiar with campus in an interactive manner. Select a location via the list or" +
                "map. If you have not been to that location before then you will see a challenge question, you must be at the indicated location to " +
                "see the question. After you answer the question correctly you will unlock additional information about that location.\n\n\n" +
                "Created by: Michael Hammil, Trevor Rambacher, Vyyom Kelkar.\nFor CSE 5236";
        about_text.setText(about);
    }

}
