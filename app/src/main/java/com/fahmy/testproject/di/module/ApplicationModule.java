package com.fahmy.testproject.di.module;

import android.app.Application;
import android.content.Context;

import com.fahmy.testproject.data.AppDataManager;
import com.fahmy.testproject.data.DataManager;
import com.fahmy.testproject.data.network.ApiHelper;
import com.fahmy.testproject.di.ApplicationContext;
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
public class ApplicationModule {

    private final Application mApplication;
    private final String mBaseUrl;

    public ApplicationModule(Application application, String baseUrl) {
        this.mApplication = application;
        this.mBaseUrl = baseUrl;
    }

    @ApplicationContext
    @Provides
    Context ApplicationContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    DataManager provideDataManager(AppDataManager AppDataManager) {
        return AppDataManager;
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
                ).writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient builder) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    ApiHelper provideApiHelper(Retrofit retrofit) {
        return retrofit.create(ApiHelper.class);
    }


}
