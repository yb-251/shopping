package com.umeng.soexample.interfaces.home;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.CategoryBean;
import com.umeng.soexample.model.CategoryGoodBean;

public interface ICategory {

    interface View extends IBaseView {
        void getCategoryTabReturn(CategoryBean result);

        void getCategoryGoodReturn(CategoryGoodBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void getCategoryTab(int categoryId);

        void getCategoryGood(int categoryId, int page, int size);
    }

    interface Model extends IModel {
        void getCategory(int categoryid, CallBack callback);

        void getCategoryGood(int categoryId, int page, int size, CallBack callback);
    }

}
