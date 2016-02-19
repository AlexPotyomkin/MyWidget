package com.lihoy21gmail.mywidget;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DB {

    private static final String DB_NAME = "myDB";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "main_table";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_COUNT1 = "Count1";
    public static final String COLUMN_COUNT2 = "Count2";
    public static final String COLUMN_COUNT3 = "Count3";
    public static final String COLUMN_COUNT4 = "Count4";

    private final Context mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context ctx) {
        mCtx = ctx;
    }

    // открыть подключение
    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    // закрыть подключение
    public void close() {
        if (mDBHelper != null) mDBHelper.close();
    }

    // получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, "Date_ms");
    }

    public Cursor getSomeData(String selection, String selection_arg[]) {
        return mDB.query(DB_TABLE, null, selection, selection_arg, null, null, null);
    }


    // добавить запись в DB_TABLE
 /*   public long addRec(String txt, int img) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, txt);
        cv.put(COLUMN_COUNT1, img);
        cv.put(COLUMN_COUNT2, img);
        cv.put(COLUMN_COUNT3, img);
        cv.put(COLUMN_COUNT4, img);
        return mDB.insert(DB_TABLE, null, cv);
    }
*/
    // добавить запись в DB_TABLE
    public long addRec(ContentValues cv) {
        return mDB.insert(DB_TABLE, null, cv);
    }

    public int update(ContentValues cv, String selection, String selection_args[]){
        return  mDB.update(DB_TABLE, cv,selection,selection_args);
    }

    // удалить запись из DB_TABLE
    public void delRec(long id) {
        mDB.delete(DB_TABLE, COLUMN_ID + " = " + id, null);
    }
}