package com.umeng.soexample.interfaces.shop;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.shop.CarBean;

public interface ICar {
    interface View extends IBaseView {
        void getCarListReturn(CarBean carBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCarList();
    }


    interface Model extends IModel {
        void getCarList(CallBack callback);
    }

}
