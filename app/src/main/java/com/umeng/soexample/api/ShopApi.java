package com.umeng.soexample.api;

import com.umeng.soexample.model.DetailsGoodData;
import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.ui.home.details.DetailsTabBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopApi {
    String BASE_URL = "http://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeData> getHomeData();

    @GET("api/catalog/index")
    Flowable<DetailsTabBean> getTabData();

    //商场 详情页 ?id=1005000 分类数据
    @GET("api/goods/list")
    Flowable<DetailsGoodData> getListData(@Query("categoryId")int cid);
}
