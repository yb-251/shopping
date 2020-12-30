package com.umeng.soexample.presenter.my;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.my.IUser;
import com.umeng.soexample.model.my.UserInfoBean;
import com.umeng.soexample.model.my.UserModel;

import java.util.Map;

public class UserPresenter extends BasePresenter<IUser.View> implements IUser.Presenter{

    IUser.Model model;

    public UserPresenter() {
        model = new UserModel();
    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
        model.updateUserInfo(map, new CallBack() {
            @Override
            public void fail(String msg) {

            }

            @Override
            public void success(Object o) {
                mView.updateUserInfoReturn((UserInfoBean) o);
            }
        });
    }
}
