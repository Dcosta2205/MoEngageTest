package com.lloyd.moengagetest.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lloyd.moengagetest.interfaces.OnResponseParsedListener;
import com.lloyd.moengagetest.models.Article;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.repository.DataMapper;
import com.lloyd.moengagetest.repository.HomeScreenRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeScreenViewModel extends ViewModel implements OnResponseParsedListener {

    private Context context;
    private MutableLiveData<List<ArticleItemModel>> mutableLiveData = new MutableLiveData<>();
    public LiveData<List<ArticleItemModel>> liveData = mutableLiveData;
    private List<Article> articleList;
    private HomeScreenRepository homeScreenRepository;
    public List<ArticleItemModel> articleItemModelList = new ArrayList<>();

    public HomeScreenViewModel(Context context) {
        this.context = context;
        homeScreenRepository = new HomeScreenRepository(this, context);
    }

    private Comparator<ArticleItemModel> ascendingComparator = (o1, o2) -> {
        if (o1.getTimeStamp() > o2.getTimeStamp()) {
            return 1;
        } else if (o1.getTimeStamp() < o2.getTimeStamp()) {
            return -1;
        }
        return 0;
    };


    private Comparator<ArticleItemModel> descendingComparator = (o1, o2) -> {
        if (o1.getTimeStamp() < o2.getTimeStamp()) {
            return 1;
        } else if (o1.getTimeStamp() > o2.getTimeStamp()) {
            return -1;
        }
        return 0;
    };

    public void sortAscendingBasedOnPublishedDate() {
        Collections.sort(articleItemModelList, ascendingComparator);
        mutableLiveData.setValue(articleItemModelList);
    }

    public void sortDescendingBasedOnPublishedDate() {
        Collections.sort(articleItemModelList, descendingComparator);
        mutableLiveData.setValue(articleItemModelList);
    }

    public void getArticles() {
        homeScreenRepository.callGetArticlesApi();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        homeScreenRepository.cancelAsyncTask();
    }


    @Override
    public void onDataReceived(List<ArticleItemModel> articleItemModelList, List<Article> articleList) {
        this.articleItemModelList = articleItemModelList;
        this.articleList = articleList;
        mutableLiveData.setValue(articleItemModelList);
    }

    @Override
    public void onDataFetchedFromDB(List<Article> articleList) {
        this.articleList = articleList;
        this.articleItemModelList = new DataMapper(context).buildData(articleList);
        mutableLiveData.setValue(articleItemModelList);
    }

    @Override
    public void onError(int responseCode) {
        articleItemModelList = null;
            /*
            Here live data post value is used because on error is called directly from background thread i.e from doInBackground method
             */
        mutableLiveData.postValue(articleItemModelList);
    }

    public void insertArticlesIntoDatabase(ArticleItemModel model) {
        /*
         we need to insert the entire response fetched from server.
         */
        Article foundArticle = null;
        for (Article article : articleList) {
            if (model.getId().equals(article.getId())) {
                foundArticle = article;
                break;
            }
        }
        if (foundArticle != null) {
            homeScreenRepository.insertArticlesIntoDatabase(foundArticle);
        }
    }

    public void getArticlesFromDB() {
        homeScreenRepository.getArticlesFromDB();
    }
}
