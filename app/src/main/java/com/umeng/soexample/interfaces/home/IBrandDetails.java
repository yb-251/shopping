package com.umeng.soexample.interfaces.home;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.data.BrandDetailsData;
import com.umeng.soexample.model.data.BrandDetailsItemData;
import com.umeng.soexample.model.data.BrandDetailsListData;

public interface IBrandDetails {

    interface View extends IBaseView{
        void getBrandDetailsReturn(BrandDetailsData brandDetailsData);
        void getBrandDetailsItemReturn(BrandDetailsItemData brandDetailsItemData);
        void getBrandDetailsListReturn(BrandDetailsListData brandDetailsListData);
    }

    interface Presenter extends IBasePresenter<View>{
        void getBrandDetailsData();
        void getBrandDetailsItemData(int id);
        void getBrandDetailsListData(int id,int page,int size);
    }

    interface Model extends IModel{
        void getBrandDetailsData(CallBack callBack);
        void getBrandDetailsItemData(CallBack callBack,int id);
        void getBrandDetailsListData(CallBack callBack,int id,int page,int size);
    }

}
