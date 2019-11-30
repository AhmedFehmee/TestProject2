package com.fahmy.testproject.ui.base;

import com.fahmy.testproject.data.DataManager;
import javax.inject.Inject;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mvpView;
    private DataManager dataManager;

    @Inject
    public BasePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        this.mvpView = null;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public V getMvpView() {
        return mvpView;
    }
}
