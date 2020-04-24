package com.lloyd.moengagetest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.lloyd.moengagetest.models.ArticleItemModel;

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

    public void insert(ArticleItemModel articleItemModel) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, articleItemModel.getTitle());
        contentValue.put(DatabaseHelper.DESCRIPTION, articleItemModel.getDescription());
        contentValue.put(DatabaseHelper.AUTHOR, articleItemModel.getAuthor());
        contentValue.put(DatabaseHelper.CONTENT, articleItemModel.getContent());
        contentValue.put(DatabaseHelper.PUBLISHED_DATE, articleItemModel.getPublishedAt());
        contentValue.put(DatabaseHelper.IMAGE_URL, articleItemModel.getUrlToImage());
        Log.d("Lloyd", "content values "+contentValue);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ARTICLE_ID, DatabaseHelper.TITLE, DatabaseHelper.DESCRIPTION,
                DatabaseHelper.AUTHOR, DatabaseHelper.CONTENT, DatabaseHelper.PUBLISHED_DATE, DatabaseHelper.IMAGE_URL};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ARTICLE_ID + "=" + _id, null);
    }

}