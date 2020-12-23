package com.umeng.soexample.presenter.sort;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.shot.ISort;
import com.umeng.soexample.model.sort.SortModel;
import com.umeng.soexample.model.sort.SortItemData;
import com.umeng.soexample.model.sort.SortTabBean;

public class SortPresenter extends BasePresenter<ISort.View> implements ISort.Presenter{
    ISort.Model model;
    public SortPresenter() {
        model = new SortModel();
    }

    @Override
    public void getShotTabData() {
        model.getShotTabData(new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getShotTabReturn((SortTabBean) o);
            }
        });
    }

    @Override
    public void getShotItemData(int id) {
        model.getShotItemData(id, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.getShotItemReturn((SortItemData) o);
            }
        });
    }
}
