package com.umeng.soexample.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.base.home.ICategory;
import com.umeng.soexample.model.CategoryBean;
import com.umeng.soexample.model.CategoryGoodBean;
import com.umeng.soexample.presenter.CategoryPresenter;
import com.umeng.soexample.ui.adapter.CategoryGoodAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChannelListActivity extends BaseActivity<ICategory.Presenter> implements ICategory.View {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.recy_goods)
    RecyclerView recyGoods;

    List<CategoryGoodBean.DataBeanX.GoodsListBean> goods;
    CategoryGoodAdapter categoryGoodAdapter;
    List<CategoryBean.DataBean.BrotherCategoryBean> tabs;

    private int currentCategoryId;
    private boolean isInit = false;


    @Override
    protected int getLayout() {
        return R.layout.activity_channel;
    }

    @Override
    protected CategoryPresenter createPrenter() {
        return new CategoryPresenter();
    }

    @Override
    protected void initView() {

        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (pos == 0 && !isInit) {
                    //还没有初始化完
                } else {
                    currentCategoryId = pos;
                    if (pos < tabs.size()) {
                        //获取列表数据
                        presenter.getCategoryGood(tabs.get(pos).getId(), 1, 100);
                    } else {
                        throw new RuntimeException("数据无效");
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {

        goods = new ArrayList<>();
        categoryGoodAdapter = new CategoryGoodAdapter(this, goods);
        recyGoods.setLayoutManager(new GridLayoutManager(this, 2));
        recyGoods.setAdapter(categoryGoodAdapter);
        Intent intent = getIntent();
        if (intent != null) {
            currentCategoryId = intent.getIntExtra("categoryid", 0);
            presenter.getCategoryTab(currentCategoryId);
        }

    }

    @Override
    public void getCategoryTabReturn(CategoryBean result) {
        //init tab
        tabs = result.getData().getBrotherCategory();
        for (CategoryBean.DataBean.BrotherCategoryBean item : result.getData().getBrotherCategory()) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(item.getName());
            tab.setTag(item.getId());
            tabLayout.addTab(tab);
            //通过一个界面传过来的categoryid判断哪个tab选中
            if (currentCategoryId == item.getId()) {
                for (int i = 0; i < tabs.size(); i++) {

                }
            }
        }
        //记录上去页面初始化完成状态
        isInit = true;
    }

    @Override
    public void getCategoryGoodReturn(CategoryGoodBean result) {
        goods.clear();
        goods.addAll(result.getData().getGoodsList());
        categoryGoodAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.img_back, R.id.img_menu, R.id.img_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_menu:
                break;
            case R.id.img_share:
                break;
        }
    }
}