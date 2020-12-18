package com.umeng.soexample.presenter;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.base.home.IDetails;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.DetailsGoodData;
import com.umeng.soexample.model.DetailsTabModel;
import com.umeng.soexample.ui.home.details.DetailsTabBean;

public class DetailsPresenter extends BasePresenter<IDetails.View> implements IDetails.Presenter{
    IDetails.Model model;
    public DetailsPresenter() {
        model = new DetailsTabModel();
    }

    @Override
    public void getDetailsTabData() {
        model.getDetailsTabData(new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getDetailsTabReturn((DetailsTabBean) o);
            }
        });
    }


    @Override
    public void getDetailsGoodsData(int id) {
        model.getDetailsGoodsData(new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getDetailsGoodsReturn((DetailsGoodData) o);
            }
        },id);
    }
}
