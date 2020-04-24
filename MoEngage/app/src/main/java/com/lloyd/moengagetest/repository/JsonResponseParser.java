package com.lloyd.moengagetest.repository;

import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleResponseModel;
import com.lloyd.moengagetest.models.Source;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public final class JsonResponseParser {

    private ArticleResponseModel articleResponseModel = new ArticleResponseModel();

    public List<Article> getParsedResponse(String responseBody) {
        List<Article> articleList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            if (jsonObject.optString("status").equalsIgnoreCase("ok")) {
                articleResponseModel.setStatus(jsonObject.optString("status"));
            }
            String articles = jsonObject.optString("articles");
            JSONArray jsonArray = new JSONArray(articles);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonArticle = jsonArray.getJSONObject(i);
                Source source = new Source();
                JSONObject sourceJson = jsonArticle.getJSONObject("source");
                source.setId(sourceJson.optString("id"));
                source.setName(sourceJson.optString("name"));
                Article article = new Article();
                article.setAuthor(jsonArticle.optString("author"));
                article.setTitle(jsonArticle.optString("title"));
                article.setContent(jsonArticle.optString("content"));
                article.setDescription(jsonArticle.optString("description"));
                article.setUrl(jsonArticle.optString("url"));
                article.setUrlToImage(jsonArticle.optString("urlToImage"));
                article.setPublishedAt(jsonArticle.optString("publishedAt"));
                article.setSource(source);
                articleList.add(article);
            }
            articleResponseModel.setArticles(articleList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articleList;
    }
}
