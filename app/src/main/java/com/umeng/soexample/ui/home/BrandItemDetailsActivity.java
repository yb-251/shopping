package com.umeng.soexample.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.base.home.IBrandDetails;
import com.umeng.soexample.model.data.BrandDetailsData;
import com.umeng.soexample.model.data.BrandDetailsItemData;
import com.umeng.soexample.model.data.BrandDetailsListData;
import com.umeng.soexample.presenter.BrandDetailsPresenter;
import com.umeng.soexample.ui.adapter.BrandItemAdapter;
import com.umeng.soexample.ui.adapter.BrandListAdapter;

import java.util.ArrayList;
import java.util.List;

public class BrandItemDetailsActivity extends BaseActivity<BrandDetailsPresenter> implements IBrandDetails.View {

    private RecyclerView rlv_brand_item;
    private RecyclerView rlv_brand_list;
    private int id;
    private int pos;
    int page = 1;
    int size = 100;
    private List<BrandDetailsItemData.DataBean.BrandBean> itemBean;
    private BrandItemAdapter brandItemAdapter;
    private List<BrandDetailsListData.DataBeanX.DataBean> beanList;
    private BrandListAdapter brandListAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_item_details;
    }

    @Override
    protected BrandDetailsPresenter createPrenter() {
        return new BrandDetailsPresenter();
    }

    @Override
    protected void initView() {
        rlv_brand_item = (RecyclerView) findViewById(R.id.rlv_brand_item);
        rlv_brand_list = (RecyclerView) findViewById(R.id.rlv_brand_list);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        pos = intent.getIntExtra("pos",0);

        rlv_brand_item.setLayoutManager(new LinearLayoutManager(this));
        itemBean = new ArrayList<>();
        brandItemAdapter = new BrandItemAdapter(this, itemBean);
        rlv_brand_item.setAdapter(brandItemAdapter);

        rlv_brand_list.setLayoutManager(new GridLayoutManager(this,2));
        beanList = new ArrayList<>();
        brandListAdapter = new BrandListAdapter(this, beanList);
        rlv_brand_list.setAdapter(brandListAdapter);
    }

    @Override
    protected void initData() {
        presenter.getBrandDetailsItemData(id);
        presenter.getBrandDetailsListData(id,page,size);
    }

    @Override
    public void getBrandDetailsReturn(BrandDetailsData brandDetailsData) {

    }

    @Override
    public void getBrandDetailsItemReturn(BrandDetailsItemData brandDetailsItemData) {
        itemBean.clear();
        itemBean.add(brandDetailsItemData.getData().getBrand());
        brandItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBrandDetailsListReturn(BrandDetailsListData brandDetailsListData) {
        if (pos==0){
            beanList.clear();
            beanList.addAll(brandDetailsListData.getData().getData());
            brandListAdapter.notifyDataSetChanged();
        }
    }
}