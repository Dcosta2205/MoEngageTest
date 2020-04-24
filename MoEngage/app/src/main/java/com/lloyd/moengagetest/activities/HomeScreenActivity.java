package com.lloyd.moengagetest.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.lloyd.moengagetest.R;
import com.lloyd.moengagetest.adapter.HomeScreenAdapter;
import com.lloyd.moengagetest.viewmodels.HomeScreenViewModel;

public class HomeScreenActivity extends BaseActivity {
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private HomeScreenAdapter homeScreenAdapter;
    private HomeScreenViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(HomeScreenViewModel.class);
        getArticles();
        viewModel.liveData.observe(this, articleResponseModel -> {
            Log.d("Lloyd" , " on data changed ");
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

    @Override
    public void showProgressDialog(ProgressBar progressBar) {
        super.showProgressDialog(mProgressBar);
    }

    @Override
    public void dismissProgressDialog(ProgressBar progressBar) {
        super.dismissProgressDialog(mProgressBar);
    }
}
