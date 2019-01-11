package com.evolutions.jabar.testmagang.api;

import com.evolutions.jabar.testmagang.model.ModelLogin;
import com.evolutions.jabar.testmagang.model.ModelProduct;
import com.evolutions.jabar.testmagang.model.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("auth/login")
    Call<ModelLogin>login(@Field("nik")String nik,
                          @Field("password")String password);
    @GET("subarea/list")
    Call<Response>response(@Header("Authorization") String token);
    @GET("pk/list")
    Call<ModelProduct>request(@Header("Authorization") String token);
}
