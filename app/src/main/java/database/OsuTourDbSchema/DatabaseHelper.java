package database.OsuTourDbSchema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import osu.mobile_apps.ohiostatetourchallenge.Location;
import osu.mobile_apps.ohiostatetourchallenge.Question;
import osu.mobile_apps.ohiostatetourchallenge.QuestionAnswer;
import osu.mobile_apps.ohiostatetourchallenge.User;

/*
 * This class is used to handle database creation and updating.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 10;
    private static final String DATABASE_NAME = "osu_tour.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        // CREATE User Table
        db.execSQL("create table " + OsuTourDbSchema.UserTable.NAME + "(" + OsuTourDbSchema.UserTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.UserTable.Cols.USER_NAME + " varchar(20) not null, " +
                OsuTourDbSchema.UserTable.Cols.PASSWORD + " varchar(20))"
        );
        db.execSQL("INSERT INTO " + OsuTourDbSchema.UserTable.NAME + " VALUES(1, 'hamill.33', 'password')");
        db.execSQL("INSERT INTO " + OsuTourDbSchema.UserTable.NAME + " VALUES(2, 'Trambacher', 'Testing')");
        db.execSQL("INSERT INTO " + OsuTourDbSchema.UserTable.NAME + " VALUES(3, 'mhamill', 'password')");

        // CREATE Location Table
        db.execSQL("create table " + OsuTourDbSchema.LocationTable.NAME+ "(" + OsuTourDbSchema.LocationTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.LocationTable.Cols.NAME + " varchar(50) not null, " +
                OsuTourDbSchema.LocationTable.Cols.LATITUDE + " decimal(9, 6) not null, " +
                OsuTourDbSchema.LocationTable.Cols.LONGITUDE + " decimal(9, 6) not null, " +
                OsuTourDbSchema.LocationTable.Cols.HISTORY + " text)"
        );

        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(1, 'Ohio Union', 39.997687, -83.008590, 'The Ohio Union serves as an activity center for students of The Ohio State University. When the Union was established in 1910, it was the first student union at a public university. The Ohio Union provides facilities for student activities, organizations/events, and campus / community interaction. Many student services and programs are housed in the union, along with dining and recreational facilities. It also serves as the home base for the D-Tix program, which provides discounted tickets to students. ')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(2, 'Recreational and Physical Activity Center (RPAC)', 39.999410, -83.018269, 'The RPAC is the main recreational facility on campus, and offers over half a million square feet of recreation, aquatic, fitness, and meeting space. The RPAC features two on-campus dining locations, a 50-meter competitive pool, 12 wood courts, 10 racquetball courts, 4 squash courts, a four-lane jogging/walking track, five multipurpose rooms, and approximately 27,500 square feet of fitness space with state-of-the-art equipment.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(3, 'Thompson Library', 39.999221, -83.014846, 'The William Oxley Thompson Memorial Library (commonly referred to as the Thompson Library) is the main library at Ohio State University\''s Columbus campus. It is the university\''s largest library and houses its main stacks, special collections, rare books and manuscripts, university archives, and many departmental subject libraries. The library was originally built in 1912, and was renovated in 1951, 1977, and 2009. It is named in honor of the university\''s fifth president, William Oxley Thompson.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(4, 'Hale Hall', 39.996984, -83.011769, 'This recently renovated facility welcomes all students and guests as home to the Office of Diversity and Inclusion. It is also home to one of the finest Black Cultural Centers in the country, an exceptional art gallery and several student support centers and student retention offices.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(5, 'Orton Hall', 39.998345, -83.011951, 'Orton Hall, one of the oldest remaining buildings on Ohio State University campus, opened in 1893 and is named after Dr Edward Orton, Sr. who served as OSUs first president, Professor of Geology from 1873 to 1899, and Ohios State Geologist from 1882 until his death in 1899. Orton Hall is a tribute to this mans dedicated service towards the understanding of the geology of Ohio.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(6, 'University Hall', 40.000524, -83.014409, 'The original University Hall was constructed in 1873, and contained a majority of the university functions, including both student and faculty housing. After being closed in 1968 for safety reasons, the building was completely torn down in 1971. At this time the old hall was removed from the National Register of Historic Places. The current University Hall was reconstructed in its place, taking an almost exact outward copy of the original building, but updating the inner workings. It was completed in 1976.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(7, 'Wexner Center for the Arts', 40.000691, -83.009297, 'The Wexner Center for the Arts is The Ohio State University’s \"multidisciplinary, international laboratory for the exploration and advancement of contemporary art\". The Wexner Center opened in November 1989, named in honor of the father of Limited Brands founder Leslie Wexner, who was a major donor to the Center. The Wexner Center is a lab and public gallery, but not a museum, as it does not collect art.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(8, 'Wilce Student Health Center', 39.9996, -83.0164, 'The Wilce Student Health Center is an on campus accredited outpatient facility as well as a pharmacy. Appointments can be made through BuckMD for a variety of reasons. All students enrolled at the Ohio State University are eligible to use Student Life Student Health Services (SLSHS), regardless of health insurance coverage. Students are responsible for their deductibles, according to their individual insurance plans.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(9, 'Traditions at Scott', 40.00415, -83.0134, 'Traditions at Scott is a 1,000-seat, all-you-care-to-eat dining operation with kitchens covering two floors. Scott features a wide variety of food choices as well as a \"Solutions\" station that offers gluten free, vegan and other options for dietary restriction. Ask to talk to a chef if you have any dietary concerns. It is the largest of three Traditions locations on campus. Kennedy and Morril commons are the other two.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(10, 'Ohio Stadium', 40.002936, -83.019555, 'The Ohio Stadium, also known as the horseshoe due to its distincitve shape, was built in 1922 and now has 104,944 seats. You can look forward to joining other students on Saturdays to watch the Buckeyes play football. Arrive early to watch the band script Ohio and stay till the end to sing Carmen. Ticket information is sent out via email.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(11, 'Caldwell Laboratory', 40.002454, -83.014992, 'Caldwell lab is home to the largest open lab for Computer Science and Engineering students, and labs for Electrical Computer Engineering students.')");
        // CREATE Question Table
        db.execSQL("create table " + OsuTourDbSchema.QuestionTable.NAME+ "(" + OsuTourDbSchema.QuestionTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.QuestionTable.Cols.TEXT + " text)"
        );
        //Thompson
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(1, 'Who is there a sculpture of outside of the Thompson Library?')");
        //Union
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(2, 'What is hanging from the ceiling of the Great Hall in the Union?')");
        //RPAC
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(3, 'What is the name of the tinted bridge?')");
        //Hale
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(4, 'What is the name of the library containing items donated by Dr. Hale?')");
        //Orton
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(5, 'The museum inside Orton Hall houses the largest fossil collection in the ____?')");
        //University
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(6, 'University Hall was rebuilt in 1976. Which 3 aspects of the original building can still be seen today?')");
        //Wexner
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(7, 'Which of these auditoriums is administered by the School of Music?')");
        //Wilce
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(8, 'Since when does the Wilce Center house the Student Health Services?')");
        //Scott
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(9, 'How many floors does Traditions at Scott have?')");
        //Stadium
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(10, 'What are the colors of the flowers on the stadium?')");
        // Caldwell
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(11, 'What is the hand above the main door of Caldwell holding?')");


        // CREATE Answer Table
        db.execSQL("create table " + OsuTourDbSchema.AnswerTable.NAME+ "(" + OsuTourDbSchema.AnswerTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.AnswerTable.Cols.TEXT + " text not null)"
        );
        //Thompson
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(1, 'William Oxley Thompson')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(2, 'William Henry Thompson')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(3, 'Drake Oxley Thompson')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(4, 'Drake Henry Thompson')");
        //Union
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(5, 'Vines')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(6, 'Buckeye Leaves')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(7, 'Trapeze Artist')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(8, 'Streamers')");
        //RPAC
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(9, 'Scarlet Walkway')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(10, 'Red Bridge')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(11, 'Spooky Skyway')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(12, 'Scarlet Skyway')");
        //Hale
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(13, 'Thompson Library')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(14, 'SEL')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(15, 'Frank W. Hale, Jr. Civil Rights Library')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(16, 'Hale Hall Library')");
        //Orton
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(17, 'State')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(18, 'Country')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(19, 'World')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(20, 'Midwest')");
        //University
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(21, 'Tower clock, hall entrance, and pillars')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(22, 'Tower clock, bells, and pillars')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(23, 'Bells, pillars, and statues')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(24, 'Hall entrance, stained glass windows, and false ceiling')");
        //Wexner
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(25, 'Mershon Auditorium')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(26, 'Weigel Auditorium')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(27, 'Thurber Auditorium')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(28, 'Univeristy Auditorium')");
        //Wilce
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(29, 'December 1969')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(30, 'March 2000')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(31, 'May 1915')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(32, 'August 1996')");
        //Scott
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(33, '75')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(34, '2')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(35, '1')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(36, '3')");
        //Stadium
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(37, 'There are no flowers on the stadium')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(38, 'Maize and Blue')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(39, 'Rose Red')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(40, 'Scarlet and Gray')");
        //Caldwell
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(41, 'Nothing')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(42, 'Buckeyes')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(43, 'Lighting Bolts')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(44, 'Wires')");

        // CREATE QuestionAnswer Table
        db.execSQL("create table " + OsuTourDbSchema.QuestionAnswerTable.NAME+ "(" + OsuTourDbSchema.QuestionAnswerTable.Cols.ID + " integer primary key autoincrement, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.QUESTION_ID + " int not null, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.ANSWER_ID + " int not null, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.IS_CORRECT + " int not null)"
        );

        //Thompson
        int question = 1;
        String correct = "1";
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(1, " + Integer.toString(question) +", 1, "+correct+")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(2, " + Integer.toString(question) +", 2, 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(3, " + Integer.toString(question) +", 3, 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(4, " + Integer.toString(question) +", 4, 0)");
        //Union
        question = 2;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+",  " +correct+")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", 0)");
        //RPAC
        question = 3;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", " +correct+")");

        //Hale
        question = 4;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", "+correct+")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", 0)");
        //Orton
        question = 5;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", " +correct+ ")");
        //University
        question = 6;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", " + correct + ")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", 0)");

        //Wexner
        question = 7;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", " +correct+ ")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", 0)");

        //Wilce
        question = 8;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", " +correct+ ")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", 0)");

        //Scott
        question = 9;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", "+correct+")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", 0)");

        //Stadium
        question = 10;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", "+correct+")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", 0)");

        //Caldwell
        question = 11;
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(1+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(1+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(2+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(2+(4*(question-1)))+", 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(3+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(3+(4*(question-1)))+", "+correct+")");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(" + Integer.toString(4+(4*(question-1)))+", "+ Integer.toString(question) +", " + Integer.toString(4+(4*(question-1)))+", 0)");


        // CREATE LocationQuestion Table
        db.execSQL("create table " + OsuTourDbSchema.LocationQuestionTable.NAME+ "(" + OsuTourDbSchema.LocationQuestionTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.LocationQuestionTable.Cols.LOCATION_ID + " int not null, " +
                OsuTourDbSchema.LocationQuestionTable.Cols.QUESTION_ID + " int not null)"
        );
        //Thompson
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(1, 3, 1)");
        //Union
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(2, 1, 2)");
        //RPAC
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(3, 2, 3)");
        //Hale
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(4, 4, 4)");
        //Orton
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(5, 5, 5)");
        //University
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(6, 6, 6)");
        //Wexner
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(7, 7, 7)");
        //Wilce
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(8, 8, 8)");
        //Scott
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(9, 9, 9)");
        //Stadium
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(10, 10, 10)");
        //Caldwell
        db.execSQL("INSERT INTO " + OsuTourDbSchema.LocationQuestionTable.NAME + " VALUES(11, 11, 11)");

        // CREATE PlayerLocationsCompleted Table
        db.execSQL("create table " + OsuTourDbSchema.PlayerLocationCompletedTable.NAME + "(" + OsuTourDbSchema.PlayerLocationCompletedTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.PlayerLocationCompletedTable.Cols.USER_ID + " int not null, " +
                OsuTourDbSchema.PlayerLocationCompletedTable.Cols.LOCATION_ID + " int not null)"
        );
        db.execSQL("INSERT INTO " + OsuTourDbSchema.PlayerLocationCompletedTable.NAME + " VALUES(1, 1, 1)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + OsuTourDbSchema.QuestionAnswerTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OsuTourDbSchema.LocationQuestionTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OsuTourDbSchema.PlayerLocationCompletedTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OsuTourDbSchema.AnswerTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OsuTourDbSchema.QuestionTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OsuTourDbSchema.LocationTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OsuTourDbSchema.UserTable.NAME);
        onCreate(db);
    }

    public User getUser(String userName) {
        User user = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.UserTable.Cols.ID, OsuTourDbSchema.UserTable.Cols.USER_NAME, OsuTourDbSchema.UserTable.Cols.PASSWORD};
        Cursor cursor =
                db.query(OsuTourDbSchema.UserTable.NAME,
                        columns,
                        OsuTourDbSchema.UserTable.Cols.USER_NAME + " = ?",
                        new String[] {String.valueOf(userName)},
                        null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            user.setId(cursor.getInt(0));
            user.setUserName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            cursor.close();
        }

        return user;
    }

    public Location getLocation(String name) {
        Location loc = new Location();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { OsuTourDbSchema.LocationTable.Cols.ID,
                OsuTourDbSchema.LocationTable.Cols.NAME,
                OsuTourDbSchema.LocationTable.Cols.LATITUDE,
                OsuTourDbSchema.LocationTable.Cols.LONGITUDE,
                OsuTourDbSchema.LocationTable.Cols.HISTORY};
        Cursor cursor =
                db.query(OsuTourDbSchema.LocationTable.NAME, columns,
                        OsuTourDbSchema.LocationTable.Cols.NAME + " =?",
                        new String[] {String.valueOf(name)},
                        null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            loc.setId(cursor.getInt(0));
            loc.setName(cursor.getString(1));
            loc.setLatitude(cursor.getDouble(2));
            loc.setLongitude(cursor.getDouble(3));
            loc.setDescription(cursor.getString(4));
            cursor.close();
        }
        return loc;
    }

    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.LocationTable.Cols.ID,
                OsuTourDbSchema.LocationTable.Cols.NAME,
                OsuTourDbSchema.LocationTable.Cols.HISTORY,
                OsuTourDbSchema.LocationTable.Cols.LATITUDE,
                OsuTourDbSchema.LocationTable.Cols.LONGITUDE};
        Cursor cursor = db.query(OsuTourDbSchema.LocationTable.NAME,
                columns,
                null,
                null,
                null,
                null,
                null);
        if (cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Location loc = new Location();
                loc.setId(cursor.getInt(0));
                loc.setName(cursor.getString(1));
                loc.setDescription(cursor.getString(2));
                loc.setLatitude(cursor.getDouble(3));
                loc.setLongitude(cursor.getDouble(4));
                locations.add(loc);
            }
            cursor.close();

        }
        return locations;
    }

    public boolean locationIsUnlocked(Integer userId, Integer locationId) {
        boolean locationUnlocked = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.PlayerLocationCompletedTable.Cols.USER_ID,
                OsuTourDbSchema.PlayerLocationCompletedTable.Cols.LOCATION_ID,};
        Cursor cursor =
                db.query(OsuTourDbSchema.PlayerLocationCompletedTable.NAME,
                        columns,
                        OsuTourDbSchema.PlayerLocationCompletedTable.Cols.USER_ID + " = ? AND " + OsuTourDbSchema.PlayerLocationCompletedTable.Cols.LOCATION_ID + " = ?",
                        new String[] {String.valueOf(userId), String.valueOf(locationId)},
                        null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            locationUnlocked = true;
            cursor.close();
        }
        return locationUnlocked;
    }

    public Question getQuestion(Integer locationId) {
        Question question = new Question();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.LocationQuestionTable.Cols.QUESTION_ID};
        // First cursor gets the questionId from the LocationQuestionTable
        Cursor cursor = db.query(OsuTourDbSchema.LocationQuestionTable.NAME,
                columns,
                OsuTourDbSchema.LocationQuestionTable.Cols.LOCATION_ID + " = ?",
                new String[] {String.valueOf(locationId)},
                null, null, null, null);
        Integer questionId;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            questionId = cursor.getInt(0);
            String[] columns2 = {OsuTourDbSchema.QuestionTable.Cols.ID,
                    OsuTourDbSchema.QuestionTable.Cols.TEXT};
            // Second cursor gets the question text from the Question table
            Cursor cursor2 = db.query(OsuTourDbSchema.QuestionTable.NAME,
                    columns2,
                    OsuTourDbSchema.QuestionTable.Cols.ID + " = ?",
                    new String[] {String.valueOf(questionId)},
                    null, null, null, null);
            if (cursor2 != null && cursor2.getCount() > 0) {
                cursor2.moveToFirst();
                question.setId(cursor2.getInt(0));
                question.setText(cursor2.getString(1));
                cursor2.close();
            }
            cursor.close();
        }

        return question;
    }

    public List<QuestionAnswer> getAnswers(Integer questionId){
        List<QuestionAnswer> questionAnswers = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.QuestionAnswerTable.Cols.ID,
                OsuTourDbSchema.QuestionAnswerTable.Cols.QUESTION_ID,
                OsuTourDbSchema.QuestionAnswerTable.Cols.ANSWER_ID,
                OsuTourDbSchema.QuestionAnswerTable.Cols.IS_CORRECT};
        Cursor cursor = db.query(OsuTourDbSchema.QuestionAnswerTable.NAME,
                columns,
                OsuTourDbSchema.QuestionAnswerTable.Cols.QUESTION_ID + " = ?",
                new String[] {String.valueOf(questionId)},
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                QuestionAnswer qa = new QuestionAnswer();
                qa.setId(cursor.getInt(0));
                qa.setQuestionId(cursor.getInt(1));
                qa.setAnswerId(cursor.getInt(2));
                qa.setCorrect(cursor.getInt(3) != 0);
                questionAnswers.add(qa);
            }
            cursor.close();
        }


        return questionAnswers;
    }

    public String getAnswerText(Integer answerId) {
        String answer = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.AnswerTable.Cols.TEXT};
        Cursor cursor = db.query(OsuTourDbSchema.AnswerTable.NAME,
                columns,
                OsuTourDbSchema.AnswerTable.Cols.ID + " = ?",
                new String[] {String.valueOf(answerId)},
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            answer = cursor.getString(0);
            cursor.close();
        }
        return answer;
    }

    public void completeLocation(Integer userId, Integer locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues c = new ContentValues();
        c.put(OsuTourDbSchema.PlayerLocationCompletedTable.Cols.LOCATION_ID, locationId);
        c.put(OsuTourDbSchema.PlayerLocationCompletedTable.Cols.USER_ID, userId);
        long newRowId = db.insert(OsuTourDbSchema.PlayerLocationCompletedTable.NAME, null, c);
        if(newRowId < 0) {
            Log.d("ERROR", "Incomplete location: row not saved. ");
        }
    }

}
