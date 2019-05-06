package com.mygdx.game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ScreenTime.db";
    public static final String TABLE_NAME = "screentime_table";
    public static final String COL_ID = "ID";
    public static final String COL_DATE = "DATE_";
    public static final String COL_SCREENTIMEDURATION = "ScreenTimeDuration";


    public DatabaseHelper(Context context) {
        // creates the database
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_DATE + " TEXT, " +
                COL_SCREENTIMEDURATION + " INTEGER " +
                ")";
        db.execSQL(query); // to execute query
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insetData(TheTimer theTimer) {
        SQLiteDatabase db = this.getWritableDatabase(); // it will create the database and table

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DATE, theTimer.getDate());
        contentValues.put(COL_SCREENTIMEDURATION, theTimer.getDuration());
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;

        }


    }

    public long convertDataToString(String date, int choice) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "";
        if (choice == 1) {
            query = "select " + COL_SCREENTIMEDURATION + "  from " +
                    TABLE_NAME + " Where " +
                    COL_DATE + " = '" + date + "'";

        }
        if (choice == 2) {
            query = "select " + COL_SCREENTIMEDURATION + "  from " +
                    TABLE_NAME + " Where " +
                    COL_DATE + " like '" + date + "-%'";
        }
        if (choice == 3) {
            query = "select " + COL_SCREENTIMEDURATION + "  from " +
                    TABLE_NAME + " Where " +
                    COL_DATE + " like '" + date + "-%'";
        }


        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Long> list = new ArrayList<>();

        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            list.add(cursor.getLong(0));

        }
        cursor.close();
        db.close();
        long theSum = 0;
        for (int i = 0; i < list.size(); i++) {
            theSum += list.get(i);
        }


        return theSum;
    }


}
