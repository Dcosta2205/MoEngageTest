package com.lloyd.moengagetest.interfaces;

import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.List;

public interface OnResponseParsedListener {

    void onDataReceived(List<ArticleItemModel> articleList);
}
