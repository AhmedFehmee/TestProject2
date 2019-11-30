package com.fahmy.testproject.ui.main;

import android.content.Context;
import com.fahmy.testproject.R;
import com.fahmy.testproject.data.DataManager;
import com.fahmy.testproject.data.network.model.AdviceResponse;
import com.fahmy.testproject.ui.base.BasePresenter;
import java.net.SocketTimeoutException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private boolean isReceivingData = false;
    private static final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getFortuneMessage() {
        if (getMvpView().inNetworkConnected()) {
            isReceivingData = true;
            Runnable task = () -> {
                if (isReceivingData){
                    getMvpView().onErrorMessageReceived(getDataManager().getAdvice());
                }
            };
            worker.schedule(task, 10, TimeUnit.SECONDS);

            getMvpView().showLoading();

            getDataManager().getApiHelper().getFortuneMessage()
                    .enqueue(new Callback<AdviceResponse>() {
                        @Override
                        public void onResponse(Call<AdviceResponse> call, Response<AdviceResponse> response) {
                            getMvpView().hideLoading();
                            if (response.code() == 200 && response.body() != null) {
                                getMvpView().renderAdviceMessageToUI(response.body());
                            } else if (response.code() == 408) {
                                getMvpView().onErrorMessageReceived(getDataManager().getAdvice());
                            } else {
                                getMvpView().retryLoadingAdviceMessage();
                            }
                            isReceivingData = false;
                        }

                        @Override
                        public void onFailure(Call<AdviceResponse> call, Throwable t) {
                            getMvpView().hideLoading();

                            if (t instanceof SocketTimeoutException) {
                                getMvpView().onErrorMessageReceived(getDataManager().getAdvice());
                            } else {
                                getMvpView().retryLoadingAdviceMessage();
                            }
                            isReceivingData = false;
                        }
                    });
        } else {
            getMvpView().onErrorMessageReceived(getDataManager().getAdvice());
        }
    }

    @Override
    public void saveCachedData(String advice) {
        getDataManager().setAdvice(advice);
    }

    @Override
    public void decideToSaveCached(Context context) {
        if (getDataManager().getAdvice() == null) {
            saveCachedData(context.getString(R.string.cached_advice_message));
        }
    }
}

