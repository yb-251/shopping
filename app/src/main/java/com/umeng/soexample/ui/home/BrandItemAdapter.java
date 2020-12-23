package com.umeng.soexample.ui.home;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;
import com.umeng.soexample.model.home.BrandDetailsItemData;

import java.util.List;

public class BrandItemAdapter extends BaseAdapter {
    public BrandItemAdapter(Context context, List<BrandDetailsItemData.DataBean.BrandBean> data) {
        super(context, data);
    }

    @Override
    protected int getLayout(int type) {
        return R.layout.item_brand_item;
    }

    @Override
    protected void bindData(Object data, VH vh) {
        ImageView img_item_brand = (ImageView) vh.getViewById(R.id.img_item_brand);
        TextView txt_brand_info = (TextView) vh.getViewById(R.id.txt_brand_info);
        TextView tv_brand_details = (TextView) vh.getViewById(R.id.tv_brand_details);
        BrandDetailsItemData.DataBean.BrandBean data1 = (BrandDetailsItemData.DataBean.BrandBean) data;
        Glide.with(context).load(data1.getList_pic_url()).into(img_item_brand);
        txt_brand_info.setText(data1.getName());
        tv_brand_details.setText(data1.getSimple_desc());
    }
}
