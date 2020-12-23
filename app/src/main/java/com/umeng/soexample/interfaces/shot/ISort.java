package com.umeng.soexample.interfaces.shot;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.sort.SortItemData;
import com.umeng.soexample.model.sort.SortTabBean;

public interface ISort {

    interface View extends IBaseView{
        void getShotTabReturn(SortTabBean sortTabBean);
        void getShotItemReturn(SortItemData sortItemData);
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
