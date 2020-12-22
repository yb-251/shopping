package com.umeng.soexample.interfaces.login;

import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.interfaces.IBaseView;
import com.umeng.soexample.interfaces.IModel;
import com.umeng.soexample.model.data.LoginData;

public interface ILogin {
    interface View extends IBaseView {
        void loginReturn(LoginData loginBean);
    }

    interface Presenter extends IBasePresenter<View> {
        void login(String username,String pw);
    }


    interface Model extends IModel {
        void login(String username,String pw, CallBack callback);
    }
}
