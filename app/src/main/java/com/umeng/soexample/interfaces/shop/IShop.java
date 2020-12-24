package com.umeng.soexample.interfaces.shop;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.home.GoodDetailBean;
import com.umeng.soexample.model.shop.AddCarBean;
import com.umeng.soexample.model.shop.ShopAllData;

import java.util.Map;

public interface IShop {
    interface View extends IBaseView {
        void getGoodDetail(GoodDetailBean goodDetailBean);
        void getGoodSeeSeeReturn(ShopAllData result);

        void addGoodCarReturn(AddCarBean addCarBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getGoodDetail(int id);
        void resultGoodSeeSeeReturn(String id,String page,String size);

        //添加进购物车
        void addGoodCar(Map<String,String> map);
    }

    interface Model extends IModel {
        void getGoodDetail(int id, CallBack callback);
        void laodGoodSeeSeeReturn(String id,String page,String size, CallBack callback);

        //添加进购物车
        void addGoodCar(Map<String,String> map,CallBack callback);
    }
}
