package com.umeng.soexample.interfaces.home;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.data.GoodsHotBean;
import com.umeng.soexample.model.data.HotGoodListBean;

import java.util.Map;

public interface IHotGood {
    interface View extends IBaseView {
        void getHotGood(HotGoodListBean result);
        void getGoodsHotData(GoodsHotBean goodsHotBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void getHotGood(Map map);
        void getGoodsHotData();
    }

    interface Model extends IModel {
        void getHotGood(Map map, CallBack callback);
        void getGoodsHotData(CallBack callback);
    }
}
