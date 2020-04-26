package com.lloyd.moengagetest.activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lloyd.moengagetest.R;
import com.lloyd.moengagetest.adapter.HomeScreenAdapter;
import com.lloyd.moengagetest.interfaces.DownloadArticleListener;
import com.lloyd.moengagetest.interfaces.TitleClickedListener;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.utils.Utils;
import com.lloyd.moengagetest.viewmodels.HomeScreenViewModel;
import com.lloyd.moengagetest.viewmodels.HomeViewModelFactory;

import java.util.List;

public class HomeScreenActivity extends BaseActivity implements DownloadArticleListener, TitleClickedListener {
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private HomeScreenAdapter homeScreenAdapter;
    private HomeScreenViewModel viewModel;
    private Toolbar mToolbar;
    private LinearLayout mErrorLayout;
    private TextView mTvErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new HomeViewModelFactory(getApplicationContext()).create(HomeScreenViewModel.class);
        setRecyclerAdapter();
        /*
        Checks for internet connection , if available data is fetched from Api else its fetched from database.
         */
        if (Utils.isNetworkAvailable(this)) {
            getArticles();
        } else {
            viewModel.getArticlesFromDB();
        }
        viewModel.liveData.observe(this, articleList -> {
            dismissProgressDialog(mProgressBar);
            handleResponseUI(articleList);
        });
    }

    private void handleResponseUI(List<ArticleItemModel> articleList) {
        /*
        articleList is null if the Api throws an error. or else it can never be null
         */
        if (articleList == null) {
            showErrorLayout(true);
            return;
        }

        /*
        Notify recyclerview adapter about the change in articleList
         */
        homeScreenAdapter.updateData(articleList);
        mRecyclerView.scrollToPosition(0);
        /*
        articleList size is 0 , then there are no articles
         */
        if (articleList.size() == 0) {
            showErrorLayout(false);
        } else {
            showRecyclerViewItems();
        }
    }

    private void showRecyclerViewItems() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mErrorLayout.setVisibility(View.GONE);
    }

    /**
     * This method makes an call to viewModel to get the articles from the server.
     */
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
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mErrorLayout = findViewById(R.id.ll_error);
        mTvErrorMessage = findViewById(R.id.tv_something_went_wrong);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuCompat.setGroupDividerEnabled(menu, true);
        return true;
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
        Toast.makeText(this, "Article saved to database", Toast.LENGTH_SHORT).show();
        viewModel.insertArticlesIntoDatabase(data);
    }

    /*
    Called when title is clicked
     */
    @Override
    public void onTitleClicked(int position, ArticleItemModel model) {
        if (model != null && model.getUrl() != null) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_new_to_old:
                viewModel.sortAscendingBasedOnPublishedDate();
                return true;
            case R.id.menu_old_to_new:
                viewModel.sortDescendingBasedOnPublishedDate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method is used to show the error screen if API throws an error and if the database is empty.
     *
     * @param isError
     */
    public void showErrorLayout(boolean isError) {
        mRecyclerView.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
        if (isError) {
            mTvErrorMessage.setText(R.string.something_went_wrong_text);
        } else {
            mTvErrorMessage.setText(R.string.no_aticles_found_text);
        }
    }
}