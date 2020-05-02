package com.fahmy.testproject.ui.main;

import com.fahmy.testproject.data.network.model.CarsResponse;
import com.fahmy.testproject.ui.base.ActivityMvpView;

public interface MainMvpView extends ActivityMvpView {

    void renderCarsListToUI(CarsResponse carsResponse);

    void retryLoadingCarsList();

    void onErrorMessageReceived(String errorMessage);
}
