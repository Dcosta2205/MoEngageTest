package com.lloyd.moengagetest.repository;

import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleResponseModel;
import com.lloyd.moengagetest.models.Source;
import com.lloyd.moengagetest.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to parse the JSON response from the server to the required model class.
 */
public final class JsonResponseParser {

    private ArticleResponseModel articleResponseModel = new ArticleResponseModel();

    public List<Article> getParsedResponse(String responseBody) {
        List<Article> articleList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(responseBody);
            if (jsonObject.optString(Constants.STATUS).equalsIgnoreCase("ok")) {
                articleResponseModel.setStatus(jsonObject.optString(Constants.STATUS));
            }
            String articles = jsonObject.optString(Constants.ARTICLES_KEY);
            JSONArray jsonArray = new JSONArray(articles);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonArticle = jsonArray.getJSONObject(i);
                Source source = new Source();
                JSONObject sourceJson = jsonArticle.getJSONObject(Constants.SOURCE_KEY);
                source.setId(sourceJson.optString(Constants.ID));
                source.setName(sourceJson.optString(Constants.NAME));
                Article article = new Article();
                article.setAuthor(jsonArticle.optString(Constants.AUTHOR));
                article.setTitle(jsonArticle.optString(Constants.TITLE));
                article.setContent(jsonArticle.optString(Constants.CONTENT));
                article.setDescription(jsonArticle.optString(Constants.DESCRIPTION));
                article.setUrl(jsonArticle.optString(Constants.URL));
                article.setUrlToImage(jsonArticle.optString(Constants.URL_TO_IMAGE));
                article.setPublishedAt(jsonArticle.optString(Constants.PUBLISHED_AT));
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
