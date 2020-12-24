package com.umeng.soexample.model.shop;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.shop.IShop;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.home.GoodDetailBean;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

import java.util.Map;

public class ShopModel extends BaseModel implements IShop.Model {
    @Override
    public void getGoodDetail(int id, CallBack callback) {
        addDisposable(HttpManager.getInstance().getShopApi().getGoodDetail(id).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodDetailBean>(callback) {
                    @Override
                    public void onNext(GoodDetailBean goodDetailBean) {
                        callback.success(goodDetailBean);
                    }
                }));
    }

    @Override
    public void laodGoodSeeSeeReturn(String id, String page, String size, CallBack callback) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getShopDataAll(id,page,size)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<ShopAllData>(callback) {
                            @Override
                            public void onNext(ShopAllData shopAllData) {
                                callback.success(shopAllData);
                            }
                        })
        );
    }

    @Override
    public void addGoodCar(Map<String, String> map, CallBack callback) {
        addDisposable(
                HttpManager.getInstance().getShopApi().addCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<AddCarBean>(callback) {
                    @Override
                    public void onNext(AddCarBean addCarBean) {
                        callback.success(addCarBean);
                    }
                })

        );
    }
}
