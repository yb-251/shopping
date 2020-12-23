package com.umeng.soexample.model.sort;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.shot.ISort;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class SortModel extends BaseModel implements ISort.Model {

    @Override
    public void getShotTabData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getTabData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortTabBean>(callBack) {
                    @Override
                    public void onNext(SortTabBean sortTabBean) {
                        callBack.success(sortTabBean);
                    }
                })
        );
    }

    @Override
    public void getShotItemData(int id, CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getShotItemData(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<SortItemData>(callBack) {
                    @Override
                    public void onNext(SortItemData sortItemData) {
                        callBack.success(sortItemData);
                    }
                })
        );
    }
}
