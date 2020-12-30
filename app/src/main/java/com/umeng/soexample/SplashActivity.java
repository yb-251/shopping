package com.umeng.soexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.ui.home.SplashVpAdapter;
import com.umeng.soexample.ui.shop.ShoppingActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    private ViewPager splash_vp;
    private ArrayList<View> views;
    private SplashVpAdapter splashVpAdapter;
    Disposable disposable;
    private TextView tv_timer;
    private TextView jump;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {
        splash_vp = findViewById(R.id.splash_vp);
    }

    @Override
    protected void initData() {
        views = new ArrayList<>();

        View p1 = LayoutInflater.from(this).inflate(R.layout.page_one, null);
        View p2 = LayoutInflater.from(this).inflate(R.layout.page_two, null);
        View p3 = LayoutInflater.from(this).inflate(R.layout.page_three, null);
        views.add(p1);
        views.add(p2);
        views.add(p3);

        splashVpAdapter = new SplashVpAdapter(this, views);
        splash_vp.setAdapter(splashVpAdapter);

        tv_timer = p3.findViewById(R.id.tv_timer);
        jump = p3.findViewById(R.id.jump);

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, ShoppingActivity.class));
                cancelCallback();
            }
        });

        //页码的点击监听
        splash_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){//在最后一页执行倒计时
                    //TODO            Interval操作符(有范围)：创建一个按照固定时间发射整数序列的Observable
                    disposable = Observable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS) //起始值，发送总数量，初始延迟，固定延迟
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            //两种写法    1. lambda表达式写法：
//                            .subscribe(time -> tv_timer.setText((10 - time) + "s"),
//                                    Throwable::printStackTrace,
//                                    () -> {
//                                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                                    }
//                            );

                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    long time = 3 - aLong;
                                    tv_timer.setText("倒计时："+time+"s");
                                    if(time == 0){
                                        startActivity(new Intent(SplashActivity.this,ShoppingActivity.class));
                                    }
                                }
                            });
                }else{
                    cancelCallback();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //取消订阅的方法
    private void cancelCallback() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}