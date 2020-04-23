package com.lloyd.moengagetest.repository;

import com.lloyd.moengagetest.models.ArticleResponseModel;
import com.lloyd.moengagetest.network.FetchArticlesTask;

public class HomeScreenRepository {

    private ArticleResponseModel articleResponseModel = new ArticleResponseModel();

    public ArticleResponseModel getArticles() {
        FetchArticlesTask  fetchArticlesTask= new FetchArticlesTask();
        fetchArticlesTask.execute();
        return null;
    }
}
