package com.lloyd.moengagetest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lloyd.moengagetest.interfaces.OnResponseParsedListener;
import com.lloyd.moengagetest.models.ArticleItemModel;
import com.lloyd.moengagetest.repository.HomeScreenRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenViewModel extends ViewModel implements OnResponseParsedListener {
    private MutableLiveData<List<ArticleItemModel>> mutableLiveData = new MutableLiveData<>();
    public LiveData<List<ArticleItemModel>> liveData = mutableLiveData;
    private HomeScreenRepository homeScreenRepository = new HomeScreenRepository(this);
    public List<ArticleItemModel> articleItemModelList = new ArrayList<>();

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
}
