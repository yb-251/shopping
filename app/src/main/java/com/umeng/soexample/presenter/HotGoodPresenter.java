package com.umeng.soexample.presenter;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.base.home.IHotGood;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.HotGoodModel;
import com.umeng.soexample.model.data.GoodsHotBean;
import com.umeng.soexample.model.data.HotGoodListBean;

import java.util.Map;

public class HotGoodPresenter extends BasePresenter<IHotGood.View> implements IHotGood.Presenter {
    IHotGood.Model model;
    public HotGoodPresenter(){
        model = new HotGoodModel();
    }


    @Override
    public void getHotGood(Map map) {
        model.getHotGood(map, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getHotGood((HotGoodListBean) o);
            }
        });
    }

    @Override
    public void getGoodsHotData() {
        model.getGoodsHotData(new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getGoodsHotData((GoodsHotBean) o);
            }
        });
    }
}
