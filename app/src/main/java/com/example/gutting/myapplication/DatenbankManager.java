package com.example.gutting.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by plabisch on 01.07.2015.
 */
public class DatenbankManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "zeiten.db";
    private static final int DB_VERSION = 1;
    private static final String td_CREATE =
            "CREATE TABLE td (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL" + "hours INTEGER" + "minutes INTEGER" + "seconds INTEGER" +"clicks INTEGER" +")";

    private static final String td_DROP =
            "DROP TABLE IF EXSITS td";

    public DatenbankManager (Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate (SQLiteDatabase db){
        db.execSQL(td_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(td_DROP);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    SQLiteDatabase db = getWritableDatabase();

    public void write (String name, int hours, int minutes, int seconds, int clicks){

        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("hours", hours);
        values.put("minutes", minutes);
        values.put("seconds", seconds);
        values.put("clicks", clicks);
        // Insert the new row, returning the primary key value of the new row

        long test;
        test = db.insert("td", "null", values);

        Log.i("DatenbankTest", Long.toString(test));
    }
}
