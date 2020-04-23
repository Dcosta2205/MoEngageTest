package com.lloyd.moengagetest.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        initViewsAndListeners();
    }

    protected abstract int getLayoutResourceId();

    protected abstract void initViewsAndListeners();

    public void showProgressDialog(ProgressBar progressBar) {
        if (!isFinishing() && progressBar != null && progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void dismissProgressDialog(ProgressBar progressBar) {
        if (!isFinishing() && progressBar != null && progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
