package com.umeng.soexample.presenter.shop;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.shop.ICar;
import com.umeng.soexample.model.shop.CarBean;
import com.umeng.soexample.model.shop.CarModel;
import com.umeng.soexample.ui.shop.DeleteCarBean;
import com.umeng.soexample.ui.shop.UpdateCarBean;

import java.util.Map;

public class CarPresenter extends BasePresenter<ICar.View> implements ICar.Presenter {

    ICar.Model model;
    public CarPresenter(){
        model = new CarModel();
    }
    @Override
    public void getCarList() {
        model.getCarList(new CallBack() {
            @Override
            public void fail(String err) {

            }

            @Override
            public void success(Object o) {
                mView.getCarListReturn((CarBean) o);
            }
        });
    }

    @Override
    public void updateCar(Map<String, String> map) {
        model.updateCar(map, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.updateCarReturn((UpdateCarBean) o);
            }
        });
    }

    @Override
    public void deleteCar(String pIds) {
        model.deleteCar(pIds, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.deleteCarReturn((DeleteCarBean) o);
            }
        });
    }
}
