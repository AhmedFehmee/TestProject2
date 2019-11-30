package com.fahmy.testproject.data.network;

import com.fahmy.testproject.data.network.model.AdviceResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiHelper {

    @GET("fortune")
    Call<AdviceResponse> getFortuneMessage();
}
