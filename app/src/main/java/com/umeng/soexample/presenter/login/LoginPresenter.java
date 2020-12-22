package com.umeng.soexample.presenter.login;


import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.login.ILogin;
import com.umeng.soexample.model.data.LoginData;
import com.umeng.soexample.model.login.LoginModel;

public class LoginPresenter extends BasePresenter<ILogin.View> implements ILogin.Presenter {
    ILogin.Model model;
    public LoginPresenter(){
        model = new LoginModel();
    }
    @Override
    public void login(String username,String pw) {
        model.login(username,pw,new CallBack() {
            @Override
            public void fail(String err) {

            }

            @Override
            public void success(Object o) {
                mView.loginReturn((LoginData) o);
            }
        });
    }
}
