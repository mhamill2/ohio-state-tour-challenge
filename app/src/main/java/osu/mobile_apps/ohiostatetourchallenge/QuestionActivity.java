package osu.mobile_apps.ohiostatetourchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import database.OsuTourDbSchema.DatabaseHelper;

public class QuestionActivity extends AppCompatActivity {
    private Location location;
    private DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("TESTING", "Question Activity onCreate()");
        setContentView(R.layout.activity_question);

        user = (User) getIntent().getSerializableExtra("Location");
        location = (Location) getIntent().getSerializableExtra("Location");
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
            TV.setText("Select Correct to answer correctly");
        }

        //TODO set answer buttons text
        Button B1 = (Button) findViewById(R.id.Button1);
        B1.setText("Set1");
        Button B2 = (Button) findViewById(R.id.Button2);
        B2.setText("Set2");
        Button B3 = (Button) findViewById(R.id.Button3);
        B3.setText("Set3");
        Button B4 = (Button) findViewById(R.id.Button4);
        B4.setText("Correct");

    }

    public void onClick(View v){
        Button selected = findViewById(v.getId());
        String answer = (String) selected.getText();

        //TODO check if selected button was correct
        if(selected.getText().toString().equals("Correct")){
            //TODO If correct unlock location and go to location information
            Toast.makeText(getApplicationContext(), "That is the correct answer!!",
                    Toast.LENGTH_SHORT).show();

            //TODO unlock location

            //Start information activity
            Intent intent = new Intent(QuestionActivity.this, InformationActivity.class);
            intent.putExtra("Location", location.getName());
            intent.putExtra("User", user);
            startActivity(intent);

        }else{
            //If incorrect, display incorrect toast
            Toast.makeText(getApplicationContext(), "That is not the correct answer.",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
