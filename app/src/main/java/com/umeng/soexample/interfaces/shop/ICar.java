package com.umeng.soexample.interfaces.shop;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.shop.CarBean;
import com.umeng.soexample.ui.shop.DeleteCarBean;
import com.umeng.soexample.ui.shop.UpdateCarBean;

import java.util.Map;

public interface ICar {
    interface View extends IBaseView {
        void getCarListReturn(CarBean carBean);

        //更新 购物车
        void updateCarReturn(UpdateCarBean result);

        //删除购物车
        void deleteCarReturn(DeleteCarBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCarList();

        //更新购物车的数据
        void  updateCar(Map<String,String> map);

        //删除购物车列表
        void deleteCar(String pIds);
    }


    interface Model extends IModel {
        void getCarList(CallBack callback);

        void updateCar(Map<String,String> map,CallBack callback);

        void deleteCar(String pIds,CallBack callback);
    }

}
