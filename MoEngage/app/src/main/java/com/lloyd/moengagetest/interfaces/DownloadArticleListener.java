package com.lloyd.moengagetest.interfaces;

import com.lloyd.moengagetest.models.ArticleItemModel;

public interface DownloadArticleListener {

    void onDownloadArticleClicked(int position, ArticleItemModel data);
}
