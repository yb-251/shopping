package com.umeng.soexample.text;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ListApi {

    String sUrl = "http://cdwan.cn:7000/";

    @GET("tongpao/list.json")
    Observable<ListData> getData();
}
