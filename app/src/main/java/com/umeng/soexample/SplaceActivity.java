package com.umeng.soexample;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.IBasePresenter;
import com.umeng.soexample.ui.start.SplaceFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplaceActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.img_1_normal)
    ImageView img1Normal;
    @BindView(R.id.img_1_select)
    ImageView img1Select;
    @BindView(R.id.img_2_normal)
    ImageView img2Normal;
    @BindView(R.id.img_2_select)
    ImageView img2Select;
    @BindView(R.id.img_3_normal)
    ImageView img3Normal;
    @BindView(R.id.img_3_select)
    ImageView img3Select;

    List<SplaceFragment> list;

    @Override
    protected int getLayout() {
        //隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splace;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    protected void initView() {

    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add(SplaceFragment.getInstance(1));
        list.add(SplaceFragment.getInstance(2));
        list.add(SplaceFragment.getInstance(3));
        viewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetDots();
                if(position == 0){
                    img1Normal.setVisibility(View.GONE);
                    img1Select.setVisibility(View.VISIBLE);
                }else if(position == 1){
                    img2Normal.setVisibility(View.GONE);
                    img2Select.setVisibility(View.VISIBLE);
                }else if(position == 2){
                    img3Normal.setVisibility(View.GONE);
                    img3Select.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void resetDots(){
        img1Normal.setVisibility(View.VISIBLE);
        img2Normal.setVisibility(View.VISIBLE);
        img3Normal.setVisibility(View.VISIBLE);
        img1Select.setVisibility(View.GONE);
        img2Select.setVisibility(View.GONE);
        img3Select.setVisibility(View.GONE);
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

}