package com.umeng.soexample.base.home;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.DetailsGoodData;
import com.umeng.soexample.ui.home.details.DetailsTabBean;

public interface IDetails {

    interface View extends IBaseView{
        void getDetailsTabReturn(DetailsTabBean detailsTabBean);
        void getDetailsGoodsReturn(DetailsGoodData detailsGoodData);
    }

    interface Presenter extends IBasePresenter<View>{
        void getDetailsTabData();
        void getDetailsGoodsData(int id);
    }

    interface Model extends IModel{
        void getDetailsTabData(CallBack callBack);
        void getDetailsGoodsData(CallBack callBack,int id);
    }

}
