package com.umeng.soexample.api;

import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.ui.home.details.DetailsTabBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopApi {
    String BASE_URL = "http://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeData> getHomeData();

    //商场 详情页 ?id=1005000 分类
    @GET("api/catalog/index/pages/category/category")
    Flowable<DetailsTabBean> getTabData(@Query("id") int id);

    //商场 详情页 ?id=1005000 分类数据
    @GET("http://cdplay.cn/api/goods/list")
    Flowable<SPListDetailsBean> getListData(@Query("categoryId")int cid);
}
