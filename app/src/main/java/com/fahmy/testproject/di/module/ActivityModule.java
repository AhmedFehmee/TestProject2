package com.fahmy.testproject.di.module;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.fahmy.testproject.di.ActivityContext;
import com.fahmy.testproject.ui.main.MainMvpPresenter;
import com.fahmy.testproject.ui.main.MainMvpView;
import com.fahmy.testproject.ui.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideAppCompatActivity() {
        return mActivity;
    }

    @Provides
    MainMvpPresenter<MainMvpView> mainMvpPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

}
