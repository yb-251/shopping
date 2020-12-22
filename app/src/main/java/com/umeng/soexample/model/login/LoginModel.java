package com.umeng.soexample.model.login;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.interfaces.CallBack;
import com.umeng.soexample.interfaces.login.ILogin;
import com.umeng.soexample.model.data.LoginData;
import com.umeng.soexample.net.CommonSubscriber;
import com.umeng.soexample.net.HttpManager;
import com.umeng.soexample.utils.RxUtils;

public class LoginModel extends BaseModel implements ILogin.Model {

    @Override
    public void login(String username,String pw, CallBack callback) {
        addDisposable(HttpManager.getInstance().getShopApi().login(username,pw).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<LoginData>(callback) {
                    @Override
                    public void onNext(LoginData loginBean) {
                        callback.success(loginBean);
                    }
                }));
    }

}
