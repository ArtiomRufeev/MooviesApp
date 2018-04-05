package com.itrexgroup.test.moovies.network;

import com.itrexgroup.test.moovies.model.MooviesListResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface MooviesApiService {

    @GET("/v2/57cffac8260000181e650041")
    Call<MooviesListResponse> getMooviesList();
}
