package com.fahmy.testproject.data.pref;

public interface PreferencesHelper {

    String getAdvice();

    void setAdvice(String adviceList);

    void setCachedFlag(Boolean cachedFlag);

    Boolean getCachedFlag();

}
