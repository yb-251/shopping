package com.umeng.soexample.model;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.base.home.IDetails;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.ui.home.details.DetailsTabBean;
import com.umeng.soexample.utils.RxUtils;

public class DetailsTabModel extends BaseModel implements IDetails.Model {
    @Override
    public void getDetailsTabData(CallBack callBack) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getTabData()
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DetailsTabBean>(callBack) {
                    @Override
                    public void onNext(DetailsTabBean detailsTabBean) {
                        callBack.success(detailsTabBean);
                    }
                })
        );
    }

    @Override
    public void getDetailsGoodsData(CallBack callBack,int id) {
        addDisposable(
                HttpManager.getInstance().getShopApi().getListData(id)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DetailsGoodData>(callBack) {
                    @Override
                    public void onNext(DetailsGoodData detailsGoodData) {
                        callBack.success(detailsGoodData);
                    }
                })
        );
    }
}
