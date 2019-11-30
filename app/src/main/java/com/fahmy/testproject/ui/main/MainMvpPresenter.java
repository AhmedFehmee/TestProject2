package com.fahmy.testproject.ui.main;

import android.content.Context;
import com.fahmy.testproject.ui.base.ActivityMvpView;
import com.fahmy.testproject.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends ActivityMvpView> extends MvpPresenter<V> {

    void getFortuneMessage();

    void saveCachedData(String advice);

    void decideToSaveCached(Context context);
}
