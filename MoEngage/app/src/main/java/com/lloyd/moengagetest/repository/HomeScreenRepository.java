package com.lloyd.moengagetest.repository;

import android.content.Context;

import com.lloyd.moengagetest.database.DBManager;
import com.lloyd.moengagetest.database.FetchArticlesFromDBTask;
import com.lloyd.moengagetest.database.InsertArticlesToDBTask;
import com.lloyd.moengagetest.interfaces.DatabaseFetchListener;
import com.lloyd.moengagetest.interfaces.OnResponseParsedListener;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.network.FetchArticlesTask;
import com.lloyd.moengagetest.network.NetworkResponseListener;

import java.util.List;

public class HomeScreenRepository implements NetworkResponseListener, DatabaseFetchListener {

    public HomeScreenRepository(OnResponseParsedListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    private FetchArticlesTask fetchArticlesTask;
    private InsertArticlesToDBTask insertArticlesToDBTask;
    private FetchArticlesFromDBTask fetchArticlesFromDBTask;
    private OnResponseParsedListener listener;
    private Context context;

    public void callGetArticlesApi() {
        fetchArticlesTask = new FetchArticlesTask(this);
        fetchArticlesTask.execute();
    }

    public void insertArticlesIntoDatabase(ArticleItemModel model) {
        insertArticlesToDBTask = new InsertArticlesToDBTask(DBManager.getInstance(context));
        insertArticlesToDBTask.execute(model);
    }

    public void getArticlesFromDB() {
        fetchArticlesFromDBTask = new FetchArticlesFromDBTask(DBManager.getInstance(context), this);
        fetchArticlesFromDBTask.execute();
    }

    public void cancelAsyncTask() {
        if (fetchArticlesTask != null) {
            fetchArticlesTask.cancel(true);
        }
        if (insertArticlesToDBTask != null) {
            insertArticlesToDBTask.cancel(true);
        }
    }

    @Override
    public void onSuccess(List<ArticleItemModel> responseBody) {
        if (listener != null) {
            listener.onDataReceived(responseBody);
        }
    }

    @Override
    public void onFailure(int error) {
        if (listener != null) {
            listener.onError(error);
        }
    }

    @Override
    public void onDataFetched(List<ArticleItemModel> list) {
        if (listener != null) {
            listener.onDataFetchedFromDB(list);
        }
    }
}
