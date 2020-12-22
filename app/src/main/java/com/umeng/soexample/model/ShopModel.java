package com.umeng.soexample.model;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.home.IShop;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.data.GoodDetailBean;
import com.umeng.soexample.model.data.ShopAllData;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

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
}
