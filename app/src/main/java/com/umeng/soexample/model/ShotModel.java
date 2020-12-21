package com.umeng.soexample.model;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.base.home.IShot;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.data.ShotItemData;
import com.umeng.soexample.model.data.ShotTabBean;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class ShotModel extends BaseModel implements IShot.Model {

    @Override
    public void getShotTabData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getTabData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ShotTabBean>(callBack) {
                    @Override
                    public void onNext(ShotTabBean shotTabBean) {
                        callBack.success(shotTabBean);
                    }
                })
        );
    }

    @Override
    public void getShotItemData(int id, CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getShotItemData(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<ShotItemData>(callBack) {
                    @Override
                    public void onNext(ShotItemData shotItemData) {
                        callBack.success(shotItemData);
                    }
                })
        );
    }
}
