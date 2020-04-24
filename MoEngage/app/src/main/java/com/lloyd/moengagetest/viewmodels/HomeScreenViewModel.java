package com.lloyd.moengagetest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lloyd.moengagetest.models.ArticleResponseModel;
import com.lloyd.moengagetest.network.OnResponseParsedListener;
import com.lloyd.moengagetest.repository.HomeScreenRepository;

public class HomeScreenViewModel extends ViewModel implements OnResponseParsedListener {
    private MutableLiveData<ArticleResponseModel> mutableLiveData = new MutableLiveData<>();
    public LiveData<ArticleResponseModel> liveData = mutableLiveData;
    private HomeScreenRepository homeScreenRepository = new HomeScreenRepository(this);

    public void getArticles() {
        homeScreenRepository.callGetArticlesApi();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        homeScreenRepository.cancelAsyncTask();
    }

    @Override
    public void onDataReceived(ArticleResponseModel articleResponseModel) {
        mutableLiveData.postValue(articleResponseModel);
    }
}
