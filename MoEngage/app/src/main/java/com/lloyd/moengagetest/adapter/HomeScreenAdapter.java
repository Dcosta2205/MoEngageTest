package com.lloyd.moengagetest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lloyd.moengagetest.R;
import com.lloyd.moengagetest.interfaces.DownloadArticleListener;
import com.lloyd.moengagetest.interfaces.TitleClickedListener;
import com.lloyd.moengagetest.models.ArticleItemModel;

import java.util.List;

public class HomeScreenAdapter extends RecyclerView.Adapter<HomeScreenViewHolder> {

    private DownloadArticleListener downloadArticleListener;
    private List<ArticleItemModel> articleList;
    private TitleClickedListener titleClickedListener;

    public HomeScreenAdapter(List<ArticleItemModel> articleList, DownloadArticleListener downloadArticleListener,
                             TitleClickedListener titleClickedListener) {
        this.articleList = articleList;
        this.downloadArticleListener = downloadArticleListener;
        this.titleClickedListener = titleClickedListener;
    }

    @NonNull
    @Override
    public HomeScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_articles_recycler_item, parent, false);
        return new HomeScreenViewHolder(view, downloadArticleListener, titleClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeScreenViewHolder holder, int position) {
        ArticleItemModel articleItemModel = articleList.get(position);
        holder.mTvTitle.setText(articleItemModel.getTitle());
        holder.mTvDescription.setText(articleItemModel.getDescription());
        holder.mTvContent.setText(articleItemModel.getContent());
        holder.mTvUpdatedAt.setText(articleItemModel.getPublishedAt());
        holder.mTvAuthorName.setText(articleItemModel.getAuthor());
        Glide.with(holder.mIvArticleImg).load(articleItemModel.getUrlToImage()).into(holder.mIvArticleImg);
        holder.setArticleData(articleItemModel);
    }

    /**
     * This method is used to notify the adapter that the recycler view data has been changed.
     * This method is called when liveData observable is called.
     */
    public void updateData(List<ArticleItemModel> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
