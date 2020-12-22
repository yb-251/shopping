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
import com.umeng.soexample.model.data.LoginData;
import com.umeng.soexample.model.data.ShopAllData;
import com.umeng.soexample.model.data.ShotItemData;
import com.umeng.soexample.model.data.ShotTabBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ShopApi {
    String BASE_URL = "http://cdplay.cn/";

    @GET("api/index")
    Flowable<HomeData> getHomeData();

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

    @GET("api/catalog/index")
    Flowable<ShotTabBean> getTabData();

    @GET("api/catalog/current")
    Flowable<ShotItemData> getShotItemData(@Query("id") int id);

    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginData> login(@Field("username") String username, @Field("password") String pw);

    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<LoginData> addCar(@FieldMap HashMap<String,String> map);
}
