package com.lloyd.moengagetest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lloyd.moengagetest.models.ArticleResponseModel;
import com.lloyd.moengagetest.repository.HomeScreenRepository;

public class HomeScreenViewModel extends ViewModel {
    private MutableLiveData<ArticleResponseModel> mutableLiveData = new MutableLiveData<>();
    public LiveData<ArticleResponseModel> liveData = mutableLiveData;
    private HomeScreenRepository homeScreenRepository = new HomeScreenRepository();
    private ArticleResponseModel articleResponseModel;

    public void getArticles() {
        articleResponseModel = homeScreenRepository.getArticles();
        mutableLiveData.setValue(articleResponseModel);
    }
}
