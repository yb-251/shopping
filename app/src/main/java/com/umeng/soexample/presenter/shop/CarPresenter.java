package com.umeng.soexample.presenter.shop;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.shop.ICar;
import com.umeng.soexample.model.shop.CarBean;
import com.umeng.soexample.model.shop.CarModel;

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
}
