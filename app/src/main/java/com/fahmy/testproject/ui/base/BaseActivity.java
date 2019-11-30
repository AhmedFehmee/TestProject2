package com.fahmy.testproject.ui.base;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.fahmy.testproject.InterviewApp;
import com.fahmy.testproject.di.component.ActivityComponent;
import com.fahmy.testproject.di.component.DaggerActivityComponent;
import com.fahmy.testproject.di.module.ActivityModule;
import com.fahmy.testproject.utils.CommonUtils;
import com.fahmy.testproject.utils.NetworkUtils;

public abstract class BaseActivity extends AppCompatActivity
        implements ActivityMvpView {

    // Activity Component
    ActivityComponent activityComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((InterviewApp) getApplication()).getApplicationComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @Override
    public void showLoading() {
        CommonUtils.showProgressBar(this);
    }

    @Override
    public void hideLoading() {
        CommonUtils.hideProgressBar(this);
    }

    @Override
    public boolean inNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }

    protected abstract void init();

}
