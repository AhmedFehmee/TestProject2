package com.fahmy.testproject.data.network;

import com.fahmy.testproject.data.network.model.CarsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiHelper {

    @GET("cars")
    Call<CarsResponse> getCarsFromApi(@Query("page") int page);
}
