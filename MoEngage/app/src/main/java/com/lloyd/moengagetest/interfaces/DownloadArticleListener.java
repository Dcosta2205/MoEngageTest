package com.lloyd.moengagetest.interfaces;

import com.lloyd.moengagetest.models.ArticleItemModel;

/**
 * Interface to provide callback when user saves it.
 */
public interface DownloadArticleListener {

    void onDownloadArticleClicked(int position, ArticleItemModel data);
}
