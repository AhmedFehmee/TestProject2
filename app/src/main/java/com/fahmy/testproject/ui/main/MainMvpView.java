package com.fahmy.testproject.ui.main;

import com.fahmy.testproject.data.network.model.AdviceResponse;
import com.fahmy.testproject.ui.base.ActivityMvpView;

public interface MainMvpView extends ActivityMvpView {

    void renderAdviceMessageToUI(AdviceResponse response);

    void retryLoadingAdviceMessage();

    void onErrorMessageReceived(String advice);
}
