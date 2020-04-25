package com.lloyd.moengagetest.database;

import android.os.AsyncTask;

import com.lloyd.moengagetest.models.ArticleItemModel;

/**
 * This class inserts all the articles into the database.
 */
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

    /**
     * This method performs the task of inserting the articles into the database.
     *
     * @param articleItemModels data to be inserted into the database.
     */
    private void insertIntoDatabase(ArticleItemModel[] articleItemModels) {
        dbManager.open();
        try {
            if (!isCancelled()) {
                for (ArticleItemModel articleItemModel : articleItemModels) {
                    dbManager.insert(articleItemModel);
                }
            }
        } finally {
            dbManager.close();
        }
    }
}
