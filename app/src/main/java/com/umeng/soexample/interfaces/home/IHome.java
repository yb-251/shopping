package com.umeng.soexample.interfaces.home;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.HomeData;

public interface IHome {

    interface View extends IBaseView{
        void getHomeReturn(HomeData homeData);
    }

    interface Presenter extends IBasePresenter<View>{
        void getHomeData();
    }

    interface Model extends IModel{
        void getHomeData(CallBack callBack);
    }

}
