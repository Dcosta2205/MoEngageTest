package com.lloyd.moengagetest.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lloyd.moengagetest.interfaces.OnResponseParsedListener;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.repository.HomeScreenRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeScreenViewModel extends ViewModel implements OnResponseParsedListener {

    private Context context;
    private MutableLiveData<List<ArticleItemModel>> mutableLiveData = new MutableLiveData<>();
    public LiveData<List<ArticleItemModel>> liveData = mutableLiveData;
    private HomeScreenRepository homeScreenRepository = new HomeScreenRepository(this, context);
    public List<ArticleItemModel> articleItemModelList = new ArrayList<>();

    public HomeScreenViewModel(Context context) {
        this.context = context;
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
    public void onDataReceived(List<ArticleItemModel> articleList) {
        this.articleItemModelList = articleList;
        mutableLiveData.setValue(articleItemModelList);
    }

    @Override
    public void onDataFetchedFromDB(List<ArticleItemModel> articleItemModelList) {
        this.articleItemModelList = articleItemModelList;
        mutableLiveData.setValue(articleItemModelList);
    }

    public void insertArticlesIntoDatabase(ArticleItemModel model) {
        homeScreenRepository.insertArticlesIntoDatabase(model);
    }

    public void getArticlesFromDB() {
        homeScreenRepository.getArticlesFromDB();
    }
}
