package com.umeng.soexample.model.shop;


import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.shop.ICar;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.ui.shop.DeleteCarBean;
import com.umeng.soexample.ui.shop.UpdateCarBean;
import com.umeng.soexample.utils.RxUtils;

import java.util.Map;

public class CarModel extends BaseModel implements ICar.Model {
    @Override
    public void getCarList(CallBack callback) {
        addDisposable(HttpManager.getInstance().getShopApi().getCarList().
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CarBean>(callback) {
                    @Override
                    public void onNext(CarBean carBean) {
                        callback.success(carBean);
                    }
                }));
    }

    @Override
    public void updateCar(Map<String, String> map, CallBack callback) {
        addDisposable(
                HttpManager.getInstance().getShopApi().updateCar(map)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UpdateCarBean>(callback) {
                    @Override
                    public void onNext(UpdateCarBean updateCarBean) {
                        callback.success(updateCarBean);
                    }
                })
        );
    }

    @Override
    public void deleteCar(String pIds, CallBack callback) {
        addDisposable(
                HttpManager.getInstance().getShopApi().deleteCar(pIds)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<DeleteCarBean>(callback) {
                    @Override
                    public void onNext(DeleteCarBean deleteCarBean) {
                        callback.success(deleteCarBean);
                    }
                })
        );
    }
}
