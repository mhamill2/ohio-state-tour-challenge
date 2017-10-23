package database.OsuTourDbSchema;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import osu.mobile_apps.ohiostatetourchallenge.User;

/*
 * This class is used to handle database creation and updating.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
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

}
