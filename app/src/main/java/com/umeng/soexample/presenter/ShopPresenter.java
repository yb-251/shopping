package com.umeng.soexample.presenter;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.base.home.IShop;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.ShopModel;
import com.umeng.soexample.model.data.GoodDetailBean;
import com.umeng.soexample.model.data.ShopAllData;

public class ShopPresenter extends BasePresenter<IShop.View> implements IShop.Presenter {
    IShop.Model model;
    public ShopPresenter(){
        model = new ShopModel();
    }
    @Override
    public void getGoodDetail(int id) {
        model.getGoodDetail(id,new CallBack<GoodDetailBean>() {
            @Override
            public void success(GoodDetailBean data) {
                if(mView != null){
                    mView.getGoodDetail(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

    @Override
    public void resultGoodSeeSeeReturn(String id, String page, String size) {
        model.laodGoodSeeSeeReturn(id, page, size, new CallBack() {
            @Override
            public void fail(String msg) {
                if (mView!=null){
                    mView.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if (mView!=null){
                    mView.getGoodSeeSeeReturn((ShopAllData) o);
                }
            }
        });
    }
}
