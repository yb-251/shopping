package com.umeng.soexample.presenter;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.base.home.IBrandDetails;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.model.data.BrandDetailsData;
import com.umeng.soexample.model.data.BrandDetailsItemData;
import com.umeng.soexample.model.data.BrandDetailsListData;
import com.umeng.soexample.model.shop.BrandDetailsModel;

public class BrandDetailsPresenter extends BasePresenter<IBrandDetails.View> implements IBrandDetails.Presenter {

    IBrandDetails.Model model;

    public BrandDetailsPresenter() {
        model = new BrandDetailsModel();
    }

    @Override
    public void getBrandDetailsData() {
        model.getBrandDetailsData(new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getBrandDetailsReturn((BrandDetailsData) o);
            }
        });
    }

    @Override
    public void getBrandDetailsItemData(int id) {
        model.getBrandDetailsItemData(new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getBrandDetailsItemReturn((BrandDetailsItemData) o);
            }
        },id);
    }

    @Override
    public void getBrandDetailsListData(int id, int page, int size) {
        model.getBrandDetailsListData(new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getBrandDetailsListReturn((BrandDetailsListData) o);
            }
        },id,page,size);
    }
}
