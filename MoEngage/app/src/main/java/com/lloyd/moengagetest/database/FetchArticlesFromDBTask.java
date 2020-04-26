package com.lloyd.moengagetest.database;

import android.database.Cursor;
import android.os.AsyncTask;

import com.lloyd.moengagetest.interfaces.DatabaseFetchListener;
import com.lloyd.moengagetest.models.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to fetch all the articles from database.
 */
public class FetchArticlesFromDBTask extends AsyncTask<Void, Void, List<Article>> {
    private DBManager dbManager;
    private DatabaseFetchListener listener;

    public FetchArticlesFromDBTask(DBManager dbManager, DatabaseFetchListener listener) {
        this.dbManager = dbManager;
        this.listener = listener;
    }

    @Override
    protected List<Article> doInBackground(Void... voids) {
        List<Article> articleItemModelList = fetchFromDatabase();
        return articleItemModelList;
    }

    /**
     * This method fetch all the articles from the database and returns the List of articles.
     */
    private List<Article> fetchFromDatabase() {
        List<Article> articleItemModelList = new ArrayList<>();
        dbManager.open();
        try {
            Cursor cursor = dbManager.fetch();
            if (cursor != null && cursor.getCount() >= 1 && cursor.moveToFirst()) {
                do {
                    Article article = new Article();
                    article.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE)));
                    article.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.DESCRIPTION)));
                    article.setUrlToImage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.IMAGE_URL)));
                    article.setPublishedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PUBLISHED_DATE)));
                    article.setAuthor(cursor.getString(cursor.getColumnIndex(DatabaseHelper.AUTHOR)));
                    article.setContent(cursor.getString(cursor.getColumnIndex(DatabaseHelper.CONTENT)));
                    article.setId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.UNIQUE_ID)));
                    articleItemModelList.add(article);
                }
                while (cursor.moveToNext());
            }

        } finally {
            dbManager.close();
        }
        return articleItemModelList;
    }

    @Override
    protected void onPostExecute(List<Article> list) {
        super.onPostExecute(list);

        /*
         * Passing data to the repository once the result is obtained on main thread.
         */
        if (listener != null && list != null) {
            listener.onDataFetched(list);
        }
    }
}
