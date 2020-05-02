package com.fahmy.testproject.data;

import android.content.Context;
import com.fahmy.testproject.data.network.ApiHelper;
import com.fahmy.testproject.data.network.model.CarsResponse;
import com.fahmy.testproject.di.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;


@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper apiHelper;

    @Inject
    AppDataManager(@ApplicationContext Context context,
                   ApiHelper apiHelper) {
        this.mContext = context;
        this.apiHelper = apiHelper;
    }

    @Override
    public ApiHelper getApiHelper() {
        return apiHelper;
    }

    @Override
    public Call<CarsResponse> getCarsFromApi(int page) {
        return apiHelper.getCarsFromApi(page);
    }
}
