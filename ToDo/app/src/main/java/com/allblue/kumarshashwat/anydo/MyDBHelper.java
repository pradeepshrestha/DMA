package com.allblue.kumarshashwat.anydo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="LIST_DATA";
    public static final int DATABASE_VERSION=1;
    public static final String COLUMN_TASK="task";
    public static final String KEY_ID="id";
    public MyDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists personal(task text,id integer primary key)");
        db.execSQL("create table if not exists work(task text,id integer primary key)");
        db.execSQL("create table if not exists movies(task text,id integer primary key)");
        db.execSQL("create table if not exists places(task text,id integer primary key)");
        db.execSQL("create table if not exists grocery(task text,id integer primary key)");
        db.execSQL("create table if not exists school(task text,id integer primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS personal");
        db.execSQL("DROP TABLE IF EXISTS grocery");
        db.execSQL("DROP TABLE IF EXISTS work");
        db.execSQL("DROP TABLE IF EXISTS movies");
        db.execSQL("DROP TABLE IF EXISTS school");
        db.execSQL("DROP TABLE IF EXISTS places");
        onCreate(db);
    }

    public void myinsert(ContentValues cv,String k){
        getWritableDatabase().insert(k,null,cv);
    }

    public Cursor myselect(String k){
        Cursor cursor=getReadableDatabase().rawQuery("Select * from "+k,null);
        return cursor;
    }
}
//PASS THE STRING FROM HOME TO LIST_TO_DO VIA INTENT.
//SEND THE EXTRACTED STRING TO LIST FRAGMENT.
//PASS THE STRING AS ARGUMENT IN MYINSERT AND MYSELECT.
