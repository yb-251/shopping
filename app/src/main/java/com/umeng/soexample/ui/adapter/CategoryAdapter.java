package com.umeng.soexample.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    public CategoryAdapter(Context context, List data) {
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
        HomeData.DataBean.CategoryListBean.GoodsListBean bean = (HomeData.DataBean.CategoryListBean.GoodsListBean) data;
        TxtUtils.setTextView(newgoods_title,bean.getName());
        TxtUtils.setTextView(newgoods_price,"￥"+bean.getRetail_price());
        Glide.with(context).load(bean.getList_pic_url()).into(newgoods_img);
    }
}
