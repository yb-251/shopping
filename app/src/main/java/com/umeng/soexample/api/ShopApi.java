package com.umeng.soexample.api;

import com.umeng.soexample.model.CategoryBean;
import com.umeng.soexample.model.CategoryGoodBean;
import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.model.data.BrandDetailsData;
import com.umeng.soexample.model.data.BrandDetailsItemData;
import com.umeng.soexample.model.data.BrandDetailsListData;
import com.umeng.soexample.model.data.GoodDetailBean;
import com.umeng.soexample.model.data.GoodsHotBean;
import com.umeng.soexample.model.data.HotGoodListBean;
import com.umeng.soexample.model.data.ShopAllData;
import com.umeng.soexample.ui.home.details.DetailsTabBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopApi {
    String BASE_URL = "http://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeData> getHomeData();

    @GET("api/catalog/index")
    Flowable<DetailsTabBean> getTabData();

    @GET("goods/category")
    Flowable<CategoryBean> getCategory(@Query("id") int id);

    @GET("api/goods/list")
    Flowable<CategoryGoodBean> getGoodList(@Query("categoryId") int categoryId, @Query("page") int page, @Query("size") int size);

    @GET("api/brand/list?page=1&size=1000")
    Flowable<BrandDetailsData> getBrandDetailsData();

    @GET("api/brand/detail")
    Flowable<BrandDetailsItemData> getBrandDetailsItemData(@Query("id")int cid);

    @GET("api/goods/list")
    Flowable<BrandDetailsListData> getBrandDetailsListData(@Query("brandId")int cid,@Query("page")int page,@Query("size")int size);

    //新品发布的条件筛选数据接口
    @GET("api/goods/list")
    Flowable<HotGoodListBean> getHotGoodList(@QueryMap Map<String,String> map);
    //新品首页图片
    //https://cdplay.cn/api/goods/hot
    @GET("api/goods/hot")
    Flowable<GoodsHotBean>getGoosHotData();

    //商品详情购买页
    @GET("api/goods/detail")
    Flowable<GoodDetailBean> getGoodDetail(@Query("id") int id);

    //详情 -- https://cdplay.cn/api/goods/list?categoryId=1005000&page=1&size=100
    @GET("goods/list")
    Flowable<ShopAllData> getShopDataAll(@Query("categoryId") String id, @Query("page") String page, @Query("size") String size);
}
