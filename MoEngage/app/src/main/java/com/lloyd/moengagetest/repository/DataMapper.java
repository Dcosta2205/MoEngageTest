package com.lloyd.moengagetest.repository;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;

import com.lloyd.moengagetest.R;
import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/*
This class is used to build data for model class.
 */
public class DataMapper {

    private Context mContext;

    public DataMapper(Context context) {
        mContext = context;
    }

    public List<ArticleItemModel> buildData(List<Article> articleList) {
        List<ArticleItemModel> articleItemModelList = new ArrayList<>();
        if (articleList != null) {
            for (int i = 0; i < articleList.size(); i++) {
                Article article = articleList.get(i);
                ArticleItemModel articleItemModel = new ArticleItemModel.ArticleItemModelBuilder()
                        .setTitle(getSpannedString("", article.getTitle().equals("null") || article.getAuthor() == (null) ? " " : article.getTitle(), ""))
                        .setAuthor(getSpannedString(mContext.getString(R.string.Author_text), article.getAuthor().equals("null") ? " " : article.getAuthor(), " :"))
                        .setId(article.getId())
                        .setDescription(getSpannedString(mContext.getString(R.string.description_text), article.getDescription().equals("null") || article.getAuthor() == (null) ? " " : article.getDescription(), " \n"))
                        .setContent(getSpannedString(mContext.getString(R.string.content_text), article.getContent().equals("null") || article.getAuthor() == (null) ? " " : article.getContent(), " \n"))
                        .setUrl(article.getUrl() == (null) ? "" : article.getUrl())
                        .setUrlToImage(article.getUrlToImage().equals("null") || article.getUrlToImage() == (null) ? " " : article.getUrlToImage())
                        .setPublishedAt(getSpannedString(mContext.getString(R.string.published_at_text), article.getPublishedAt().equals("null") || article.getAuthor() == (null) ? " " : Utils.getFormattedDate(article.getPublishedAt()), " :"))
                        .setTimeStamp(Utils.getTimeStampFromDate(article.getPublishedAt()))
                        .build();
                articleItemModelList.add(articleItemModel);
            }
        }
        return articleItemModelList;
    }

    private Spannable getSpannedString(String heading, String content, String delimiter) {
        SpannableStringBuilder str = new SpannableStringBuilder(heading + delimiter + content);
        str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, heading.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        if (content.equals(" ")) {
            SpannableStringBuilder str_new = new SpannableStringBuilder(" ");

            str_new.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            return str_new;
        }
        return str;
    }
}
