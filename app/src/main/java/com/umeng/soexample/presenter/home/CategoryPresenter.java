package com.umeng.soexample.presenter.home;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.home.ICategory;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.model.home.CategoryBean;
import com.umeng.soexample.model.home.CategoryGoodBean;
import com.umeng.soexample.model.home.CategoryModel;

public class CategoryPresenter extends BasePresenter<ICategory.View> implements ICategory.Presenter {

    ICategory.Model model;

    public CategoryPresenter(){
        model = new CategoryModel();
    }

    @Override
    public void getCategoryTab(int categoryId) {
        model.getCategory(categoryId, new CallBack() {
            @Override
            public void fail(String err) {

            }

            @Override
            public void success(Object o) {
                if (mView != null){
                    mView.getCategoryTabReturn((CategoryBean) o);
                }
            }
        });
    }

    @Override
    public void getCategoryGood(int categoryId, int page, int size) {
        model.getCategoryGood(categoryId, page, size, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getCategoryGoodReturn((CategoryGoodBean) o);
            }
        });
    }


}
