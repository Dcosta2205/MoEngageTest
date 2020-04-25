package com.lloyd.moengagetest.interfaces;

import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.List;

/**
 * This method is used to provide callback when the json data has been parsed.
 */
public interface OnResponseParsedListener {

    void onDataReceived(List<ArticleItemModel> articleList);

    void onDataFetchedFromDB(List<ArticleItemModel> articleItemModelList);

    void onError(int responseCode);

}
