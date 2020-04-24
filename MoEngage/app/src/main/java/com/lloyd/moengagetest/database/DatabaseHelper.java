package com.lloyd.moengagetest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Table Name
    public static final String TABLE_NAME = "ARTICLES_DATABASE";

    // Table columns
    public static final String _ARTICLE_ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String AUTHOR = "author";
    public static final String IMAGE_URL = "string";
    public static final String CONTENT = "content";
    public static final String PUBLISHED_DATE = "published_date";

    // Database Information
    static final String DB_NAME = "MOENGAGE_ARTICLES.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ARTICLE_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT , " + DESCRIPTION + " TEXT ,"
            + AUTHOR + " TEXT , " + IMAGE_URL + " TEXT ," + CONTENT + " TEXT , " +
            PUBLISHED_DATE + " TEXT) ;";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}