package com.umeng.soexample.presenter;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.home.IShot;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.ShotModel;
import com.umeng.soexample.model.data.ShotItemData;
import com.umeng.soexample.model.data.ShotTabBean;

public class ShotPresenter extends BasePresenter<IShot.View> implements IShot.Presenter{
    IShot.Model model;
    public ShotPresenter() {
        model = new ShotModel();
    }

    @Override
    public void getShotTabData() {
        model.getShotTabData(new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getShotTabReturn((ShotTabBean) o);
            }
        });
    }

    @Override
    public void getShotItemData(int id) {
        model.getShotItemData(id, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getShotItemReturn((ShotItemData) o);
            }
        });
    }
}
