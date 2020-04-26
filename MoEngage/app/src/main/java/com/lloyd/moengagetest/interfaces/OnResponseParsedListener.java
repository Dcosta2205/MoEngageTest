package com.lloyd.moengagetest.interfaces;

import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.List;

/**
 * This method is used to provide callback when the json data has been parsed.
 */
public interface OnResponseParsedListener {

    void onDataReceived(List<ArticleItemModel> articleItemModelList , List<Article> articleList);

    void onDataFetchedFromDB(List<Article> articleList);

    void onError(int responseCode);

}
