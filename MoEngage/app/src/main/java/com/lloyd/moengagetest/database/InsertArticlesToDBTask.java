package com.lloyd.moengagetest.database;

import android.os.AsyncTask;

import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleItemModel;

/**
 * This class inserts all the articles into the database.
 */
public class InsertArticlesToDBTask extends AsyncTask<Article, Void, Void> {

    private DBManager dbManager;

    public InsertArticlesToDBTask(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    protected Void doInBackground(Article... articleItemModels) {
        insertIntoDatabase(articleItemModels);
        return null;
    }

    /**
     * This method performs the task of inserting the articles into the database.
     *
     * @param articles data to be inserted into the database.
     */
    private void insertIntoDatabase(Article[] articles) {
        dbManager.open();
        try {
            if (!isCancelled()) {
                for (Article articleItemModel : articles) {
                    dbManager.insert(articleItemModel);
                }
            }
        } finally {
            dbManager.close();
        }
    }
}
