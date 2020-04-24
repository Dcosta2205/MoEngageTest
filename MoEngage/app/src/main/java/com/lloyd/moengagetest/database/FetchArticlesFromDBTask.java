package com.lloyd.moengagetest.database;

import android.database.Cursor;
import android.os.AsyncTask;

import com.lloyd.moengagetest.interfaces.DatabaseFetchListener;
import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.ArrayList;
import java.util.List;

public class FetchArticlesFromDBTask extends AsyncTask<Void, Void, List<ArticleItemModel>> {
    private DBManager dbManager;
    private DatabaseFetchListener listener;

    public FetchArticlesFromDBTask(DBManager dbManager, DatabaseFetchListener listener) {
        this.dbManager = dbManager;
        this.listener = listener;
    }

    @Override
    protected List<ArticleItemModel> doInBackground(Void... voids) {
        List<ArticleItemModel> articleItemModelList = fetchFromDatabase();
        return articleItemModelList;
    }

    private List<ArticleItemModel> fetchFromDatabase() {
        List<ArticleItemModel> articleItemModelList = new ArrayList<>();
        Cursor cursor = dbManager.fetch();
        if (cursor != null && cursor.getCount() >= 1 && cursor.moveToFirst()) {
            do {
                ArticleItemModel articleItemModel = new ArticleItemModel();
                articleItemModel.setTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE)));
                articleItemModel.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.DESCRIPTION)));
                articleItemModel.setUrlToImage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.IMAGE_URL)));
                articleItemModel.setPublishedAt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.PUBLISHED_DATE)));
                articleItemModel.setAuthor(cursor.getString(cursor.getColumnIndex(DatabaseHelper.AUTHOR)));
                articleItemModelList.add(articleItemModel);
            }
            while (cursor.moveToNext());
        }
        return articleItemModelList;
    }

    @Override
    protected void onPostExecute(List<ArticleItemModel> list) {
        super.onPostExecute(list);

        if (listener != null && list != null) {
            listener.onDataFetched(list);
        }
    }
}
