package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.data.BrandDetailsListData;

import java.util.List;

public class BrandListAdapter extends BaseAdapter {
    public BrandListAdapter(Context context, List<BrandDetailsListData.DataBeanX.DataBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.layout_newgoods;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView newgoods_title = (TextView) vh.getViewById(R.id.newgoods_title);
        TextView newgoods_price = (TextView) vh.getViewById(R.id.newgoods_price);
        ImageView newgoods_img = (ImageView) vh.getViewById(R.id.newgoods_img);
        BrandDetailsListData.DataBeanX.DataBean data1 = (BrandDetailsListData.DataBeanX.DataBean) data;
        Glide.with(context).load(data1.getList_pic_url()).into(newgoods_img);
        newgoods_price.setText("￥"+data1.getRetail_price());
        newgoods_title.setText(data1.getName());
    }
}
