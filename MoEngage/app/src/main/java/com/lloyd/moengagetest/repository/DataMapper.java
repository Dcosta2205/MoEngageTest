package com.lloyd.moengagetest.repository;

import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.ArrayList;
import java.util.List;

/*
This class is used to build data for model class.
 */
public class DataMapper {

    public List<ArticleItemModel> buildData(List<Article> articleList) {
        List<ArticleItemModel> articleItemModelList = new ArrayList<>();
        if (articleList != null) {
            for (int i = 0; i < articleList.size(); i++) {
                ArticleItemModel articleItemModel = new ArticleItemModel();
                articleItemModel.setAuthor(articleList.get(i).getAuthor());
                articleItemModel.setTitle(articleList.get(i).getTitle());
                articleItemModel.setDescription(articleList.get(i).getDescription());
                articleItemModel.setContent(articleList.get(i).getContent());
                articleItemModel.setUrl(articleList.get(i).getUrl());
                articleItemModel.setUrlToImage(articleList.get(i).getUrlToImage());
                articleItemModel.setPublishedAt(articleList.get(i).getPublishedAt());
                if (i == articleList.size() - 1) {
                    articleItemModel.setLast(8);
                } else {
                    articleItemModel.setLast(1);
                }
                articleItemModelList.add(articleItemModel);
            }
        }
        return articleItemModelList;
    }
}
