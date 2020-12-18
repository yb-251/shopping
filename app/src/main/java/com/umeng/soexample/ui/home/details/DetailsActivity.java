package com.umeng.soexample.ui.home.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.MainActivity;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.base.home.IDetails;
import com.umeng.soexample.model.DetailsGoodData;
import com.umeng.soexample.presenter.DetailsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements IDetails.View, View.OnClickListener {

    ImageView imgBack;
    ImageView imgMenu;
    ImageView imgShare;
    TabLayout tabDetails;
    ViewPager vpDetails;
    private ArrayList<DetailsTabBean.DataBean.CategoryListBean> tabs;
    private ArrayList<Fragment> fragments;
    // 定义标题栏弹窗按钮
    private TitlePopup titlePopup;
    private List<DetailsTabBean.DataBean.CategoryListBean> categoryList;
    public String id;
    private String name;


    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected DetailsPresenter createPrenter() {
        return new DetailsPresenter();
    }

    @Override
    protected void initView() {
        imgBack = findViewById(R.id.img_back);
        imgMenu = findViewById(R.id.img_menu);
        tabDetails = findViewById(R.id.tab_details);
        vpDetails = findViewById(R.id.vp_details);
        Intent intent = getIntent();
        String cid = intent.getStringExtra("cid");
        name = intent.getStringExtra("name");
        id = cid.substring(cid.length()-7);
        System.out.println(id.toString());
        titlePopup = new TitlePopup(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titlePopup.addAction(new ActionItem(this, "消息", R.drawable.mm_title_btn_compose_normal));
        titlePopup.addAction(new ActionItem(this, "首页", R.drawable.mm_title_btn_receiver_normal));
        titlePopup.addAction(new ActionItem(this, "搜索", R.drawable.mm_title_btn_keyboard_normal));
        titlePopup.addAction(new ActionItem(this, "购物车", R.drawable.mm_title_btn_qrcode_normal));
        titlePopup.setItemOnClickListener(new TitlePopup.OnItemOnClickListener() {
            @Override
            public void onItemClick(ActionItem item, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                        finish();
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
        imgBack.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        presenter.getDetailsTabData();
        presenter.getDetailsGoodsData(Integer.parseInt(id));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.img_menu:
                titlePopup.show(view);
                break;
            case R.id.img_share:
                break;
        }
    }


    @Override
    public void getDetailsTabReturn(DetailsTabBean detailsTabBean) {
        categoryList = detailsTabBean.getData().getCategoryList();
        tabs = new ArrayList<>();
        fragments = new ArrayList<>();
        for (int i = 0; i <categoryList.size() ; i++) {
            DetailsFragment detailsFragment = new DetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",categoryList.get(i).getId());
            detailsFragment.setArguments(bundle);
            fragments.add(detailsFragment);
        }
        tabs.addAll(categoryList);
        vpDetails.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabs.get(position).getName();
            }
        });
        tabDetails.setupWithViewPager(vpDetails);

        for (int i = 0; i <categoryList.size() ; i++) {
            tabDetails.getTabAt(i).setText(categoryList.get(i).getName());
            if (name.equals(categoryList.get(i).getName())){
                tabDetails.getTabAt(i).select();
            }
        }
    }

    @Override
    public void getDetailsGoodsReturn(DetailsGoodData detailsGoodData) {

    }

}
