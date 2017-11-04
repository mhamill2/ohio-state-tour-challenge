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

import java.util.List;

import database.OsuTourDbSchema.DatabaseHelper;

public class QuestionActivity extends AppCompatActivity {
    private Location location;
    private DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);
    private User user;

    // back button returns to list of locations
    @Override
    public void onBackPressed() {
        Intent intent;
        String caller = getIntent().getStringExtra("caller");
        try {
            Class callerClass = Class.forName(caller);
            intent = new Intent(QuestionActivity.this, callerClass);
            intent.putExtra("User", user);
            intent.putExtra("Target", "Locked");
            startActivity(intent);
        } catch(ClassNotFoundException e) {
            intent = new Intent(QuestionActivity.this, ListActivity.class);
            intent.putExtra("User", user);
            intent.putExtra("Target", "Locked");
            startActivity(intent);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnCreate() Executed");
        setContentView(R.layout.activity_question);

        user = (User) getIntent().getSerializableExtra("User");
        location = (Location) getIntent().getSerializableExtra("Location");
        TextView TV = (TextView) findViewById(R.id.textView);
        ImageView image = findViewById(R.id.image);

        //Display everything, since location is unlocked and viewable from anywhere
        String locationImage = location.getName().toLowerCase().replace(" ", "").replace("(", "").replace(")", "");
        int resourceId = this.getResources().getIdentifier(locationImage, "drawable", "osu.mobile_apps.ohiostatetourchallenge");
        image.setImageResource(resourceId);
        Question question = new Question();
        if (TV != null){
            TV.setText(location.getName());
        }
        TV = (TextView) findViewById(R.id.question);
        if (TV != null){
            question = mDatabaseHelper.getQuestion(location.getId());
            TV.setText(question.getText());
        }

        /*
         * Set a tag for each button to determine if the answer is correct or not. We can retrieve
         * the tag in the onClick method.
         */
        List<QuestionAnswer> questionAnswers = mDatabaseHelper.getAnswers(question.getId());
        Button B1 = (Button) findViewById(R.id.Button1);
        B1.setText(mDatabaseHelper.getAnswerText(questionAnswers.get(0).getAnswerId()));
        setCorrectTag(B1, questionAnswers.get(0).isCorrect());
        Button B2 = (Button) findViewById(R.id.Button2);
        B2.setText(mDatabaseHelper.getAnswerText(questionAnswers.get(1).getAnswerId()));
        setCorrectTag(B2, questionAnswers.get(1).isCorrect());
        Button B3 = (Button) findViewById(R.id.Button3);
        B3.setText(mDatabaseHelper.getAnswerText(questionAnswers.get(2).getAnswerId()));
        setCorrectTag(B3, questionAnswers.get(2).isCorrect());
        Button B4 = (Button) findViewById(R.id.Button4);
        B4.setText(mDatabaseHelper.getAnswerText(questionAnswers.get(3).getAnswerId()));
        setCorrectTag(B4, questionAnswers.get(3).isCorrect());
    }

    public void onClick(View v){
        Button selected = findViewById(v.getId());
        String answer = (String) selected.getText();
        // The tag is an Object, so we need to convert it to String and parse the Boolean
        String buttonTag = selected.getTag().toString();
        boolean correct = false;
        try {
            correct = Boolean.parseBoolean(buttonTag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(correct){

            //If correct unlock location and go to location information
            Toast.makeText(getApplicationContext(), "That is the correct answer!!",
                    Toast.LENGTH_SHORT).show();

            //unlock location
            mDatabaseHelper.completeLocation(user.getId(), location.getId());

            //Start information activity
            Intent intent = new Intent(QuestionActivity.this, InformationActivity.class);
            intent.putExtra("Location", location);
            intent.putExtra("User", user);
            intent.putExtra("isUnlocked", mDatabaseHelper.locationIsUnlocked(user.getId(), location.getId()));
            startActivity(intent);

        }else{
            //If incorrect, display incorrect toast
            Toast.makeText(getApplicationContext(), "That is not the correct answer. Try again!",
                    Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStart(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnStart() Executed");
        super.onStart();
    }

    @Override
    protected void onPause(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnPause() Executed");
        super.onPause();
    }

    @Override
    protected void onResume(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnResume() Executed");
        super.onResume();
    }

    @Override
    protected void onStop(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnStop() Executed");
        super.onStop();
    }

    @Override
    protected void onRestart(){
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnRestart() Executed");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d("LIFECYCLE",this.getClass().getSimpleName().toString() + " OnDestroy() Executed");
        super.onDestroy();
    }

    public void setCorrectTag(Button b, boolean correct) {
        if(correct) {
            b.setTag(true);
        } else {
            b.setTag(false);
        }
    }

}
