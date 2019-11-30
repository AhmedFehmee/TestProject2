package com.fahmy.testproject.data;

import android.content.Context;
import com.fahmy.testproject.data.network.ApiHelper;
import com.fahmy.testproject.data.network.model.AdviceResponse;
import com.fahmy.testproject.data.pref.PreferencesHelper;
import com.fahmy.testproject.di.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;


@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper apiHelper;

    @Inject
    AppDataManager(@ApplicationContext Context context,
                   PreferencesHelper preferencesHelper,
                   ApiHelper apiHelper) {
        this.mContext = context;
        this.mPreferencesHelper = preferencesHelper;
        this.apiHelper = apiHelper;
    }

    @Override
    public ApiHelper getApiHelper() {
        return apiHelper;
    }

    @Override
    public String getAdvice() {
        return mPreferencesHelper.getAdvice();
    }

    @Override
    public void setAdvice(String advice) {
        mPreferencesHelper.setAdvice(advice);
    }

    @Override
    public void setCachedFlag(Boolean cachedFlag) {
        mPreferencesHelper.setCachedFlag(cachedFlag);
    }

    @Override
    public Boolean getCachedFlag() {
        return mPreferencesHelper.getCachedFlag();
    }

    @Override
    public Call<AdviceResponse> getFortuneMessage() {
        return apiHelper.getFortuneMessage();
    }
}
