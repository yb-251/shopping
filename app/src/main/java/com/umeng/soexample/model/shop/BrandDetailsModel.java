package com.umeng.soexample.model.shop;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.home.IBrandDetails;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.home.BrandDetailsData;
import com.umeng.soexample.model.home.BrandDetailsItemData;
import com.umeng.soexample.model.home.BrandDetailsListData;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class BrandDetailsModel extends BaseModel implements IBrandDetails.Model {
    @Override
    public void getBrandDetailsData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getBrandDetailsData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<BrandDetailsData>(callBack) {
                    @Override
                    public void onNext(BrandDetailsData brandDetailsData) {
                        callBack.success(brandDetailsData);
                    }
                })
        );
    }

    @Override
    public void getBrandDetailsItemData(CallBack callBack, int id) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getBrandDetailsItemData(id)
                        .compose(RxUtils.rxScheduler())
                        .subscribeWith(new CommonSubscriber<BrandDetailsItemData>(callBack) {
                            @Override
                            public void onNext(BrandDetailsItemData brandDetailsItemData) {
                                callBack.success(brandDetailsItemData);
                            }
                        })
        );
    }

    @Override
    public void getBrandDetailsListData(CallBack callBack, int id, int page, int size) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getBrandDetailsListData(id,page,size)
                        .compose(RxUtils.rxScheduler())
                    .subscribeWith(new CommonSubscriber<BrandDetailsListData>(callBack) {
                            @Override
                            public void onNext(BrandDetailsListData brandDetailsListData) {
                                callBack.success(brandDetailsListData);
                            }
                        })
        );
    }
}
