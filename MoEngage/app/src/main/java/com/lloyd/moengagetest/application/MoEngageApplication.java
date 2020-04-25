package com.lloyd.moengagetest.application;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.lloyd.moengagetest.database.DBManager;

public class MoEngageApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager.getInstance(this);
        /*
        Initialize firebase
         */
        FirebaseApp.initializeApp(this);
    }
}
