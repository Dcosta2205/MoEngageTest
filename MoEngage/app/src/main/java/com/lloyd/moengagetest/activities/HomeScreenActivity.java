package com.lloyd.moengagetest.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lloyd.moengagetest.R;
import com.lloyd.moengagetest.adapter.HomeScreenAdapter;
import com.lloyd.moengagetest.interfaces.DownloadArticleListener;
import com.lloyd.moengagetest.interfaces.TitleClickedListener;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.viewmodels.HomeScreenViewModel;

public class HomeScreenActivity extends BaseActivity implements DownloadArticleListener, TitleClickedListener {
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private HomeScreenAdapter homeScreenAdapter;
    private HomeScreenViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
        setRecyclerAdapter();
        getArticles();
        viewModel.liveData.observe(this, articleList -> {
            dismissProgressDialog(mProgressBar);
            homeScreenAdapter.updateData(articleList);
        });
    }

    private void getArticles() {
        viewModel.getArticles();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initViewsAndListeners() {
        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.rv_recyclerview);
    }

    private void setRecyclerAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        homeScreenAdapter = new HomeScreenAdapter(viewModel.articleItemModelList, this, this);
        mRecyclerView.setAdapter(homeScreenAdapter);
    }

    @Override
    public void showProgressDialog(ProgressBar progressBar) {
        super.showProgressDialog(mProgressBar);
    }

    @Override
    public void dismissProgressDialog(ProgressBar progressBar) {
        super.dismissProgressDialog(mProgressBar);
    }

    /*
    This function is called when save article is clicked.
     */
    @Override
    public void onDownloadArticleClicked(int position, ArticleItemModel data) {
        Toast.makeText(this, "On item clicked " + "Position " + position, Toast.LENGTH_SHORT).show();
    }

    /*
    Called when title is clicked
     */
    @Override
    public void onTitleClicked(int position, ArticleItemModel model) {
        if(model!=null && model.getUrl()!=null){
            String url = model.getUrl();
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                startActivity(i);
            } catch (ActivityNotFoundException e) {
                // Try with the default browser
                i.setPackage(null);
                startActivity(i);
            }
        }
    }
}
