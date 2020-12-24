package com.umeng.soexample.api;

import com.umeng.soexample.model.home.CategoryBean;
import com.umeng.soexample.model.home.CategoryGoodBean;
import com.umeng.soexample.model.home.HomeData;
import com.umeng.soexample.model.home.BrandDetailsData;
import com.umeng.soexample.model.home.BrandDetailsItemData;
import com.umeng.soexample.model.home.BrandDetailsListData;
import com.umeng.soexample.model.home.GoodDetailBean;
import com.umeng.soexample.model.home.GoodsHotBean;
import com.umeng.soexample.model.home.HotGoodListBean;
import com.umeng.soexample.model.login.LoginData;
import com.umeng.soexample.model.shop.AddCarBean;
import com.umeng.soexample.model.shop.ShopAllData;
import com.umeng.soexample.model.sort.SortItemData;
import com.umeng.soexample.model.sort.SortTabBean;
import com.umeng.soexample.model.shop.CarBean;
import com.umeng.soexample.ui.shop.DeleteCarBean;
import com.umeng.soexample.ui.shop.UpdateCarBean;

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
    Flowable<SortTabBean> getTabData();

    @GET("api/catalog/current")
    Flowable<SortItemData> getShotItemData(@Query("id") int id);

    @POST("api/auth/login")
    @FormUrlEncoded
    Flowable<LoginData> login(@Field("username") String username, @Field("password") String pw);

    //添加到购物车
    @POST("api/cart/add")
    @FormUrlEncoded
    Flowable<AddCarBean> addCar(@FieldMap Map<String,String> map);

    //购物车列表
    @GET("api/cart/index")
    Flowable<CarBean> getCarList();

    //更新购物车的数据
    @POST("api/cart/update")
    @FormUrlEncoded
    Flowable<UpdateCarBean> updateCar(@FieldMap Map<String,String> map);

    //删除购物车数据
    @POST("api/cart/delete")
    @FormUrlEncoded
    Flowable<DeleteCarBean> deleteCar(@Field("productIds") String productIds);
}
