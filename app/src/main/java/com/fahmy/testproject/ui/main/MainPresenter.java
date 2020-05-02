package com.fahmy.testproject.ui.main;

import com.fahmy.testproject.data.DataManager;
import com.fahmy.testproject.data.network.model.CarsResponse;
import com.fahmy.testproject.ui.base.BasePresenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCarsFromApi(int pageNo, Boolean isReloaded) {
        if (getMvpView().inNetworkConnected()) {

            getMvpView().showLoading();

            getDataManager().getApiHelper().getCarsFromApi(pageNo)
                    .enqueue(new Callback<CarsResponse>() {
                        @Override
                        public void onResponse(Call<CarsResponse> call, Response<CarsResponse> response) {
                            getMvpView().hideLoading();
                            if (response.code() == 200 && response.body() != null) {
                                getMvpView().renderCarsListToUI(response.body());
                            } else {
                                getMvpView().retryLoadingCarsList();
                            }
                        }

                        @Override
                        public void onFailure(Call<CarsResponse> call, Throwable t) {
                            getMvpView().hideLoading();
                            getMvpView().retryLoadingCarsList();
                        }
                    });
        } else {
            // NetworkNotConnected
            getMvpView().onErrorMessageReceived("Network Error");
        }
    }
}

