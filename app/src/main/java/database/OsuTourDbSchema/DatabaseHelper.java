package database.OsuTourDbSchema;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL("create table " + OsuTourDbSchema.UserTable.NAME + "(" + " _id integer primary key autoincrement, " +
                OsuTourDbSchema.UserTable.Cols.USER_NAME + " varchar(20) not null, " +
                OsuTourDbSchema.UserTable.Cols.PASSWORD + " varchar(20) not null)"
        );
        db.execSQL("create table " + OsuTourDbSchema.LocationTable.NAME+ "(" + " _id integer primary key autoincrement, " +
                OsuTourDbSchema.LocationTable.Cols.NAME + " varchar(50) not null, " +
                OsuTourDbSchema.LocationTable.Cols.LATITUDE + " decimal(9, 6) not null, " +
                OsuTourDbSchema.LocationTable.Cols.LONGITUDE + " decimal(9, 6) not null, " +
                OsuTourDbSchema.LocationTable.Cols.HISTORY + " text)"
        );
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(1, 'Ohio Union', 39.997687, -83.008590, 'The Ohio Union serves as an activity center for students of The Ohio State University. When the Union was established in 1910, it was the first student union at a public university. The Ohio Union provides facilities for student activities, organizations/events, and campus / community interaction. Many student services and programs are housed in the union, along with dining and recreational facilities. It also serves as the home base for the D-Tix program, which provides discounted tickets to students.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(2, 'Recreational and Physical Activity Center (RPAC)', 39.999410, -83.018269, 'The RPAC is the main recreational facility on campus, and offers over half a million square feet of recreation, aquatic, fitness, and meeting space. The RPAC features two on-campus dining locations, a 50-meter competitive pool, 12 wood courts, 10 racquetball courts, 4 squash courts, a four-lane jogging/walking track, five multipurpose rooms, and approximately 27,500 square feet of fitness space with state-of-the-art equipment.')");
        db.execSQL("INSERT INTO "+ OsuTourDbSchema.LocationTable.NAME +" VALUES(3, 'Thompson Library', 39.999221, -83.014846, 'The William Oxley Thompson Memorial Library (commonly referred to as the Thompson Library) is the main library at Ohio State University\''s Columbus campus. It is the university\''s largest library and houses its main stacks, special collections, rare books and manuscripts, university archives, and many departmental subject libraries. The library was originally built in 1912, and was renovated in 1951, 1977, and 2009. It is named in honor of the university\''s fifth president, William Oxley Thompson.')");
        db.execSQL("create table " + OsuTourDbSchema.QuestionTable.NAME+ "(" + " _id integer primary key autoincrement, " +
                OsuTourDbSchema.QuestionTable.Cols.LOCATION_ID + " int not null, " +
                OsuTourDbSchema.QuestionTable.Cols.TEXT + " text not null)"
        );

        db.execSQL("create table " + OsuTourDbSchema.AnswerTable.NAME+ "(" + " _id integer primary key autoincrement, " +
                OsuTourDbSchema.AnswerTable.Cols.TEXT + " text not null)"
        );
        db.execSQL("create table " + OsuTourDbSchema.PlayerLocationCompletedTable.NAME + "(" + " _id integer primary key autoincrement, " +
                OsuTourDbSchema.PlayerLocationCompletedTable.Cols.USER_ID + " int not null, " +
                OsuTourDbSchema.PlayerLocationCompletedTable.Cols.LOCATION_ID + " int not null)"
        );
        db.execSQL("create table " + OsuTourDbSchema.LocationQuestionTable.NAME+ "(" + " _id integer primary key autoincrement, " +
                OsuTourDbSchema.LocationQuestionTable.Cols.LOCATION_ID + " int not null, " +
                OsuTourDbSchema.LocationQuestionTable.Cols.QUESTION_ID + " int not null)"
        );
        db.execSQL("create table " + OsuTourDbSchema.QuestionAnswerTable.NAME+ "(" + " _id integer primary key autoincrement, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.QUESTION_ID + " int not null, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.ANSWER_ID + " int not null, " +
                OsuTourDbSchema.QuestionAnswerTable.Cols.IS_CORRECT + " int not null)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public User getUser(String userName) {
        User user = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.UserTable.Cols.USER_NAME, OsuTourDbSchema.UserTable.Cols.PASSWORD};
        Cursor cursor =
                db.query(OsuTourDbSchema.UserTable.NAME,
                        columns,
                        OsuTourDbSchema.UserTable.Cols.USER_NAME + " = ?",
                        new String[] {String.valueOf(userName)},
                        null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            user.setUserName(cursor.getString(0));
            user.setPassword(cursor.getString(1));
        }

        return user;
    }

    public Location getLocation(String name) {
        Location loc = new Location();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.LocationTable.Cols.NAME,
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
            loc.setName(cursor.getString(0));
            loc.setLatitude(cursor.getDouble(1));
            loc.setLongitude(cursor.getDouble(2));
            loc.setDescription(cursor.getString(3));
        }
        return loc;
    }

    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {OsuTourDbSchema.LocationTable.Cols.NAME,
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
                loc.setName(cursor.getString(0));
                loc.setDescription(cursor.getString(1));
                loc.setLatitude(cursor.getDouble(2));
                loc.setLongitude(cursor.getDouble(3));
                locations.add(loc);
            }

        }
        return locations;
    }

}
