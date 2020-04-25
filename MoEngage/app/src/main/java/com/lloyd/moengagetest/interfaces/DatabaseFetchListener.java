package com.lloyd.moengagetest.interfaces;

import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.List;

/**
 * Interface used to provide callback when the data is fetched from database.
 */
public interface DatabaseFetchListener {
    void onDataFetched(List<ArticleItemModel> list);
}
