package com.fahmy.testproject.di.module;

import android.app.Application;
import android.content.Context;
import com.fahmy.testproject.data.AppDataManager;
import com.fahmy.testproject.data.DataManager;
import com.fahmy.testproject.data.network.ApiHelper;
import com.fahmy.testproject.data.pref.AppPreferencesHelper;
import com.fahmy.testproject.data.pref.PreferencesHelper;
import com.fahmy.testproject.di.ApplicationContext;
import com.fahmy.testproject.di.PreferencesInfo;
import com.fahmy.testproject.utils.AppConstants;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationTestModule {

    private final Application mApplication;

    public ApplicationTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferencesInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient builder) {
        return new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor).addInterceptor(chain -> {
                            Request request = chain.request();
                            return chain.proceed(request);
                        }
                ).writeTimeout(7, TimeUnit.SECONDS)
                .connectTimeout(7, TimeUnit.SECONDS)
                .readTimeout(7, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

}
