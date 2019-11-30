package com.fahmy.testproject.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import com.fahmy.testproject.di.ApplicationContext;
import com.fahmy.testproject.di.PreferencesInfo;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    // constant String Keys for SharedPreferences
    private static final String PREF_KEY_ADVICE_LIST = "PREF_KEY_ADVICE_LIST";
    private static final String PREF_KEY_CACHED_FLAG = "PREF_KEY_CACHED_FLAG";

    private final SharedPreferences mPref;

    /* @ApplicationContext,PreferencesInfo :
     * dependencies will be provided into Application module
     **/
    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferencesInfo String prefFileName) {

        mPref = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAdvice() {
        return mPref.getString(PREF_KEY_ADVICE_LIST, null);
    }

    @Override
    public void setAdvice(String advice) {
        mPref.edit().putString(PREF_KEY_ADVICE_LIST, new Gson().toJson(advice)).apply();
    }

    @Override
    public void setCachedFlag(Boolean cachedFlag) {
        mPref.edit().putBoolean(PREF_KEY_CACHED_FLAG, cachedFlag).apply();
    }

    @Override
    public Boolean getCachedFlag() {
        return mPref.getBoolean(PREF_KEY_CACHED_FLAG, false);
    }
}
