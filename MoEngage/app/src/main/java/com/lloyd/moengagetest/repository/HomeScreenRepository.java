package com.lloyd.moengagetest.repository;

import com.lloyd.moengagetest.interfaces.OnResponseParsedListener;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.network.FetchArticlesTask;
import com.lloyd.moengagetest.network.NetworkResponseListener;

import java.util.List;

public class HomeScreenRepository implements NetworkResponseListener {

    public HomeScreenRepository(OnResponseParsedListener listener) {
        this.listener = listener;
    }

    private FetchArticlesTask fetchArticlesTask;
    private OnResponseParsedListener listener;
    private JsonResponseParser responseParser = new JsonResponseParser();

    public void callGetArticlesApi() {
        fetchArticlesTask = new FetchArticlesTask(this);
        fetchArticlesTask.execute();
    }


    public void cancelAsyncTask() {
        if (fetchArticlesTask != null) {
            fetchArticlesTask.cancel(true);
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

    }
}
