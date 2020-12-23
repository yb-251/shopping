package com.umeng.soexample.ui.home;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.interfaces.home.IBrandDetails;
import com.umeng.soexample.model.home.BrandDetailsData;
import com.umeng.soexample.model.home.BrandDetailsItemData;
import com.umeng.soexample.model.home.BrandDetailsListData;
import com.umeng.soexample.presenter.home.BrandDetailsPresenter;

import java.util.ArrayList;
import java.util.List;

public class BrandDetailsActivity extends BaseActivity<BrandDetailsPresenter> implements IBrandDetails.View {

    private RecyclerView rlv_brand_details;
    private SmartRefreshLayout srl;
    private List<BrandDetailsData.DataBeanX.DataBean> brandDetailsBean;
    private BrandDetailsAdapter brandDetailsAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_brand_details;
    }

    @Override
    protected BrandDetailsPresenter createPrenter() {
        return new BrandDetailsPresenter();
    }

    protected void initView() {
        rlv_brand_details = (RecyclerView) findViewById(R.id.rlv_brand_details);
        srl = (SmartRefreshLayout) findViewById(R.id.srl);

        rlv_brand_details.setLayoutManager(new LinearLayoutManager(this));
        brandDetailsBean = new ArrayList<>();
        brandDetailsAdapter = new BrandDetailsAdapter(this, brandDetailsBean);
        rlv_brand_details.setAdapter(brandDetailsAdapter);
    }

    @Override
    protected void initData() {
        presenter.getBrandDetailsData();
    }

    @Override
    public void getBrandDetailsReturn(BrandDetailsData brandDetailsData) {
        brandDetailsBean.clear();
        brandDetailsBean.addAll(brandDetailsData.getData().getData());
        brandDetailsAdapter.notifyDataSetChanged();
        brandDetailsAdapter.setOnItemClickListener(new BrandDetailsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                Intent intent = new Intent(BrandDetailsActivity.this,BrandItemDetailsActivity.class);
                intent.putExtra("id",brandDetailsData.getData().getData().get(pos).getId());
                intent.putExtra("pos",pos);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getBrandDetailsItemReturn(BrandDetailsItemData brandDetailsItemData) {

    }

    @Override
    public void getBrandDetailsListReturn(BrandDetailsListData brandDetailsListData) {

    }

}
