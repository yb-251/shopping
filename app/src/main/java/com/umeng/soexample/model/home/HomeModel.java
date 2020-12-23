package com.umeng.soexample.model.home;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.home.IHome;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class HomeModel extends BaseModel implements IHome.Model {
    @Override
    public void getHomeData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getHomeData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<HomeData>(callBack) {
                    @Override
                    public void onNext(HomeData homeData) {
                        callBack.success(homeData);
                    }
                })
        );
    }
}
