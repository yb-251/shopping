package com.umeng.soexample.model.home;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.home.IHotGood;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

import java.util.Map;

import io.reactivex.disposables.Disposable;

public class HotGoodModel extends BaseModel implements IHotGood.Model {
    @Override
    public void getHotGood(Map map, CallBack callback) {
        addDisposable((Disposable) HttpManager.getInstance().getShopApi().getHotGoodList(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HotGoodListBean>(callback) {
                    @Override
                    public void onNext(HotGoodListBean hotGoodListBean) {
                        callback.success(hotGoodListBean);
                    }
                }));
    }

    @Override
    public void getGoodsHotData(CallBack callback) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getGoosHotData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodsHotBean>(callback) {
                    @Override
                    public void onNext(GoodsHotBean goodsHotBean) {
                        callback.success(goodsHotBean);
                    }
                })
        );
    }
}
