package com.lloyd.moengagetest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lloyd.moengagetest.models.Article;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;
    private static DBManager instance;

    public static synchronized DBManager getInstance(Context context) {
        if (instance == null) {
            instance = new DBManager(context);
            return instance;
        }
        return instance;
    }

    private DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * This method is used to insert data into the database.
     */
    public void insert(Article article) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, article.getTitle());
        contentValue.put(DatabaseHelper.DESCRIPTION, article.getDescription());
        contentValue.put(DatabaseHelper.AUTHOR, article.getAuthor());
        contentValue.put(DatabaseHelper.CONTENT, article.getContent());
        contentValue.put(DatabaseHelper.PUBLISHED_DATE, article.getPublishedAt());
        contentValue.put(DatabaseHelper.IMAGE_URL, article.getUrlToImage());
        contentValue.put(DatabaseHelper.UNIQUE_ID, article.getId());
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    /**
     * This method is used to fetch data from the database.
     */
    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ARTICLE_ID, DatabaseHelper.TITLE, DatabaseHelper.DESCRIPTION,
                DatabaseHelper.AUTHOR, DatabaseHelper.CONTENT, DatabaseHelper.PUBLISHED_DATE, DatabaseHelper.IMAGE_URL,
                DatabaseHelper.UNIQUE_ID};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}