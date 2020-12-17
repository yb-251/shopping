package com.umeng.soexample.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.HomeData;
import com.umeng.soexample.utils.TxtUtils;

import java.util.List;

public class BrandAdapter extends BaseAdapter {
    public BrandAdapter(Context context, List<HomeData.DataBean.BrandListBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.brand_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        TextView brand_title = (TextView) vh.getViewById(R.id.brand_title);
        TextView brand_price = (TextView) vh.getViewById(R.id.brand_price);
        ImageView brand_img = (ImageView) vh.getViewById(R.id.brand_img);
        HomeData.DataBean.BrandListBean bean = (HomeData.DataBean.BrandListBean) data;
        TxtUtils.setTextView(brand_price,bean.getFloor_price()+"元");
        TxtUtils.setTextView(brand_title,bean.getName());
        Glide.with(context).load(bean.getNew_pic_url()).into(brand_img);
    }
}
