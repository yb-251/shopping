package com.umeng.soexample.model.home;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.home.ICategory;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class CategoryModel extends BaseModel implements ICategory.Model {
    @Override
    public void getCategory(int categoryid, CallBack callback) {
        addDisposable(HttpManager.getInstance().getShopApi().getCategory(categoryid).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryBean>(callback) {
                    @Override
                    public void onNext(CategoryBean categoryBean) {
                        callback.success(categoryBean);
                    }
                }));
    }

    @Override
    public void getCategoryGood(int categoryId, int page, int size, CallBack callback) {
        addDisposable(HttpManager.getInstance().getShopApi().getGoodList(categoryId,page,size).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<CategoryGoodBean>(callback) {
                    @Override
                    public void onNext(CategoryGoodBean categoryBean) {
                        callback.success(categoryBean);
                    }
                }));
    }
}
