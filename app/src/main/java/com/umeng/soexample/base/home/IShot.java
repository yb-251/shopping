package com.umeng.soexample.base.home;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.data.ShotItemData;
import com.umeng.soexample.model.data.ShotTabBean;

public interface IShot {

    interface View extends IBaseView{
        void getShotTabReturn(ShotTabBean shotTabBean);
        void getShotItemReturn(ShotItemData shotItemData);
    }

    interface Presenter extends IBasePresenter<View>{
        void getShotTabData();
        void getShotItemData(int id);
    }

    interface Model extends IModel{
        void getShotTabData(CallBack callBack);
        void getShotItemData(int id,CallBack callBack);
    }
}
