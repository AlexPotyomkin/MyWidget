package com.lihoy21gmail.mywidget;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {
    final String TAG = "myLog";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        //Log.d(TAG, "DBHelper: constructor");
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table main_table ("
                + " _id integer primary key autoincrement,"
                + "Date DATE,"
                + "Date_ms integer,"
                + "Count1 integer,"
                + "Count2 integer,"
                + "Count3 integer,"
                + "Count4 integer" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}