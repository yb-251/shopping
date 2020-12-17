package com.umeng.soexample.api;

import com.umeng.soexample.model.HomeData;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ShopApi {
    String BASE_URL = "http://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeData> getHomeData();
}
