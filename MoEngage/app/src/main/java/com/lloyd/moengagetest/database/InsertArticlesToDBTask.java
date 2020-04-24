package com.lloyd.moengagetest.database;

import android.os.AsyncTask;

import com.lloyd.moengagetest.models.ArticleItemModel;

public class InsertArticlesToDBTask extends AsyncTask<ArticleItemModel, Void, Void> {

    private DBManager dbManager;

    public InsertArticlesToDBTask(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    protected Void doInBackground(ArticleItemModel... articleItemModels) {
        insertIntoDatabase(articleItemModels);
        return null;
    }

    private void insertIntoDatabase(ArticleItemModel[] articleItemModels) {
        if (!isCancelled()) {
            for (ArticleItemModel articleItemModel : articleItemModels) {
                dbManager.insert(articleItemModel);
            }
        }
    }
}
