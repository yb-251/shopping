package com.umeng.soexample.ui.my;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.login.ILogin;
import com.umeng.soexample.model.login.LoginData;
import com.umeng.soexample.presenter.login.LoginPresenter;
import com.umeng.soexample.utils.SpUtils;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, ILogin.View, TextWatcher {

    private View account_center_line;
    private ImageView clear_view;
    private EditText account_name;
    private ImageView show_secret;
    private EditText account_secret;
    private ConstraintLayout account_input_view;
    private View verify_center_line;
    private TextView area_code;
    private View verify_phone_cut_line;
    private EditText phone_et;
    private TextView get_verify;
    private View get_verify_line;
    private EditText verify_code;
    private ConstraintLayout verify_group;
    private View account_bottom_line;
    private TextView login;
    private TextView register;
    private TextView forgot_pwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPrenter() {
        return new LoginPresenter();
    }

    protected void initView() {
        clear_view = (ImageView) findViewById(R.id.clear_view);
        account_name = (EditText) findViewById(R.id.account_name);
        show_secret = (ImageView) findViewById(R.id.show_secret);
        account_secret = (EditText) findViewById(R.id.account_secret);
        account_input_view = (ConstraintLayout) findViewById(R.id.account_input_view);
        area_code = (TextView) findViewById(R.id.area_code);
        phone_et = (EditText) findViewById(R.id.phone_et);
        get_verify = (TextView) findViewById(R.id.get_verify);
        verify_code = (EditText) findViewById(R.id.verify_code);
        verify_group = (ConstraintLayout) findViewById(R.id.verify_group);
        login = (TextView) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        forgot_pwd = (TextView) findViewById(R.id.forgot_pwd);

        account_name.addTextChangedListener(this);

        clear_view.setOnClickListener(this);
        show_secret.setOnClickListener(this);
        login.setOnClickListener(this);
        account_secret.setOnClickListener(this);
        phone_et.setOnClickListener(this);
        verify_code.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear_view:
                account_name.setText("");
                break;
            case R.id.show_secret:
                account_secret.setTransformationMethod(show_secret.isSelected() ? PasswordTransformationMethod.getInstance() : HideReturnsTransformationMethod.getInstance());
                show_secret.setSelected(!show_secret.isSelected());
                break;
            case R.id.login:
                login();
                break;
            case R.id.register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            case R.id.forgot_pwd:
                break;
        }
    }

    private void login() {
        String username = account_name.getText().toString();
        String pwd = account_secret.getText().toString();
        if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)){
            presenter.login(username,pwd);
        }else{
            Toast.makeText(this, R.string.text_empty, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginReturn(LoginData loginBean) {
        if(!TextUtils.isEmpty(loginBean.getData().getToken())){
            SpUtils.getInstance().setValue("token",loginBean.getData().getToken());
            SpUtils.getInstance().setValue("uid",loginBean.getData().getUserInfo().getUid());
            finish();
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}