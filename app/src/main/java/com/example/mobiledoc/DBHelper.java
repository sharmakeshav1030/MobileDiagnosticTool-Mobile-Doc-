package com.example.mobiledoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/*******
 * <p> Title: DBHelper Class</p>
 *
 * <p> Description: It is a database helper class which provides us the methods to access database.</p>
 *
 * @author Keshav Sharma
 *
 * @version 1.00	2019-11-23
 *
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ReportDatabase.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table test_report " +
                        "(test_id int, test_name text,test_date_time text, test_status text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS test_report");
        onCreate(db);
    }


    // This method helps to insert data into the table.
    public boolean insert(String test_name, String test_date_time, String test_status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("test_name", test_name);
        contentValues.put("test_date_time", test_date_time);
        contentValues.put("test_status", test_status);
        db.insert("test_report", null, contentValues);
        return true;
    }

    // This method helps to fetch the data int the table.
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from test_report",null);
        return res;
    }

    // This method creates a new table by deleting the existing one.
    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS test_report");
        onCreate(db);
    }
}