package com.lloyd.moengagetest.network;

import com.lloyd.moengagetest.models.ArticleResponseModel;

public interface OnResponseParsedListener {

    void onDataReceived(ArticleResponseModel articleResponseModel);
}
