package com.lloyd.moengagetest.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lloyd.moengagetest.R;
import com.lloyd.moengagetest.interfaces.DownloadArticleListener;
import com.lloyd.moengagetest.interfaces.TitleClickedListener;
import com.lloyd.moengagetest.models.ArticleItemModel;

public class HomeScreenViewHolder extends RecyclerView.ViewHolder {

    ImageView mIvArticleImg;
    TextView mTvTitle;
    TextView mTvAuthorName;
    TextView mTvDescription;
    TextView mTvContent;
    TextView mTvUpdatedAt;
    ImageView mIvDownloadArticle;
    LinearLayout mTitleLayout;
    LinearLayout mAuthorLayout;
    LinearLayout mDescriptionLayout;
    LinearLayout mContentLayout;
    LinearLayout mUpdatedTimeLayout;
    View viewSeparator;
    private ArticleItemModel articleItemModel;
    private DownloadArticleListener downloadArticleListener;
    private TitleClickedListener titleClickedListener;

    public HomeScreenViewHolder(@NonNull View itemView, DownloadArticleListener downloadArticleListener, TitleClickedListener titleClickedListener) {
        super(itemView);
        this.downloadArticleListener = downloadArticleListener;
        this.titleClickedListener = titleClickedListener;
        initViews(itemView);

    }

    private void initViews(View itemView) {
        mIvArticleImg = itemView.findViewById(R.id.iv_article_image);
        mTvTitle = itemView.findViewById(R.id.tv_article_title);
        mTvAuthorName = itemView.findViewById(R.id.tv_author_name);
        mTvDescription = itemView.findViewById(R.id.tv_description);
        mTvContent = itemView.findViewById(R.id.tv_content);
        mTvUpdatedAt = itemView.findViewById(R.id.tv_updated_time);
        viewSeparator = itemView.findViewById(R.id.view_separator);
        mIvDownloadArticle = itemView.findViewById(R.id.iv_download_article);
        mIvDownloadArticle.setOnClickListener(view -> {
            if (downloadArticleListener != null) {
                downloadArticleListener.onDownloadArticleClicked(getAdapterPosition(), articleItemModel);
            }
        });

        mTvTitle.setOnClickListener(view -> {
            if (titleClickedListener != null) {
                titleClickedListener.onTitleClicked(getAdapterPosition(), articleItemModel);
            }
        });

    }

    void setArticleData(ArticleItemModel model) {
        this.articleItemModel = model;
    }
}
