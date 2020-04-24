package com.lloyd.moengagetest.interfaces;

import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.List;

public interface DatabaseFetchListener {
    void onDataFetched(List<ArticleItemModel> list);
}
