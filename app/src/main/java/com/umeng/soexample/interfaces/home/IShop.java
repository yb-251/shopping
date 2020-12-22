package com.umeng.soexample.interfaces.home;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.data.GoodDetailBean;
import com.umeng.soexample.model.data.ShopAllData;

public interface IShop {
    interface View extends IBaseView {
        void getGoodDetail(GoodDetailBean goodDetailBean);
        void getGoodSeeSeeReturn(ShopAllData result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getGoodDetail(int id);
        void resultGoodSeeSeeReturn(String id,String page,String size);
    }


    interface Model extends IModel {
        void getGoodDetail(int id, CallBack callback);
        void laodGoodSeeSeeReturn(String id,String page,String size, CallBack callback);
    }
}
