package com.lloyd.moengagetest.repository;

import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.utils.Utils;

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
                Article article = articleList.get(i);
                ArticleItemModel articleItemModel = new ArticleItemModel();
                articleItemModel.setAuthor(article.getAuthor().equals("null") ? " " : article.getAuthor());
                articleItemModel.setTitle(article.getTitle().equals("null") ? " " : article.getTitle());
                articleItemModel.setDescription(article.getDescription().equals("null") ? " " : article.getDescription());
                articleItemModel.setContent(article.getContent().equals("null") ? " " : article.getContent());
                articleItemModel.setUrl(article.getUrl().equals("null") ? "" : article.getUrl());
                articleItemModel.setUrlToImage(article.getUrlToImage().equals("null") ? " " : article.getUrlToImage());
                articleItemModel.setPublishedAt(Utils.getFormattedDate(article.getPublishedAt()));
                articleItemModel.setTimeStamp(Utils.getTimeStampFromDate(article.getPublishedAt()));
                articleItemModelList.add(articleItemModel);
            }
        }
        return articleItemModelList;
    }
}
