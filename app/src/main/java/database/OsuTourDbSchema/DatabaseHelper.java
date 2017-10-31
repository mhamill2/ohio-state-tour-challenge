package database.OsuTourDbSchema;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import osu.mobile_apps.ohiostatetourchallenge.Location;
import osu.mobile_apps.ohiostatetourchallenge.User;

/*
 * This class is used to handle database creation and updating.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 2;
    public static final String DATABASE_NAME = "osu_tour.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        // CREATE User Table
        db.execSQL("create table " + OsuTourDbSchema.UserTable.NAME + "(" + OsuTourDbSchema.UserTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.UserTable.Cols.USER_NAME + " varchar(20) not null, " +
                OsuTourDbSchema.UserTable.Cols.PASSWORD + " varchar(20) not null)"
        );
        db.execSQL("INSERT INTO " + OsuTourDbSchema.UserTable.NAME + " VALUES(1, 'hamill.33', 'password')");
        db.execSQL("INSERT INTO " + OsuTourDbSchema.UserTable.NAME + " VALUES(2, 'Trambacher', 'Testing')");

        // CREATE Location Table
        db.execSQL("create table " + OsuTourDbSchema.LocationTable.NAME+ "(" + OsuTourDbSchema.LocationTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.LocationTable.Cols.NAME + " varchar(50) not null, " +
                OsuTourDbSchema.LocationTable.Cols.LATITUDE + " decimal(9, 6) not null, " +
                OsuTourDbSchema.LocationTable.Cols.LONGITUDE + " decimal(9, 6) not null, " +
                OsuTourDbSchema.LocationTable.Cols.HISTORY + " text)"
        );
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(1, 'Ohio Union', 39.997687, -83.008590, 'The Ohio Union serves as an activity center for students of The Ohio State University. When the Union was established in 1910, it was the first student union at a public university. The Ohio Union provides facilities for student activities, organizations/events, and campus / community interaction. Many student services and programs are housed in the union, along with dining and recreational facilities. It also serves as the home base for the D-Tix program, which provides discounted tickets to students.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(2, 'Recreational and Physical Activity Center (RPAC)', 39.999410, -83.018269, 'The RPAC is the main recreational facility on campus, and offers over half a million square feet of recreation, aquatic, fitness, and meeting space. The RPAC features two on-campus dining locations, a 50-meter competitive pool, 12 wood courts, 10 racquetball courts, 4 squash courts, a four-lane jogging/walking track, five multipurpose rooms, and approximately 27,500 square feet of fitness space with state-of-the-art equipment.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(3, 'Thompson Library', 39.999221, -83.014846, 'The William Oxley Thompson Memorial Library (commonly referred to as the Thompson Library) is the main library at Ohio State University\''s Columbus campus. It is the university\''s largest library and houses its main stacks, special collections, rare books and manuscripts, university archives, and many departmental subject libraries. The library was originally built in 1912, and was renovated in 1951, 1977, and 2009. It is named in honor of the university\''s fifth president, William Oxley Thompson.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(4, 'Hale Hall', 39.996984, -83.011769, 'This recently renovated facility welcomes all students and guests as home to the Office of Diversity and Inclusion. It is also home to one of the finest Black Cultural Centers in the country, an exceptional art gallery and several student support centers and student retention offices')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(5, 'Orton Hall', 39.998345, -83.011951, 'Orton Hall, one of the oldest remaining buildings on Ohio State University campus, opened in 1893 and is named after Dr Edward Orton, Sr. who served as OSUs first president, Professor of Geology from 1873 to 1899, and Ohios State Geologist from 1882 until his death in 1899. Orton Hall is a tribute to this mans dedicated service towards the understanding of the geology of Ohio.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(6, 'University Hall', 40.000524, -83.014409, 'The original University Hall was constructed in 1873, and contained a majority of the university functions, including both student and faculty housing. After being closed in 1968 for safety reasons, the building was completely torn down in 1971. At this time the old hall was removed from the National Register of Historic Places. The current University Hall was reconstructed in its place, taking an almost exact outward copy of the original building, but updating the inner workings. It was completed in 1976.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(7, 'Wexner Center for the Arts', 40.000691, -83.009297, 'The Wexner Center for the Arts is The Ohio State University’s \"multidisciplinary, international laboratory for the exploration and advancement of contemporary art\". The Wexner Center opened in November 1989, named in honor of the father of Limited Brands founder Leslie Wexner, who was a major donor to the Center. The Wexner Center is a lab and public gallery, but not a museum, as it does not collect art.')");

        // CREATE Question Table
        db.execSQL("create table " + OsuTourDbSchema.QuestionTable.NAME+ "(" + OsuTourDbSchema.QuestionTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.QuestionTable.Cols.TEXT + " text)"
        );
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionTable.NAME +" VALUES(1, 'Who is there a sculpture of outside of the Thompson Library?')");

        // CREATE Answer Table
        db.execSQL("create table " + OsuTourDbSchema.AnswerTable.NAME+ "(" + OsuTourDbSchema.AnswerTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.AnswerTable.Cols.TEXT + " text not null)"
        );
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(1, 'William Oxley Thompson')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(2, 'William Henry Thompson')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(3, 'Drake Oxley Thompson')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.AnswerTable.NAME +" VALUES(4, 'Drake Henry Thompson')");

        // CREATE QuestionAnswer Table
        db.execSQL("create table " + OsuTourDbSchema.QuestionAnswerTable.NAME+ "(" + OsuTourDbSchema.QuestionAnswerTable.Cols.ID + " integer primary key autoincrement, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.QUESTION_ID + " int not null, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.ANSWER_ID + " int not null, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.IS_CORRECT + " int not null)"
        );
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(1, 1, 1, 1)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(2, 1, 1, 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(3, 1, 1, 0)");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.QuestionAnswerTable.NAME +" VALUES(4, 1, 1, 0)");

        // CREATE PlayerLocationsCompleted Table
        db.execSQL("create table " + OsuTourDbSchema.PlayerLocationCompletedTable.NAME + "(" + OsuTourDbSchema.PlayerLocationCompletedTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.PlayerLocationCompletedTable.Cols.USER_ID + " int not null, " +
                OsuTourDbSchema.PlayerLocationCompletedTable.Cols.LOCATION_ID + " int not null)"
        );
        db.execSQL("INSERT INTO " + OsuTourDbSchema.PlayerLocationCompletedTable.NAME + " VALUES(1, 1, 1)");

        // CREATE LocationQuestion Table
        db.execSQL("create table " + OsuTourDbSchema.LocationQuestionTable.NAME+ "(" + OsuTourDbSchema.LocationQuestionTable.Cols.ID +" integer primary key autoincrement, " +
                OsuTourDbSchema.LocationQuestionTable.Cols.LOCATION_ID + " int not null, " +
                OsuTourDbSchema.LocationQuestionTable.Cols.QUESTION_ID + " int not null)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
        }

        return user;
    }

    public Location getLocation(Integer locatonId) {
        Location loc = new Location();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.LocationTable.Cols.ID,
                OsuTourDbSchema.LocationTable.Cols.NAME,
                OsuTourDbSchema.LocationTable.Cols.LATITUDE,
                OsuTourDbSchema.LocationTable.Cols.LONGITUDE,
                OsuTourDbSchema.LocationTable.Cols.HISTORY};
        Cursor cursor =
                db.query(OsuTourDbSchema.LocationTable.NAME, columns,
                        OsuTourDbSchema.LocationTable.Cols.ID + " =?",
                        new String[] {String.valueOf(locatonId)},
                        null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            loc.setId(locatonId);
            loc.setName(cursor.getString(0));
            loc.setLatitude(cursor.getDouble(1));
            loc.setLongitude(cursor.getDouble(2));
            loc.setDescription(cursor.getString(3));
        }
        return loc;
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

        }
        return locations;
    }

    public List<Location> getCompletedLocations(Integer userId) {
        List<Location> locationsCompleted = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.PlayerLocationCompletedTable.Cols.USER_ID,
                OsuTourDbSchema.PlayerLocationCompletedTable.Cols.LOCATION_ID,};
        Cursor cursor =
                db.query(OsuTourDbSchema.PlayerLocationCompletedTable.NAME,
                        columns,
                        OsuTourDbSchema.PlayerLocationCompletedTable.Cols.USER_ID + " = ?",
                        new String[] {String.valueOf(userId)},
                        null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                Location loc = getLocation(cursor.getInt(1));
                if(loc != null) {
                    locationsCompleted.add(loc);
                }
            }

        }
        return locationsCompleted;
    }

    public boolean locationIsUnlocked(Integer userId, Integer locationId) {
        boolean locationUnlocked = false;
        Log.d("USERID: ", userId.toString());
        Log.d("LOCATIONID: ", locationId.toString());
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

        }
        return locationUnlocked;
    }

}
