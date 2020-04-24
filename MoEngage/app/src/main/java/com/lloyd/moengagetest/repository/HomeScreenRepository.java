package com.lloyd.moengagetest.repository;

import android.util.Log;

import com.lloyd.moengagetest.models.ArticleResponseModel;
import com.lloyd.moengagetest.network.FetchArticlesTask;
import com.lloyd.moengagetest.network.NetworkResponseListener;
import com.lloyd.moengagetest.network.OnResponseParsedListener;

public class HomeScreenRepository implements NetworkResponseListener {

    public HomeScreenRepository(OnResponseParsedListener listener) {
        this.listener = listener;
    }

    private ArticleResponseModel articleResponseModel;
    private FetchArticlesTask fetchArticlesTask;
    private OnResponseParsedListener listener;
    private JsonResponseParser responseParser = new JsonResponseParser();

    public void callGetArticlesApi() {
        fetchArticlesTask = new FetchArticlesTask(this);
        fetchArticlesTask.execute();
    }

    @Override
    public void onSuccess(String responseBody) {
        Log.d("Lloyd ", "thread name " + Thread.currentThread().getName());
        articleResponseModel = responseParser.getParsedResponse(responseBody);
        if (listener != null) {
            listener.onDataReceived(articleResponseModel);
        }
    }

    @Override
    public void onFailure(int error) {

    }

    public void cancelAsyncTask() {
        if (fetchArticlesTask != null) {
            fetchArticlesTask.cancel(true);
        }
    }
}
