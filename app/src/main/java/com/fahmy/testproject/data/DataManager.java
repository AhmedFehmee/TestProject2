package com.fahmy.testproject.data;

import com.fahmy.testproject.data.network.ApiHelper;
import com.fahmy.testproject.data.pref.PreferencesHelper;

public interface DataManager extends PreferencesHelper, ApiHelper {

    ApiHelper getApiHelper();
}
