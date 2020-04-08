package com.fahmy.testproject;

import android.app.Application;
import com.fahmy.testproject.data.DataManager;
import com.fahmy.testproject.di.component.ApplicationComponent;
import com.fahmy.testproject.di.component.DaggerApplicationComponent;
import com.fahmy.testproject.di.module.ApplicationModule;
import com.fahmy.testproject.utils.AppConstants;
import javax.inject.Inject;

public class TestApp extends Application {
    @Inject
    DataManager mDataManager;

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, AppConstants.BASE_URL))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}