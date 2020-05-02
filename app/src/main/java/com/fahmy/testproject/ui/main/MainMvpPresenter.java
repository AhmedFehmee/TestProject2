package com.fahmy.testproject.ui.main;

import com.fahmy.testproject.ui.base.ActivityMvpView;
import com.fahmy.testproject.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends ActivityMvpView> extends MvpPresenter<V> {

    void getCarsFromApi(int pageNo , Boolean isReloaded);
}
