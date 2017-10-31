package osu.mobile_apps.ohiostatetourchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTING", "Question Activity onCreate()");
        setContentView(R.layout.activity_question);

        Location location = (Location) getIntent().getSerializableExtra("Location");
        TextView TV = (TextView) findViewById(R.id.textView);
        ImageView image = findViewById(R.id.image);

        //Display everything, since location is unlocked and viewable from anywhere
        String locationImage = location.getName().toLowerCase().replace(" ", "").replace("(", "").replace(")", "");
        int resourceId = this.getResources().getIdentifier(locationImage, "drawable", "osu.mobile_apps.ohiostatetourchallenge");
        image.setImageResource(resourceId);
        if (TV != null){
            TV.setText(location.getName());
        }
        TV = (TextView) findViewById(R.id.question);
        if (TV != null){
            //TODO get location question
            TV.setText("Question goes here");
        }

        //TODO set answer buttons text
        Button B1 = (Button) findViewById(R.id.Button1);
        B1.setText("Set1");
        Button B2 = (Button) findViewById(R.id.Button2);
        B2.setText("Set2");
        Button B3 = (Button) findViewById(R.id.Button3);
        B3.setText("Set3");
        Button B4 = (Button) findViewById(R.id.Button4);
        B4.setText("Set4");

    }

    public void onClick(View v){
        //TODO check if answer is correct
        Button selected = findViewById(v.getId());
        String answer = (String) selected.getText();

        //TODO If correct unlock location and go to location information

        //TODO If incorrect display incorrect toast

    }
}
