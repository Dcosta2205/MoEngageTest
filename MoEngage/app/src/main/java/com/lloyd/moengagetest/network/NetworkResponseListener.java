package com.lloyd.moengagetest.network;

public interface NetworkResponseListener {

    void onSuccess(String responseBody);

    void onFailure(int error);
}
