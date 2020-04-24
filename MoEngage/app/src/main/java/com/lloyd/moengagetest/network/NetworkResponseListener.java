package com.lloyd.moengagetest.network;

import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.List;

public interface NetworkResponseListener {

    void onSuccess(List<ArticleItemModel> responseBody);

    void onFailure(int error);
}
