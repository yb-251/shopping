package com.umeng.soexample.presenter;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.base.home.IHome;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.model.HomeModel;

public class HomePresenter extends BasePresenter<IHome.View> implements IHome.Presenter{
    IHome.Model model;
    public HomePresenter() {
        model = new HomeModel();
    }

    @Override
    public void getHomeData() {
        model.getHomeData(new CallBack() {
            @Override
            public void fail(String msg) {
                if (mView != null){
                    mView.tips(msg);
                }
            }

            @Override
            public void success(Object o) {
                if (mView != null){
                    mView.getHomeReturn((HomeData) o);
                }
            }
        });
    }
}
